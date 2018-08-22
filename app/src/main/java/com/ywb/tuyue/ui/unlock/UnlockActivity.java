package com.ywb.tuyue.ui.unlock;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.blankj.utilcode.util.BarUtils;
import com.ywb.tuyue.R;
import com.ywb.tuyue.ui.mvp.BaseActivity;
import com.ywb.tuyue.ui.splash.SplashActivity;
import com.ywb.tuyue.widget.CustomerUnlockView;

import javax.inject.Inject;

import butterknife.BindView;

public class UnlockActivity extends BaseActivity implements UnlockContract.UnlockView {

    @BindView(R.id.unlock_bg_advert)
    ImageView unlockBgAdvert;
    @BindView(R.id.unlock_view)
    CustomerUnlockView unlockView;

    @Inject
    UnlockPresenter mUnlockPresenter;

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
        mUnlockPresenter.attachView(this);
        unlockView.setOnLockListener(new OnLockListener() {
            @Override
            public void onLockListener(boolean isLocked) {
                if (isLocked) {
                    mOperation.forwardAndFinish(SplashActivity.class, LEFT_RIGHT);
                }
            }
        });
    }


    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public void initInjector() {
        getComponent().inject(this);
    }

}
