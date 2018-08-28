package com.ywb.tuyue.ui.setting.version;


import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.ywb.tuyue.R;
import com.ywb.tuyue.ui.mvp.BaseFragmentV4;


/**
 * @Author AboutUsFragment
 * @Date 2018-08-22 11:52
 * @Description 设置-版本
 */
public class VersionFragment extends BaseFragmentV4 implements VersionContract.View {

    @Override
    public void startLoading() {

    }

    @Override
    public void endLoading() {

    }

    @Override
    public void onError(String error) {

    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_apk_version;
    }

    @Override
    public void initParams(Bundle params) {

    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public void lazyInitBusiness(Context mContext) {

    }

    @Override
    public void initInjector() {
        getComponent().inject(this);
    }
}
