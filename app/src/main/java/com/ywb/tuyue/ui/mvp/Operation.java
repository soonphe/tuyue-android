package com.ywb.tuyue.ui.mvp;

import android.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.ywb.tuyue.MyApplication;

import java.io.Serializable;

/**
 * 基本的操作共通抽取
 *
 * @version 1.0
 */
public class Operation {
    private Toast mToast;
    /**
     * 激活Activity组件意图
     **/
    private              Intent        mIntent      = new Intent();
    /*** 上下文 **/
    private              Activity      mContext     = null;
    /*** 整个应用Applicaiton **/
    private MyApplication mApplication = null;
    /**
     * 日志输出标志
     **/
    private final static String        TAG          = Operation.class.getSimpleName();
    private MaterialDialog dialog;

    public MaterialDialog getDialog() {
        return dialog;
    }

    public Operation(Activity mContext) {
        this.mContext = mContext;
        mApplication = (MyApplication) this.mContext.getApplicationContext();
    }

    public void forwardAndFinish(Class activity, int animeType) {
        forwardActivity(activity, animeType);
        mContext.finish();
    }

    public void forwardAndFinish(Class activity) {
        forwardActivity(activity, IBaseConstant.LEFT_RIGHT);
        mContext.finish();
    }

    public void forward(Class activity, int animeType) {
        forwardActivity(activity, animeType);
    }

    public void forward(Class activity) {
        forwardActivity(activity, IBaseConstant.LEFT_RIGHT);
    }

