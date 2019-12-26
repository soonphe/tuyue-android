package com.ywb.tuyue.ui.setting.hotspot;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.KeyboardUtils;
import com.ywb.tuyue.R;
import com.ywb.tuyue.constants.Constants;
import com.ywb.tuyue.services.hotspot.ServiceUtil;
import com.ywb.tuyue.base.BaseFragmentV4;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @Author soonphe
 * @Date 2018-08-22 11:01
 * @Description 设置-热点设置
 */
public class HotspotFragment extends BaseFragmentV4 implements HotspotContract.View {

    @BindView(R.id.setHotPassword)
    EditText setHotPassword;
    @BindView(R.id.jumpSetting)
    TextView jumpSetting;
    @BindView(R.id.hot_spot_rootView)
    LinearLayout hotSpotRootView;

    @Override
    public int bindLayout() {
        return R.layout.fragment_hot_spot;
    }

    @Override
    public void initParams(Bundle params) {

    }

    @Override
    public void initView(View view) {
    }

    @OnClick({R.id.jumpSetting,R.id.hot_spot_rootView})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.jumpSetting:
                String inputPassword = setHotPassword.getText().toString().trim();
                if (TextUtils.isEmpty(inputPassword)) {
                    Toast.makeText(getContext(), "请输入密码！", Toast.LENGTH_SHORT).show();// //218069
                    return;
                }
                if (!inputPassword.equals(Constants.ADMIN)) {
                    Toast.makeText(getContext(), "密码不正确！", Toast.LENGTH_SHORT).show();
                    return;
                }
                //进入设置界面
                startSetting();
                //启动返回按钮service
                startService();
                break;
            case R.id.hot_spot_rootView:
                //隐藏软键盘
                KeyboardUtils.hideSoftInput(this.getContext());
                break;
        }
    }

    /**
     * 启动service
     */
    private void startService() {

        ServiceUtil.startService(getContext());
    }

    /**
     * 进入设置界面
     */
    public void startSetting() {
        startActivity(new Intent(Settings.ACTION_SETTINGS));
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

    @Override
    public void startLoading() {

    }

    @Override
    public void endLoading() {

    }

    @Override
    public void onError(String error) {

    }



}
