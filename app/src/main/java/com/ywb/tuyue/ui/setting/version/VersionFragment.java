package com.ywb.tuyue.ui.setting.version;


import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.ta.utdid2.android.utils.SystemUtils;
import com.ywb.tuyue.BuildConfig;
import com.ywb.tuyue.R;
import com.ywb.tuyue.constants.Constants;
import com.ywb.tuyue.entity.TVersion;
import com.ywb.tuyue.ui.mvp.BaseFragmentV4;

import java.io.File;

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
    int appVersionCode;

    @Override
    public void startLoading() {
        mOperation.showProgress("正在下载...", false);
    }

    @Override
    public void endLoading() {
        mOperation.dissMissDialog();
    }

    @Override
    public void onError(String error) {
        ToastUtils.showShort(error);
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
        appVersionCode = AppUtils.getAppVersionCode();
        tvCurrentCode.setText(appVersionCode + "");

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
        this.tVersion = tVersion;
        //如果线上版本高于当前版本
        if (Integer.parseInt(tVersion.getVersioncode()) > appVersionCode) {
            llUpdate.setVisibility(View.VISIBLE);
            tvUpdateCode.setText(tVersion.getVersioncode());
        }
    }

    @Override
    public void downloadApkSuccess(String filepath) {

        File file = new File(filepath);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        //判断是否是AndroidN以及更高的版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(getActivity(),
                    BuildConfig.APPLICATION_ID + ".fileProvider", file);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        startActivity(intent);

    }

    @OnClick(R.id.tv_update)
    public void onViewClicked() {
        mOperation.showBasicDialog("版本升级",
                "检测到最新版本" + tVersion.getVersioncode() + "，更新内容：" + tVersion.getContent() + "，是否更新？",
                "更新",
                "以后再说",
                (dialog, which) -> {
                    presenter.downloadApk(tVersion);
                });
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
