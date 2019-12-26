package com.ywb.tuyue.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.ywb.tuyue.MyApplication;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @Author soonphe
 * @Date 2017-12-01 15:13
 * @Description Operation
 */
public class Operation {

    /*** Toast **/
    private Toast mToast;
    /*** Activity之间数据传输数据对象默认Key **/
    public static final String ACTIVITY_DTO_KEY = "ACTIVITY_DTO_KEY";
    /*** 激活Activity组件意图**/
    private Intent mIntent = new Intent();
    /*** 上下文 **/
    private Activity mContext = null;
    /*** 整个应用Applicaiton **/
    private MyApplication mApplication = null;
    /*** 日志输出标志**/
    private final static String TAG = Operation.class.getSimpleName();
    private MaterialDialog dialog;

    public MaterialDialog getDialog() {
        return dialog;
    }

    public Operation(Activity mContext) {
        this.mContext = mContext;
        mApplication = (MyApplication) this.mContext.getApplicationContext();
    }

    /**
     * Activity跳转并finish
     *
     * @param activity  目标activity
     * @param animeType 动画类型
     */
    public void forwardAndFinish(Class activity, int animeType) {
        forwardActivity(activity, animeType);
        mContext.finish();
    }

    /**
     * Activity跳转并finish，默认动画从左至右
     *
     * @param activity 目标activity
     */
    public void forwardAndFinish(Class activity) {
        forwardActivity(activity, IBaseConstant.LEFT_RIGHT);
        mContext.finish();
    }

    /**
     * Activity跳转
     *
     * @param activity  目标activity
     * @param animeType 动画类型
     */
    public void forward(Class activity, int animeType) {
        forwardActivity(activity, animeType);
    }

    /**
     * Activity跳转并，默认动画从左至右
     *
     * @param activity 目标activity
     */
    public void forward(Class activity) {
        forwardActivity(activity, IBaseConstant.LEFT_RIGHT);
    }

    /**
     * 跳转Activity方法
     *
     * @param className 目标activity
     * @param animaType 动画类型LEFT_RIGHT/TOP_BOTTOM/FADE_IN_OUT
     */
    private void forwardActivity(Class className, int animaType) {
        mIntent.setClass(mContext, className);
        mIntent.putExtra(IBaseActivity.ANIMATION_TYPE, animaType);
        mContext.startActivity(mIntent);

        int mAnimIn = 0;
        int mAnimOut = 0;
        switch (animaType) {
            case IBaseActivity.LEFT_RIGHT:
                mAnimIn = BaseView.gainResId(mApplication, BaseView.ANIM, "base_slide_right_in");
                mAnimOut = BaseView.gainResId(mApplication, BaseView.ANIM, "base_slide_left_out");
                mContext.overridePendingTransition(mAnimIn, mAnimOut);
                break;
            case IBaseActivity.TOP_BOTTOM:
                mAnimIn = BaseView.gainResId(mApplication, BaseView.ANIM, "base_push_bottom_in");
                mAnimOut = BaseView.gainResId(mApplication, BaseView.ANIM, "base_push_up_out");
                mContext.overridePendingTransition(mAnimIn, mAnimOut);
                break;
            case IBaseActivity.FADE_IN_OUT:
                mAnimIn = BaseView.gainResId(mApplication, BaseView.ANIM, "base_fade_in");
                mAnimOut = BaseView.gainResId(mApplication, BaseView.ANIM, "base_fade_out");
                mContext.overridePendingTransition(mAnimIn, mAnimOut);
                break;
            default:
                break;
        }
    }

    /**
     * 设置传递参数
     *
     * @param value 数据传输对象
     */
    public void addParameter(HashMap value) {
        mIntent.putExtra(ACTIVITY_DTO_KEY, value);
    }

    /**
     * 设置传递参数
     *
     * @param key   参数key
     * @param value 数据传输对象
     */
    public void addParameter(String key, HashMap value) {
        mIntent.putExtra(key, value);
    }

    /**
     * 设置传递参数
     *
     * @param key   参数key
     * @param value 数据传输对象
     */
    public void addParameter(String key, Bundle value) {
        mIntent.putExtra(key, value);
    }

    /**
     * 设置传递参数
     *
     * @param key   参数key
     * @param value 数据传输对象
     */
    public void addParameter(String key, Serializable value) {
        mIntent.putExtra(key, value);
    }

    /**
     * 设置传递参数
     *
     * @param key   参数key
     * @param value 数据传输对象
     */
    public void addParameter(String key, String value) {
        mIntent.putExtra(key, value);
    }

    /**
     * 获取跳转时设置的参数
     *
     * @param key
     * @return
     */
    public Object getParameter(String key) {
        Bundle extras = mContext.getIntent().getExtras();
        if (null == extras) return null;

        return mContext.getIntent().getExtras().get(key);
    }

    /**
     * 获取跳转时设置的参数
     *
     * @param key
     * @return
     */
    public Object getListParameter(String key) {
        Bundle extras = mContext.getIntent().getExtras();
        if (null == extras) return null;

        return mContext.getIntent().getSerializableExtra(key);
    }

    /**
     * 获取跳转参数集合
     *
     * @return
     */
    public HashMap getParameters() {
        HashMap parms = (HashMap) mContext.getIntent().getExtras().getSerializable(ACTIVITY_DTO_KEY);
        return parms;
    }

    /**
     * 设置全局Application传递参数
     *
     * @param strKey 参数key
     * @param value  数据传输对象
     */
    public void addGloableAttribute(String strKey, Object value) {
        mApplication.assignData(strKey, value);
    }

    /**
     * 获取跳转时设置的参数
     *
     * @param strKey
     * @return
     */
    public Object getGloableAttribute(String strKey) {
        return mApplication.gainData(strKey);
    }


    /**
     * 获取资源文件id
     *
     * @param mContext 上下文
     * @param resType  资源类型（drawable/string/layout/style/dimen/color/array等）
     * @param resName  资源文件名称
     * @return
     */
    public int gainResId(Context mContext, String resType, String resName) {
        int result = -1;
        try {
            String packageName = mContext.getPackageName();
            result = mContext.getResources().getIdentifier(resName, resType, packageName);
        } catch (Exception e) {
            result = -1;
            Log.e(TAG, "获取资源文件失败，原因：" + e.getMessage());
        }

        return result;
    }

    /***
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * *
     * ToastUtil                                                   *
     * *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */

    public void showToast(CharSequence text) {
        if (mToast == null) {
            mToast = Toast.makeText(mContext, text, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(text);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    public void EmptyToast() {
        if (mToast == null) {
            mToast = Toast.makeText(mContext, "不允许为空！", Toast.LENGTH_SHORT);
        } else {
            mToast.setText("不允许为空！");
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    public void cancelToast() {
        if (mToast != null) {
            mToast.cancel();
        }
    }

    public void showToastInCenter(String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(mContext, msg, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msg);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.setGravity(Gravity.CENTER, 0, 0);
        mToast.show();
    }

    /***
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * *
     * MetraDesginDialog                                           *
     * *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */
    public void showProgress(String context) {
        dismissDialog();
        dialog = new MaterialDialog.Builder(mContext)
                .cancelable(true)
                .content(context)
                .progress(true, 0)
                .progressIndeterminateStyle(false)
                .show();
    }

    public void showProgress(String context, boolean cacanle) {
        dismissDialog();
        dialog = new MaterialDialog.Builder(mContext)
                .cancelable(cacanle)
                .content(context)
                .progress(true, 0)
                .progressIndeterminateStyle(false)
                .show();
    }

    public void dismissDialog() {
        if (!mContext.isFinishing() && dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

}
