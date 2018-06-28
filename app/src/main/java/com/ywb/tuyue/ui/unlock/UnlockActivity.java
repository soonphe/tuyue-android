package com.ywb.tuyue.ui.unlock;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.blankj.utilcode.util.LogUtils;
import com.ywb.tuyue.R;
import com.ywb.tuyue.constants.IpConfig;
import com.ywb.tuyue.entity.TUnlockAdvert;
import com.ywb.tuyue.ui.mvp.BaseActivity;
import com.ywb.tuyue.ui.splash.SplashActivity;
import com.ywb.tuyue.utils.GlideUtils;
import com.ywb.tuyue.widget.CustomerUnlockView;
import java.util.List;
import javax.inject.Inject;
public class UnlockActivity extends BaseActivity implements UnlockContract.UnlockView {

    //TODO: 2018/6/20 控件
    private ImageView mUnlockAdvertImage;
    private CustomerUnlockView mCustomerUnlockView;
    
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
         //parms.getString()
        // TODO: 2018/6/20 接收参数 
    }

    @Override
    public void initView(View view) {
        // TODO: 2018/6/20 布局
        mUnlockAdvertImage=findViewById(R.id.unlock_bg_advert);
        mCustomerUnlockView=findViewById(R.id.unlock_view);

        mUnlockPresenter.attachView(this);

        mCustomerUnlockView.setOnLockListener(new OnLockListener() {
            @Override
            public void onLockListener(boolean isLocked) {
                if(isLocked){
                   mOperation.forwardAndFinish(SplashActivity.class,LEFT_RIGHT);
                }
            }
        });
    }


    @Override
    public void doBusiness(Context mContext) {
        mUnlockPresenter.getAdvert();
        // TODO: 2018/6/20  业务调用
        // TODO: 2018/6/21 处理解锁屏统计次数
    }

    @Override
    public void initInjector() {
        getComponent().inject(this);
    }


    @Override
    public void getUnlockAdvertSuccess(TUnlockAdvert unlockAdvert) {
        // TODO: 2018/6/28 网络请求回调成功
        LogUtils.e(unlockAdvert.getId()+"////");
        LogUtils.e(unlockAdvert.getName()+"////");
        if(unlockAdvert.getId()==1){

        }

    }
}
