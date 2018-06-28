package com.ywb.tuyue.ui.web;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.ywb.tuyue.R;
import com.ywb.tuyue.ui.mvp.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wushange on 2017/8/27.
 */

public class WebviewActivity extends BaseActivity {
    @BindView(R.id.fl_web)
    FrameLayout flWeb;

    @Override
    public int bindLayout() {
        return R.layout.activity_web;
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public void initInjector() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
