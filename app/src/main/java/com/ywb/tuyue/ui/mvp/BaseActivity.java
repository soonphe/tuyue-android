package com.ywb.tuyue.ui.mvp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.blankj.utilcode.util.ToastUtils;
import com.ywb.tuyue.MyApplication;
import com.ywb.tuyue.di.component.ActivityComponent;
import com.ywb.tuyue.di.component.ApplicationComponent;
import com.ywb.tuyue.di.component.DaggerActivityComponent;
import com.ywb.tuyue.di.module.ActivityModule;
import com.ywb.tuyue.receiver.ScreenOnReceiver;
import com.ywb.tuyue.utils.ActivityManager;

import java.io.InputStream;
import java.lang.ref.WeakReference;

import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;


/**
 * android 系统中的四大组件之一Activity基类
 *
 * @version 1.0
 */
public abstract class BaseActivity extends AppCompatActivity implements IBaseActivity, IBaseConstant {

    protected CompositeDisposable compositeDisposable = new CompositeDisposable();
    /*** 整个应用Applicaiton **/
    private MyApplication mApplication = null;
    /***当前Activity的弱引用，防止内存泄露**/
    private WeakReference<Activity> mContextWR = null;
    /*** 动画类型**/
    private int mAnimationType = NONE;
    /*** 是否运行截屏**/
    private boolean isCanScreenshot = true;
    private boolean autoDissIm = false;//是否自动检测点击屏幕边缘隐藏输入法
    /*** 共通操作**/
    protected Operation mOperation = null;
    /*** 日志输出标志**/
    protected final String TAG = this.getClass().getSimpleName();

    protected ActivityComponent mActivityComponent;
    private static boolean enableNightMode = false;

    private ScreenOnReceiver mScreenOnReceiver; //解锁屏广播  无论用户在看哪个模块，只要按下锁屏，在开屏时 从第一个页面开始展示

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Log.d(TAG, "BaseActivity-->onCreate()");
        // 获取应用Application
        mApplication = (MyApplication) getApplicationContext();
        initInjector();
        //需要在setContentView之前配置window的一些属性
        config(savedInstanceState);
        // 设置渲染视图View
        /*
          当前Activity渲染的视图View
        */
        ViewGroup mContextView = (ViewGroup) LayoutInflater.from(this).inflate(bindLayout(), null);
        setContentView(mContextView);
        ButterKnife.bind(this);