    /**
     * 跳转Activity
     *
     * @param animaType 动画类型IBaseActivity.LEFT_RIGHT/TOP_BOTTOM/FADE_IN_OUT
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
    public void addParameter(DTO value) {
        mIntent.putExtra(SysEnv.ACTIVITY_DTO_KEY, value);
    }

    /**
     * 设置传递参数
     *
     * @param key   参数key
     * @param value 数据传输对象
     */
    public void addParameter(String key, DTO value) {
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
    public DTO getParameters() {
        DTO parms = (DTO) mContext.getIntent().getExtras().getSerializable(SysEnv.ACTIVITY_DTO_KEY);
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
    @SuppressLint("ResourceAsColor")
    public MaterialDialog showCustomerDialog(String content, int view,boolean wrapInScrollView) {
        dismissAlert();
        //是否允许自定义布局滑动
//        boolean wrapInScrollView = false;
        return dialog = new MaterialDialog.Builder(mContext)
//                .content(content)
//                .positiveText(R.string.ok)
//                .negativeText(R.string.cancel)
                .autoDismiss(false)
                .customView(view, wrapInScrollView)
                .backgroundColor(R.color.transparent)
                .show();
    }

    //apply fail
    @SuppressLint("ResourceAsColor")
    public MaterialDialog showCustomerDialog(String content, int view, MaterialDialog.SingleButtonCallback okCallBack) {
        dismissAlert();
        //是否允许自定义布局滑动
        boolean wrapInScrollView = false;
        return dialog = new MaterialDialog.Builder(mContext)
//                .content(content)
//                .positiveText(R.string.ok)
//                .negativeText(R.string.cancel)
                .customView(view, wrapInScrollView)
                .backgroundColor(R.color.transparent)
                .show();
    }

    public void showBasicDialog(String content, MaterialDialog.SingleButtonCallback okCallBack) {
        dismissAlert();
        dialog = new MaterialDialog.Builder(mContext)
                .content(content)
                .positiveText(R.string.ok)
                .negativeText(R.string.cancel)
                .onPositive(okCallBack)
                .show();
    }
    public void showBasicDialog(String content) {
        dismissAlert();
        dialog = new MaterialDialog.Builder(mContext)
                .content(content)
                .positiveText(R.string.ok)
                .show();
    }
    public void showSingleBtnDialog(String content, String postext, MaterialDialog.SingleButtonCallback okCallBack) {
        dismissAlert();
        dialog = new MaterialDialog.Builder(mContext)
                .content(content)
                .positiveText(postext)
                .onPositive(okCallBack)
                .show();
    }

    public void showBasicDialog(int content, MaterialDialog.SingleButtonCallback okCallBack) {
        dismissAlert();
        dialog = new MaterialDialog.Builder(mContext)
                .content(mContext.getResources().getString(content))
                .positiveText(R.string.ok)
                .negativeText(R.string.cancel)
                .onPositive(okCallBack)
                .show();
    }

    public void showBasicDialog(int title, int content, MaterialDialog.SingleButtonCallback singleButtonCallback) {
        dismissAlert();
        dialog = new MaterialDialog.Builder(mContext)
                .title(mContext.getResources().getString(title))
                .content(mContext.getResources().getString(content))
                .positiveText(R.string.ok)
                .canceledOnTouchOutside(false)
                .negativeText(R.string.cancel)
                .onPositive(singleButtonCallback)
                .show();
    }

    public void showBasicDialog(String title, String content, MaterialDialog.SingleButtonCallback singleButtonCallback) {
        dismissAlert();
        dialog = new MaterialDialog.Builder(mContext)
                .title(title)
                .content(content)
                .positiveText(R.string.ok)
                .negativeText(R.string.cancel)
                .canceledOnTouchOutside(false)
                .onAny(singleButtonCallback)
                .show();
    }

    public void showBasicDialog(String title, String content, String positiveText, String negativeText , MaterialDialog.SingleButtonCallback singleButtonCallback) {
        dismissAlert();
        dialog = new MaterialDialog.Builder(mContext)
                .title(title)
                .content(content)
                .positiveText(positiveText)
                .negativeText(negativeText)
                .onPositive(singleButtonCallback)
                .show();
    }

    public void showInputDialog(String title, String content, String hint, int inputMix, int inputMax, MaterialDialog.InputCallback inputCallback) {
        dismissAlert();
        dialog = new MaterialDialog.Builder(mContext)
                .title(title)
                .content(content)
                .inputType(InputType.TYPE_CLASS_TEXT |
                        InputType.TYPE_TEXT_VARIATION_PERSON_NAME |
                        InputType.TYPE_TEXT_FLAG_CAP_WORDS)
                .inputRange(inputMix, inputMax)
                .positiveText(R.string.ok)
                .negativeText(R.string.cancel)
                .input(hint, hint, false, inputCallback).show();
    }

    public void showInputDialog(String content, String hint,String text, MaterialDialog.InputCallback inputCallBack) {
        dismissAlert();
        dialog = new MaterialDialog.Builder(mContext)
                .content(content)
                .inputType(InputType.TYPE_CLASS_TEXT |
                        InputType.TYPE_TEXT_VARIATION_PERSON_NAME |
                        InputType.TYPE_TEXT_FLAG_CAP_WORDS)
                .positiveText(R.string.ok)
                .negativeText(R.string.cancel)
                .input(hint, text, false, inputCallBack).show();
    }



    public void showInputDialog(String title, String content, String hint, String positiveText, String negativeText, int inputMix, int inputMax) {
        dismissAlert();
        dialog = new MaterialDialog.Builder(mContext)
                .title(title)
                .content(content)
                .inputType(InputType.TYPE_CLASS_TEXT |
                        InputType.TYPE_TEXT_VARIATION_PERSON_NAME |
                        InputType.TYPE_TEXT_FLAG_CAP_WORDS)
                .inputRange(inputMix, inputMax)
                .positiveText(positiveText)
                .negativeText(negativeText)
                .input(hint, hint, false, new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                    }
                }).show();
    }

    public void showInputDialog(String title, String content, String hint, String defaultText, String positiveText, String negativeText, MaterialDialog.InputCallback inputCallback) {
        dismissAlert();
        dialog = new MaterialDialog.Builder(mContext)
                .title(title)
                .content(content)
                .inputType(InputType.TYPE_CLASS_TEXT |
                        InputType.TYPE_TEXT_VARIATION_PERSON_NAME |
                        InputType.TYPE_TEXT_FLAG_CAP_WORDS)
                .positiveText(positiveText)
                .negativeText(negativeText)
                .input(hint, hint, false, inputCallback).build();
        dialog.getInputEditText().setText(defaultText);
        dialog.show();
    }

