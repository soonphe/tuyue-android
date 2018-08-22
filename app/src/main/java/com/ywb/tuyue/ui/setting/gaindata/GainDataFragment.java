package com.ywb.tuyue.ui.setting.gaindata;


import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
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

    int advertVersion = 0;  //广告版本
    int dataVersion = 0;    //数据版本


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
        //存储数据版本与广告版本表
        tDataVersion.save();
        dataVersion = SPUtils.getInstance().getInt(Constants.DATA_VERSION, 0);
        advertVersion = SPUtils.getInstance().getInt(Constants.ADVERT_VERSION, 0);
        //判断数据版本和广告版本是否一致
        if (tDataVersion.getAdvertversion() > advertVersion) {
            presenter.getAdvertList();
        }
//        if (tDataVersion.getDataversion() > dataVersion) {
//            LogUtils.e("___数据版本大于当前版本");
////            presenter.getDataVersion();
//        }
    }

    @Override
    public void getAdvertSuccess(List<TAdvert> tAdvertList) {
        //更新广告版本号
//        SPUtils.getInstance().put(Constants.DATA_VERSION, advertVersion);
        for (TAdvert tAdvert :
                tAdvertList) {
            if (!tAdvert.save()) {
                LogUtils.e("广告数据保存失败" + tAdvert.getId() + tAdvert.getTitle());
            } else {
                LogUtils.e("广告数据保存成功" + tAdvert.getId() + tAdvert.getTitle());
            }
        }
    }
}
