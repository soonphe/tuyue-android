package com.ywb.tuyue.ui.splash;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.bumptech.glide.Glide;
import com.ywb.tuyue.R;
import com.ywb.tuyue.constants.Constants;
import com.ywb.tuyue.entity.TAdvert;
import com.ywb.tuyue.entity.TStats;
import com.ywb.tuyue.ui.advert.AdvertContract;
import com.ywb.tuyue.ui.advert.AdvertPresenter;
import com.ywb.tuyue.ui.data.DataContract;
import com.ywb.tuyue.ui.data.DataPresenter;
import com.ywb.tuyue.ui.main.MainActivity;
import com.ywb.tuyue.ui.mvp.BaseActivity;
import com.ywb.tuyue.utils.GlideUtils;

import org.litepal.LitePal;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

import static com.ywb.tuyue.constants.Constants.IS_MOBILE;
import static com.ywb.tuyue.constants.Constants.NETWORK_AVAILABLE;

/**
 * @Author soonphe
 * @Date 2018-08-30 10:56
 * @Description flash闪屏页
 */
public class SplashActivity extends BaseActivity implements AdvertContract.View, DataContract.View {

    @Inject
    AdvertPresenter advertPresenter;
    @Inject
    DataPresenter dataPresenter;

    @BindView(R.id.iv_flash)
    ImageView ivFlash;
    @BindView(R.id.splash_countdown)
    TextView splashCountdown;

    Timer timer = new Timer();
    int currentSecond = 4;
    int minTime = 0;


    @Override
    public int bindLayout() {
        return R.layout.activity_splash;
    }

    @Override
    public void initParms(Bundle parms) {
    }

    @Override
    public void initView(View view) {

        BarUtils.setStatusBarAlpha(this, 0);
        advertPresenter.attachView(this);
        dataPresenter.attachView(this);

        Glide.with(this).load(R.mipmap.home_start).into(ivFlash);

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
    }

    @Override
    public void doBusiness(Context mContext) {
        advertPresenter.getAdvertListByType(2);
    }

    @Override
    public void getAdvertListSuccess(List<TAdvert> list) {
        if (list.size() > 0) {
            //这里只选取最新的1张
            GlideUtils.loadImageViewLoding(this,
                    list.get(0).getDownloadPic(), ivFlash, R.mipmap.home_start, R.mipmap.home_start);
        }

        //判断这里是否存在用户，如果存在则要记录本次解锁数据
        String phone = SPUtils.getInstance().getString(Constants.REGIST_PHONE, "");
        if (!StringUtils.isEmpty(phone)) {
            //判断该手机号是否创建过统计数据——有数据则解锁+1
            TStats tOpen = LitePal.where("phone = ?", phone + "").order("id desc").findFirst(TStats.class);
            if (tOpen != null) {
                tOpen.setOpenlock(tOpen.getOpenlock() + 1);
                boolean result = tOpen.save();
                if (result) {
                    //上传所有数据
                    dataPresenter.uploadData(tOpen);
                }
            }
        }

        //处理倒计时逻辑，倒计时4s之后自动跳到主界面
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                currentSecond--;
                runOnUiThread(() -> splashCountdown.setText(currentSecond + "秒跳过"));
                if (currentSecond == minTime) {
                    if (timer != null) {
                        timer.cancel();
                    }
                    mOperation.forwardAndFinish(MainActivity.class);
                }
            }
        }, 1, 1000);

    }

    @OnClick({R.id.splash_countdown})
    public void onClicked(View view) {
        switch (view.getId()) {
            case R.id.splash_countdown:
                if (timer != null) {
                    timer.cancel();
                }
                mOperation.forwardAndFinish(MainActivity.class);
                break;
        }
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

    @Override
    public void uploadDataSuccess() {
        LogUtils.e("数据上传成功");
    }


}
