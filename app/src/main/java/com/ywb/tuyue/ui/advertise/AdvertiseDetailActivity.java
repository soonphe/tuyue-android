package com.ywb.tuyue.ui.advertise;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.ywb.tuyue.R;
import com.ywb.tuyue.ui.main.MainActivity;
import com.ywb.tuyue.ui.mvp.BaseActivity;
import com.ywb.tuyue.widget.head.HeaderView;


import butterknife.BindView;


/**
 * Description:所有广告详情页
 * Created by wcystart on 2018/6/25.
 */
public class AdvertiseDetailActivity extends BaseActivity {


//    @BindView(R.id.ad_webview)
//    WebView mAdWebview;
    @BindView(R.id.header)
    HeaderView mHeader;


    @Override
    public int bindLayout() {
        return R.layout.activity_advertise_detail;
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public void initView(View view) {
        mHeader.setTitle("广告一");
        mHeader.setRightBtnVisiable(View.GONE);
        mHeader.setLeftBtnClickListsner(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOperation.forwardAndFinish(MainActivity.class,LEFT_RIGHT);
            }
        });
    }

    @Override
    public void doBusiness(Context mContext) {

    }


    @Override
    public void initInjector() {

    }
}
