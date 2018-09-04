package com.ywb.tuyue.ui.setting.hotspot;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ywb.tuyue.R;
import com.ywb.tuyue.constants.Constants;
import com.ywb.tuyue.services.hotspot.ServiceUtil;
import com.ywb.tuyue.ui.mvp.BaseFragmentV4;


/**
 * @Author soonphe
 * @Date 2018-08-22 11:01
 * @Description 设置-热点设置
 */
public class HotspotFragment extends BaseFragmentV4 implements HotspotContract.View {
    EditText mSetHotPassword;
    RelativeLayout mRootView;
    TextView mJumpSettings;

    @Override
    public int bindLayout() {
        return R.layout.fragment_hot_spot;
    }

    @Override
    public void initParams(Bundle params) {

    }

    @Override
    public void initView(View view) {
        mSetHotPassword= (EditText) findViewById(R.id.setHotPassword);
        mRootView= (RelativeLayout) findViewById(R.id.hot_spot_rootView);
        mJumpSettings= (TextView) findViewById(R.id.jumpSetting);

        //输入密码进入系统设置界面   给系统设置界面添加一个返回键
        mJumpSettings.setOnClickListener(v -> {
            String inputPassword = mSetHotPassword.getText().toString().trim();
            if (TextUtils.isEmpty(inputPassword)) {
                Toast.makeText(getContext(), "请输入密码！", Toast.LENGTH_SHORT).show();// //218069
                return;
            }
            if (!inputPassword.equals(Constants.ADMIN)) {
                Toast.makeText(getContext(), "密码不正确！", Toast.LENGTH_SHORT).show();
                return;
            }
            startSetting();
            startService();
        });

        mRootView.setOnClickListener(v -> {
            dismiss(mRootView);
        });
    }

    //隐藏键盘
    public void dismiss(View view) {
        try {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            // 强制隐藏软键盘
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }catch (Exception e){
            e.printStackTrace();
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
