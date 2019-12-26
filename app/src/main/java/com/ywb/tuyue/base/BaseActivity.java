package com.ywb.tuyue.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.blankj.utilcode.util.ToastUtils;
import com.ywb.tuyue.MyApplication;
import com.ywb.tuyue.di.component.ActivityComponent;
import com.ywb.tuyue.di.component.ApplicationComponent;
import com.ywb.tuyue.di.component.DaggerActivityComponent;
import com.ywb.tuyue.di.module.ActivityModule;
import com.ywb.tuyue.receiver.ScreenOnReceiver;

import java.io.InputStream;
import java.lang.ref.WeakReference;

import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;


/**
 * @Author soonphe
 * @Date 2017-12-01 15:13
 * @Description BaseActivity
 */
public abstract class BaseActivity extends AppCompatActivity implements IBaseActivity, IBaseConstant {

    /*** 管理Rxjava内存泄漏 **/
    protected CompositeDisposable compositeDisposable = new CompositeDisposable();
    /*** 整个应用Applicaiton **/
    private MyApplication mApplication = null;
    /***当前Activity的弱引用，防止内存泄露**/
    private WeakReference<Activity> mContextWR = null;
    /*** 动画类型**/
    private int mAnimationType = NONE;
    /*** 是否运行截屏**/
    private boolean isCanScreenshot = true;
    /*** 设置是否触摸关闭输入法 **/
    private boolean autoDissIm = false;
    /*** 夜间模式开关**/
    private static boolean enableNightMode = false;
    /*** 日志输出标志**/
    protected final String TAG = this.getClass().getSimpleName();
    /*** 共通操作**/
    protected Operation mOperation = null;

    private ScreenOnReceiver mScreenOnReceiver; //解锁屏广播  无论用户在看哪个模块，只要按下锁屏，在开屏时 从第一个页面开始展示


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "BaseActivity-->onCreate()");
        // 获取应用Application
        mApplication = (MyApplication) getApplicationContext();
        //运行dagger注入
        initInjector();
        //需要在setContentView之前配置window的一些属性
        config(savedInstanceState);
        //调用bindLayout渲染当前Activity的视图View
        ViewGroup mContextView = (ViewGroup) LayoutInflater.from(this).inflate(bindLayout(), null);
        setContentView(mContextView);
        //ButterKnife注解绑定
        ButterKnife.bind(this);
        // 将当前Activity压入栈
        mContextWR = new WeakReference<>(this);
        mApplication.pushActivity(mContextWR);
        // 实例化共通操作
        mOperation = new Operation(this);
        // 获取跳转参数
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mAnimationType = bundle.getInt(ANIMATION_TYPE, NONE);
        } else {
            bundle = new Bundle();
        }
        // 初始化参数
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

    /**
     * dagger依赖注入（onCreate方法中调用）
     */
    public abstract void initInjector();

    /**
     * 初始化ActivityComponent
     * 每个继承与BaseActivity的类都需要在调用getComponent().inject(this);
     */
    public ActivityComponent getComponent() {
        return DaggerActivityComponent.builder()
                .activityModule(getActivityModule())
                .applicationComponent(getApplicationComponent())
                .build();
    }

    /*** 获取ApplicationComponent **/
    protected ApplicationComponent getApplicationComponent() {
        return mApplication.getApplicationComponent();
    }

    /*** 构造ActivityModule **/
    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    @Override
    public void config(Bundle savedInstanceState) {
        //这里可以添加在setContentView之前的一些window配置
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
        //这里可以添加在onResume后的配置
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
        //关闭弹窗
//        mOperation.dissMissDialog();
        //将当前Activity对象从栈中移除
        mApplication.removeActivity(mContextWR);
        if(compositeDisposable!=null){
            compositeDisposable.clear();
        }
        //注销广播
        unregisterReceiver(mScreenOnReceiver);
    }
    @Override
    public void destroy() {
        //这里可以添加在onDestroy后的配置
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //让Fragment可以消费
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void finish() {
        super.finish();
        int mAnimIn = 0;
        int mAnimOut = 0;
        switch (mAnimationType) {
            //左进右出
            case LEFT_RIGHT:
                mAnimIn = BaseView.gainResId(mApplication, BaseView.ANIM, "base_slide_left_in");
                mAnimOut = BaseView.gainResId(mApplication, BaseView.ANIM, "base_slide_right_out");
                overridePendingTransition(mAnimIn, mAnimOut);
                break;
            //上进下出
            case TOP_BOTTOM:
                mAnimIn = BaseView.gainResId(mApplication, BaseView.ANIM, "base_push_up_in");
                mAnimOut = BaseView.gainResId(mApplication, BaseView.ANIM, "base_push_bottom_out");
                overridePendingTransition(mAnimIn, mAnimOut);
                break;
            case FADE_IN_OUT:
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

    /**
     * 获取当前Activity
     *
     * protected-public
     * @return
     */
    public Activity getContext() {
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

    protected void Toast(int message) {
        ToastUtils.showShort(getResources().getString(message));
    }

    protected void Toast(String message) {
        ToastUtils.showShort(message);
    }

    /**
     * 是否可以夜间模式
     *
     * @return true or false
     */
    public boolean isEnableNightMode() {
        return enableNightMode;
    }

    /**
     * 设置夜间模式
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

    /**
     * 重新加载页面
     */
    public void reload() {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }


}