    public void showInputDialog(String title, String content, String hint, String defaultText, String positiveText, String negativeText, MaterialDialog.InputCallback inputCallback, TextWatcher textWatcher) {
        dismissAlert();
        dialog = new MaterialDialog.Builder(mContext)
                .title(title)
                .content(content)
                .inputType(InputType.TYPE_CLASS_TEXT |
                        InputType.TYPE_TEXT_VARIATION_PERSON_NAME |
                        InputType.TYPE_TEXT_FLAG_CAP_WORDS)
                .positiveText(positiveText)
                .negativeText(negativeText)
                .input(hint, hint, false, inputCallback).build();
        dialog.getInputEditText().setText(defaultText);
        dialog.getInputEditText().addTextChangedListener(textWatcher);
        dialog.show();
    }

    public void showInputDialog(String title, String content, String hint, String defaultText, String positiveText, String negativeText, int inputMix, int inputMax, MaterialDialog.InputCallback inputCallback) {
        dismissAlert();
        dialog = new MaterialDialog.Builder(mContext)
                .title(title)
                .content(content)
                .inputType(InputType.TYPE_CLASS_TEXT |
                        InputType.TYPE_TEXT_VARIATION_PERSON_NAME |
                        InputType.TYPE_TEXT_FLAG_CAP_WORDS)
                .inputRange(inputMix, inputMax)
                .positiveText(positiveText)
                .negativeText(negativeText)
                .input(hint, hint, false, inputCallback).build();
        dialog.getInputEditText().setText(defaultText);
        dialog.show();
    }

    public void showProgress(String context) {
        dismissAlert();
        dialog = new MaterialDialog.Builder(mContext)
                .cancelable(true)
                .content(context)
                .progress(true, 0)
                .progressIndeterminateStyle(false)
                .show();
    }

    public void showProgress(String context, boolean cacanle) {
        dismissAlert();
        dialog = new MaterialDialog.Builder(mContext)
                .cancelable(cacanle)
                .content(context)
                .progress(true, 0)
                .progressIndeterminateStyle(false)
                .show();
    }

    public void showProgressIndeterminateDialog(String content) {
        showIndeterminateProgressDialog(false, content);
    }

    public void showProgressIndeterminateDialog(String title, String content) {
        showIndeterminateProgressDialog(false, title, content, false);
    }

    public void showProgressHorizontalIndeterminateDialog(String content) {
        showIndeterminateProgressDialog(true, content);
    }

    public void dissMissDialog() {
        if (null != dialog && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    public void showProgressHorizontalIndeterminateDialog(String title, String content) {
        showIndeterminateProgressDialog(true, title, content, false);
    }

    public void showProgressHorizontalIndeterminateDialog(String content, boolean canCancel) {
        showIndeterminateProgressDialog(true, content, false);
    }

    public void showProgressHorizontalIndeterminateDialog(String title, String content, boolean canCancel) {
        showIndeterminateProgressDialog(true, title, content, canCancel);
    }

    private void showIndeterminateProgressDialog(boolean horizontal, String content) {
        dismissAlert();
        dialog = new MaterialDialog.Builder(mContext)
                .content(content)
                .progress(true, 0)
                .progressIndeterminateStyle(horizontal)
                .show();
    }

    private void showIndeterminateProgressDialog(boolean horizontal, String content, boolean cancancel) {
        dismissAlert();
        dialog = new MaterialDialog.Builder(mContext)
                .cancelable(cancancel)
                .content(content)
                .progress(true, 0)
                .progressIndeterminateStyle(horizontal)
                .show();
    }

    private void showIndeterminateProgressDialog(boolean horizontal, String title, String content, boolean cancancel) {
        dismissAlert();
        dialog = new MaterialDialog.Builder(mContext)
                .title(title)
                .cancelable(cancancel)
                .content(content)
                .progress(true, 0)
                .progressIndeterminateStyle(horizontal)
                .show();
    }

    private void dismissAlert() {
        if (!mContext.isFinishing() && dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    public MaterialDialog.Builder getMaterialDialog() {
        if (!mContext.isFinishing() && dialog != null && dialog.isShowing())
            dialog.dismiss();
        return new MaterialDialog.Builder(mContext);
    }
}
