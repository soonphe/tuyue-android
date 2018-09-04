package com.ywb.tuyue.ui.splash;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.BarUtils;
import com.ywb.tuyue.R;
import com.ywb.tuyue.entity.TAdvert;
import com.ywb.tuyue.ui.advert.AdvertContract;
import com.ywb.tuyue.ui.advert.AdvertPresenter;
import com.ywb.tuyue.ui.main.MainActivity;
import com.ywb.tuyue.ui.mvp.BaseActivity;
import com.ywb.tuyue.utils.GlideUtils;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Author soonphe
 * @Date 2018-08-30 10:56
 * @Description flash闪屏页
 */
public class SplashActivity extends BaseActivity implements AdvertContract.View {

    @Inject
    AdvertPresenter advertPresenter;

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
    }

    @Override
    public void doBusiness(Context mContext) {
        advertPresenter.getAdvertListByType(2);
    }

    @Override
    public void getAdvertListSuccess(List<TAdvert> list) {
        if (list.size()>0){
            //这里只选取最新的1张
            GlideUtils.loadImageView(this,
                    list.get(0).getDownloadPic(), ivFlash);
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
                mOperation.forwardAndFinish(MainActivity.class, LEFT_RIGHT);
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

}
