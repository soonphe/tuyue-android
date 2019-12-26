package com.ywb.tuyue.base;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

/**
 * @Author soonphe
 * @Date 2017-12-01 15:13
 * @Description IBaseFragment
 */
public interface IBaseFragment extends IBaseConstant {

    /**
     * 绑定渲染视图View（onCreate方法中调用）
     *
     * @return 布局文件资源id
     */
    public int bindLayout();

    /**
     * 绑定渲染视图View（onCreate方法中调用）
     *
     * @return
     */
    public View bindView();

    /**
     * 初始化界面参数
     */
    public void initParams(Bundle params);

    /**
     * 初始化控件
     */
    public void initView(final View view);

    /**
     * 业务处理操作（onCreateView方法中调用）
     *
     * @param mContext 当前Activity对象
     */
    public void doBusiness(Context mContext);

    /**
     * 懒加载业务
     *
     * @param mContext 当前Activity对象
     */
    public void lazyInitBusiness(Context mContext);
}
