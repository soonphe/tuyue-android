package com.ywb.tuyue.ui.setting.gaindata;


import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.ywb.tuyue.R;
import com.ywb.tuyue.constants.Constants;
import com.ywb.tuyue.entity.TAdvert;
import com.ywb.tuyue.entity.TDataVersion;
import com.ywb.tuyue.ui.mvp.BaseFragmentV4;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Author soonphe
 * @Date 2018-08-22 11:08
 * @Description 设置-数据同步
 */
public class GainDataFragment extends BaseFragmentV4 implements GainDataContract.View {

    @Inject
    GainDataPresenter presenter;

    @BindView(R.id.tv_dataVersion)
    TextView tvDataVersion;
    @BindView(R.id.tv_advertVersion)
    TextView tvAdvertVersion;
    @BindView(R.id.syncData)
    TextView syncData;

    int advertVersion = 0;  //SP广告版本
    int dataVersion = 0;    //SP数据版本
    TDataVersion dbVersion; //本地数据库最新数据版本

    @Override
    public void startLoading() {
        mOperation.showProgress("正在更新数据...", false);
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
        return R.layout.fragment_data_syn;
    }

    @Override
    public void initParams(Bundle params) {

    }

    @Override
    public void initView(View view) {

        presenter.attachView(this);

        tvDataVersion.setText(SPUtils.getInstance().getInt(Constants.DATA_VERSION, 1) + "");
        tvAdvertVersion.setText(SPUtils.getInstance().getInt(Constants.ADVERT_VERSION, 1) + "");
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

    @OnClick({R.id.syncData})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.syncData:
                presenter.getDataVersion();
                break;
        }
    }

    @Override
    public void getDataVersionSuccess(TDataVersion tDataVersion) {

//        dbVersion = LitePal.order("id desc").limit(1).findFirst(TDataVersion.class);
        dbVersion = tDataVersion;
        dataVersion = SPUtils.getInstance().getInt(Constants.DATA_VERSION, 0);
        advertVersion = SPUtils.getInstance().getInt(Constants.ADVERT_VERSION, 0);
        //判断数据版本和广告版本是否一致
        if (tDataVersion.getAdvertversion() == advertVersion && tDataVersion.getDataversion() == dataVersion) {
            LogUtils.e("当前已经是最新数据");
            ToastUtils.showShort("当前已经是最新数据");
        } else {
            //分别判断更新广告版本和数据版本
            if (tDataVersion.getAdvertversion() > advertVersion) {
                LogUtils.e("准备更新广告版本");
                presenter.getAdvertList();
            }
            if (tDataVersion.getDataversion() > dataVersion) {
                LogUtils.e("准备更新数据版本");
                presenter.getOrtherData();
            }
        }


    }

    @Override
    public void getAdvertSuccess(List<TAdvert> tAdvertList) {
        //SP更新广告版本号
        SPUtils.getInstance().put(Constants.ADVERT_VERSION, dbVersion.getAdvertversion());

        ToastUtils.showShort("广告数据更新成功");
    }

    @Override
    public void getOtherDataSuccess() {
        //SP更新数据版本号
        SPUtils.getInstance().put(Constants.DATA_VERSION, dbVersion.getDataversion());

        LogUtils.e("其他数据同步成功");

    }
}
