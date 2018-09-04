package com.ywb.tuyue.ui.unlock;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.ywb.tuyue.R;
import com.ywb.tuyue.constants.Constants;
import com.ywb.tuyue.entity.TAdvert;
import com.ywb.tuyue.entity.TStats;
import com.ywb.tuyue.ui.advert.AdvertContract;
import com.ywb.tuyue.ui.advert.AdvertPresenter;
import com.ywb.tuyue.ui.data.DataContract;
import com.ywb.tuyue.ui.data.DataPresenter;
import com.ywb.tuyue.ui.mvp.BaseActivity;
import com.ywb.tuyue.ui.splash.SplashActivity;
import com.ywb.tuyue.utils.DeviceUtils;
import com.ywb.tuyue.utils.GlideUtils;
import com.ywb.tuyue.widget.CustomerUnlockView;

import org.litepal.LitePal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

import static com.ywb.tuyue.constants.Constants.IS_MOBILE;
import static com.ywb.tuyue.constants.Constants.NETWORK_AVAILABLE;

public class UnlockActivity extends BaseActivity implements AdvertContract.View, DataContract.View, UnlockContract.UnlockView {

    @Inject
    AdvertPresenter advertPresenter;
    @Inject
    DataPresenter dataPresenter;
    @Inject
    UnlockPresenter presenter;

    @BindView(R.id.unlock_bg_advert)
    ImageView unlockBgAdvert;
    @BindView(R.id.unlock_view)
    CustomerUnlockView unlockView;


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
        return R.layout.activity_unlock;
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public void initView(View view) {
        BarUtils.setStatusBarAlpha(this, 0);
        advertPresenter.attachView(this);
        dataPresenter.attachView(this);
        presenter.attachView(this);

        unlockView.setOnLockListener(isLocked -> {
            if (isLocked) {
                //判断网络是否可用
                if (NetworkUtils.isAvailableByPing()) {
                    SPUtils.getInstance().put(NETWORK_AVAILABLE, true);
                    LogUtils.e("当前网络可用");
                    //判断是否为wifi网络
//                    if (NetworkUtils.getNetworkType() != NetworkUtils.NetworkType.NETWORK_WIFI) {
                    if (NetworkUtils.getNetworkOperatorName() != null) {
                        LogUtils.e("当前网络是4g");
                        SPUtils.getInstance().put(IS_MOBILE, true);
                    }
                } else {
                    SPUtils.getInstance().put(NETWORK_AVAILABLE, true);
                    SPUtils.getInstance().put(IS_MOBILE, false);
                }
                String phone = SPUtils.getInstance().getString(Constants.REGIST_PHONE, "");
                //判断这里是否存在用户，如果存在则要记录本次解锁数据
                if (!StringUtils.isEmpty(phone)) {
                    //这里调用初始化litepal
                    SQLiteDatabase db = LitePal.getDatabase();
                    //判断该手机号是否创建过统计数据——有数据则解锁+1
                    TStats tOpen = LitePal.where("phone = ?", phone + "").order("id desc").findFirst(TStats.class);
                    if (tOpen != null) {
                        tOpen.setOpenlock(tOpen.getOpenlock() + 1);
                        boolean result = tOpen.save();
                        //判断当前网络可用且用户数据保存成功
                        if ( result) {
                            //上传所有数据
                            dataPresenter.uploadData(tOpen);
                        }
                    }
                }

                mOperation.forwardAndFinish(SplashActivity.class);
            }
        });
    }


    @Override
    public void doBusiness(Context mContext) {
        advertPresenter.getAdvertListByType(1);
    }

    @Override
    public void getAdvertListSuccess(List<TAdvert> list) {
        if (list.size() > 0) {
            //这里只选取最新的1张
            GlideUtils.loadImageView(this,
                    list.get(0).getDownloadPic(), unlockBgAdvert);
        }

    }

    @Override
    public void initInjector() {
        getComponent().inject(this);
    }


    @Override
    public void uploadDataSuccess() {
        LogUtils.e("数据上传成功");
    }
}
