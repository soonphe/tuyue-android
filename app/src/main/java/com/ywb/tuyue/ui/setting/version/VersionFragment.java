package com.ywb.tuyue.ui.setting.version;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.blankj.utilcode.util.SPUtils;
import com.ywb.tuyue.R;
import com.ywb.tuyue.constants.Constants;
import com.ywb.tuyue.entity.TVersion;
import com.ywb.tuyue.ui.mvp.BaseFragmentV4;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.ywb.tuyue.constants.Constants.NETWORK_AVAILABLE;


/**
 * @Author soonphe
 * @Date 2018-08-22 11:01
 * @Description 设置-版本
 */
public class VersionFragment extends BaseFragmentV4 implements VersionContract.View {


    @Inject
    VersionPresenter presenter;

    @BindView(R.id.tv_update_code)
    TextView tvUpdateCode;
    @BindView(R.id.tv_update)
    TextView tvUpdate;
    @BindView(R.id.ll_update)
    LinearLayout llUpdate;
    @BindView(R.id.tv_current_code)
    TextView tvCurrentCode;

    TVersion tVersion;

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
        presenter.attachView(this);

    }

    @Override
    public void doBusiness(Context mContext) {
        //获取版本最新信息
        if (SPUtils.getInstance().getBoolean(NETWORK_AVAILABLE)) {
            presenter.getVersion();
        }
    }

    @Override
    public void getVersionSuccess(TVersion tVersion) {
        this.tVersion=tVersion;
        String versionCode = SPUtils.getInstance().getString(Constants.APK_VERSION, "0");
        //如果线上版本高于当前版本
        if (Integer.parseInt(tVersion.getVersioncode()) > Integer.parseInt(versionCode)) {
            llUpdate.setVisibility(View.VISIBLE);
            tvUpdateCode.setText(tVersion.getVersioncode());
            //SP版本信息同步
//            SPUtils.getInstance().put(Constants.APK_VERSION, tVersion.getVersioncode());
        }
    }

    @Override
    public void downloadApkSuccess() {

    }

    @OnClick(R.id.tv_update)
    public void onViewClicked() {
        mOperation.showBasicDialog("版本升级",
                "检测到最新版本" + tVersion.getVersioncode() + "，更新内容：" + tVersion.getContent() + "，是否更新？",
                "更新",
                "以后再说",
                (MaterialDialog.SingleButtonCallback) (dialog, which) -> {
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);   //跳转位置服务界面
                    startActivity(intent);
                });
//        new MaterialDialog.Builder(this.mContext)
//                .content("检测到最新版本" + mSystemVersion.getVersioncode() +
//                        "，更新内容：" + mSystemVersion.getContent() + "，是否更新？")
//                .positiveText("更新")
//                .negativeText("以后再说")
//                .onPositive((dialog1, which) -> {
//                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);   //跳转位置服务界面
//                    startActivity(intent);
//                })
//                .show();

    }

    @Override
    public void lazyInitBusiness(Context mContext) {

    }

    @Override
    public void initInjector() {
        getComponent().inject(this);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
