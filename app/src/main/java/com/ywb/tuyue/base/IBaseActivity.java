package com.ywb.tuyue.base;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

/**
 * @Author soonphe
 * @Date 2017-12-01 15:13
 * @Description IBaseActivity
 */
public interface IBaseActivity extends IBaseConstant {

    /**
     * 在setContentView之前的一些window配置
     *
     * @param savedInstanceState
     */
    public void config(Bundle savedInstanceState);

    /**
     * 绑定渲染视图View（onCreate方法中调用）
     *
     * @return 布局文件资源id
     */
    public int bindLayout();

    /**
     * 绑定渲染View
     *
     * @return
     */
    public View bindView();

    /**
     * 初始化界面参数
     *
     * @param parms
     */
    public void initParms(Bundle parms);

    /**
     * 初始化控件
     */
    public void initView(final View view);

    /**
     * 业务处理操作（onCreate方法中调用）
     *
     * @param mContext 当前Activity对象
     */
    public void doBusiness(Context mContext);

    /**
     * 暂停恢复刷新相关操作（onResume方法中调用）
     */
    public void resume();

    /**
     * 销毁、释放资源相关操作（onDestroy方法中调用）
     */
    public void destroy();

}