        // 将当前Activity压入栈
        mContextWR = new WeakReference<Activity>(this);
        mApplication.pushActivity(mContextWR);
        ActivityManager.getActivityManager().addActivity(this);
        // 实例化共通操作
        mOperation = new Operation(this);
        // 初始化参数
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mAnimationType = bundle.getInt(ANIMATION_TYPE, NONE);
        } else {
            bundle = new Bundle();
        }
        initParms(bundle);
        // 初始化控件
        initView(mContextView);
        // 业务操作
        doBusiness(this);
        // 是否可以截屏
        if (!isCanScreenshot) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);
        }

        //注册锁屏广播
        if (mScreenOnReceiver == null) {
            mScreenOnReceiver = new ScreenOnReceiver();
        }
        registerScreenBroadcastReceiver(); //注册锁屏广播
    }
    /**
     * 注册锁屏的广播
     */
    private void registerScreenBroadcastReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_SCREEN_ON);
        registerReceiver(mScreenOnReceiver, filter);
    }


    @Override
    public void config(Bundle savedInstanceState) {

    }

    /**
     * 注入Injector
     */
    public abstract void initInjector();

    /*ActivityComponent提供依赖*/
    public ActivityComponent getComponent() {
        return DaggerActivityComponent.builder()
                .activityModule(getActivityModule())
                .applicationComponent(getApplicationComponent())
                .build();
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((MyApplication) getApplication()).getApplicationComponent();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "BaseActivity-->onRestart()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "BaseActivity-->onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "BaseActivity-->onResume()");
        resume();
    }

    @Override
    public void resume() {

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "BaseActivity-->onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "BaseActivity-->onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "BaseActivity-->onDestroy()");
        destroy();
        mOperation.dissMissDialog();
        mApplication.removeActivity(mContextWR);

        if(compositeDisposable!=null){
            compositeDisposable.clear();
        }

        unregisterReceiver(mScreenOnReceiver);//LS:重点！

    }
    @Override
    public void destroy() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //让Fragment可以消费
        super.onActivityResult(requestCode, resultCode, data);
    }


    /**
     * 获取当前Activity
     *
     * @return
     */
    protected Activity getContext() {
        if (null != mContextWR)
            return mContextWR.get();
        else
            return null;
    }

    /**
     * 获取共通操作机能
     */
    public Operation getOperation() {
        return this.mOperation;
    }

    /**
     * 设置是否可截屏
     */
    public void setCanScreenshot(boolean isCanScreenshot) {
        this.isCanScreenshot = isCanScreenshot;
    }

    /**
     * 设置是否触摸关闭输入法
     *
     * @param autoDissIm
     */
    public void setTouchDissIm(boolean autoDissIm) {
        this.autoDissIm = autoDissIm;
    }

    /**
     * 引导状态存储偏好
     */
    protected final static String GUIDE_STATUS = "guide_status_sp";


    /**
     * 调用JNI底层实现获取本地图片资源
     *
     * @param mContext
     * @param resId
     * @return
     */
    public Bitmap readBitMap(Context mContext, int resId) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        opt.inJustDecodeBounds = false;
        // width，hight设为原来的十分一
        // opt.inSampleSize = 10;
        // 获取资源图片
        InputStream is = mContext.getResources().openRawResource(resId);
        return BitmapFactory.decodeStream(is, null, opt);
    }



    public void finish() {
        super.finish();
        int mAnimIn = 0;
        int mAnimOut = 0;
        switch (mAnimationType) {
            //左进右出
            case IBaseActivity.LEFT_RIGHT:
                mAnimIn = BaseView.gainResId(mApplication, BaseView.ANIM, "base_slide_left_in");
                mAnimOut = BaseView.gainResId(mApplication, BaseView.ANIM, "base_slide_right_out");
                overridePendingTransition(mAnimIn, mAnimOut);
                break;
            //上进下出
            case IBaseActivity.TOP_BOTTOM:
                mAnimIn = BaseView.gainResId(mApplication, BaseView.ANIM, "base_push_up_in");
                mAnimOut = BaseView.gainResId(mApplication, BaseView.ANIM, "base_push_bottom_out");
                overridePendingTransition(mAnimIn, mAnimOut);
                break;
            case IBaseActivity.FADE_IN_OUT:
                mAnimIn = BaseView.gainResId(mApplication, BaseView.ANIM, "base_fade_in");
                mAnimOut = BaseView.gainResId(mApplication, BaseView.ANIM, "base_fade_out");
                overridePendingTransition(mAnimIn, mAnimOut);
                break;
            default:
                break;
        }
        mAnimationType = NONE;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (autoDissIm) {
            if (ev.getAction() == MotionEvent.ACTION_DOWN) {
                View v = getCurrentFocus();
                if (isShouldHideInput(v, ev)) {

                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm != null) {
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    }
                }
                return super.dispatchTouchEvent(ev);
            }
            if (getWindow().superDispatchTouchEvent(ev)) {
                return true;
            }

        } else {
            return super.dispatchTouchEvent(ev);
        }
        return onTouchEvent(ev);
    }


    public static boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    protected void showProgressDialog(String context, boolean cancle) {
        mOperation.showProgress(context, cancle);

    }

    protected void showProgressDialog(String context) {
        mOperation.showProgress(context);

    }

    protected void dissmissDialog() {
        if (mOperation != null) {
            mOperation.dissMissDialog();
        }
    }

    protected void Toast(int message) {
        ToastUtils.showShort(getResources().getString(message));
    }

    protected void Toast(String message) {
        ToastUtils.showShort(message);
    }

    /**
     * If enabled night mode
     *
     * @return true or false
     */
    public boolean isEnableNightMode() {
        return enableNightMode;
    }

    /**
     * enable night mode or not
     *
     * @param enableNightMode true or false
     */
    public void setEnableNightMode(boolean enableNightMode) {
        this.enableNightMode = enableNightMode;
        if (enableNightMode) {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        recreate();
    }

    public void reload() {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
