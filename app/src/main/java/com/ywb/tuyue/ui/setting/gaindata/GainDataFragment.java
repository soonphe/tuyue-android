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
import com.ywb.tuyue.constants.enums.DownloadEventEnum;
import com.ywb.tuyue.entity.TAdvert;
import com.ywb.tuyue.entity.TDataVersion;
import com.ywb.tuyue.base.BaseFragmentV4;
import com.ywb.tuyue.ui.setting.download.DownloadAllActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

import static com.ywb.tuyue.constants.Constants.NETWORK_AVAILABLE;

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
    @BindView(R.id.syncMovie)
    TextView syncMovie;
    @BindView(R.id.downAll)
    TextView downAll;
    @BindView(R.id.uploadData)
    TextView uploadData;

    int apkVersion = 0;  //APK广告版本
    int advertVersion = 0;  //SP广告版本
    int dataVersion = 0;    //SP数据版本
    TDataVersion dbVersion; //本地数据库最新数据版本

    @Override
    public void startLoading() {
        mOperation.showProgress("正在更新数据...", true);
    }

    @Override
    public void endLoading() {
        mOperation.dismissDialog();
    }

    @Override
    public void onError(String error) {
        LogUtils.e(error + "");
//        ToastUtils.showShort(error);
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

    @OnClick({R.id.syncData, R.id.syncMovie, R.id.downAll, R.id.uploadData})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.syncData:
                //判断当前网络可用
                if (SPUtils.getInstance().getBoolean(NETWORK_AVAILABLE)) {
                    presenter.getDataVersion();
                } else {
                    ToastUtils.showShort("请检查网络是否连接");
                }
                break;
            case R.id.syncMovie:
                //判断当前网络可用
//                if (SPUtils.getInstance().getBoolean(NETWORK_AVAILABLE)) {
//                    presenter.getMovieData();
//                } else {
//                    ToastUtils.showShort("请检查网络是否连接");
//                }
                break;
            case R.id.downAll:
                mOperation.forward(DownloadAllActivity.class);
                break;
            case R.id.uploadData:
                //点击上传数据库数据
                if (SPUtils.getInstance().getBoolean(NETWORK_AVAILABLE)) {
                    //先后上传用户数据与统计数据
                    presenter.uploadUserData();

//                    TStats stats = new TStats();
//                    stats.setPhone("ceshi1");
//                    stats.setRegtime("2018-09-04 14:56:50");
//                    stats.setCreatedate("2018-09-04");
//                    stats.setIsmobile(SPUtils.getInstance().getBoolean(IS_MOBILE));
//                    stats.setImcode(DeviceUtils.getUniqueId(getContext()) + "");
//                    stats.save();
//
//                    TStats stats1 = new TStats();
//                    stats1.setPhone("ceshi1");
//                    stats1.setRegtime("2018-09-05 14:56:50");
//                    stats1.setCreatedate("2018-09-05");
//                    stats1.setIsmobile(SPUtils.getInstance().getBoolean(IS_MOBILE));
//                    stats1.setImcode(DeviceUtils.getUniqueId(getContext()) + "");
//                    stats1.setBook(100);
//                    stats1.save();
//
//                    TStats stats12 = new TStats();
//                    stats12.setPhone("ceshi1");
//                    stats12.setRegtime("2018-09-05 16:56:50");
//                    stats12.setCreatedate("2018-09-05");
//                    stats12.setIsmobile(SPUtils.getInstance().getBoolean(IS_MOBILE));
//                    stats12.setImcode(DeviceUtils.getUniqueId(getContext()) + "");
//                    stats12.setBook(200);
//                    stats12.save();
                    presenter.uploadStatsData();
                } else {
                    ToastUtils.showShort("请检查网络是否连接");
                }
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
            //这里应该弹出最新数据的提示
            presenter.getAdvertList();
            presenter.getOtherData();
        } else {
            //分别判断更新广告版本和数据版本
            if (tDataVersion.getAdvertversion() > advertVersion) {
                LogUtils.e("更新广告版本");
                presenter.getAdvertList();
            }
            if (tDataVersion.getDataversion() > dataVersion) {
                LogUtils.e("更新数据版本");
                presenter.getOtherData();
            }
        }
//        //获取1905数据
        presenter.getMovieData();

    }

    @Override
    public void getAdvertSuccess(List<TAdvert> tAdvertList) {
        //SP更新广告版本号
        SPUtils.getInstance().put(Constants.ADVERT_VERSION, dbVersion.getAdvertversion());

        LogUtils.e("广告数据更新成功");
    }

    @Override
    public void getOtherDataSuccess() {
        //SP更新数据版本号
        SPUtils.getInstance().put(Constants.DATA_VERSION, dbVersion.getDataversion());
        LogUtils.e("其他数据更新成功");
    }

    @Override
    public void getMovieDataSuccess() {
        LogUtils.e("电影数据更新成功");
    }

    @Override
    public void uploadUserDataSuccess() {

        LogUtils.e("用户数据上传成功");
    }

    @Override
    public void uploadStatsDataSuccess() {
        ToastUtils.showShort("统计数据上传成功");
        LogUtils.e("统计数据上传成功");
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


    @Subscribe
    public void finishDownload(DownloadEventEnum commonEvent) {
        LogUtils.e("接收到单个完成下载eventBus事件");
        if (commonEvent == DownloadEventEnum.FINISH_DOWNLOAD) {
            //结束加载动画
//            if (mOperation.getDialog() != null) {
//                endLoading();
//            }
        }
    }

    @Subscribe
    public void finishAll(DownloadEventEnum commonEvent) {
        LogUtils.e("接收到全部完成下载eventBus事件");
    }
}
