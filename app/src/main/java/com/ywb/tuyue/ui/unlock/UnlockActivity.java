package com.ywb.tuyue.ui.unlock;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.blankj.utilcode.util.LogUtils;
import com.ywb.tuyue.R;
import com.ywb.tuyue.constants.IpConfig;
import com.ywb.tuyue.entity.TAdvertData;
import com.ywb.tuyue.entity.TAdvertType;
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
    String[] mAdvertTypes = {"锁屏广告", "首页广告", "电影广告", "游戏广告", "书吧广告","音乐广告"};
    List<TAdvertType> mTAdvertTypes;
    
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
        mUnlockPresenter.getAdvertData();
        mUnlockPresenter.getAdvertType();
        // TODO: 2018/6/20  业务调用
        // TODO: 2018/6/21 处理解锁屏统计次数
    }

    @Override
    public void initInjector() {
        getComponent().inject(this);
    }



    @Override
    public void getAdvertTypeSuccess(List<TAdvertType> tAdvertType) {
        this.mTAdvertTypes=tAdvertType;

//        if("锁屏广告".equals(tAdvertType.get(0).getName())){
//            mAdvertType=tAdvertType.get(0).getName(); //锁屏广告
//            LogUtils.e(mAdvertType+"////");
//        }

//        if("首页广告".equals(tAdvertType.get(1).getName())){
//            mAdvertType=tAdvertType.get(1).getName(); //首页广告
//            LogUtils.e(mAdvertType+"////");
//        }
//        if("电影广告".equals(tAdvertType.get(2).getName())){
//            mAdvertType=tAdvertType.get(2).getName(); //电影广告
//            LogUtils.e(mAdvertType+"////");
//        }
//        if("游戏广告".equals(tAdvertType.get(3).getName())){
//            mAdvertType=tAdvertType.get(3).getName(); //游戏广告
//            LogUtils.e(mAdvertType+"////");
//        }
//        if("书吧广告".equals(tAdvertType.get(4).getName())){
//            mAdvertType=tAdvertType.get(4).getName(); //书吧广告
//            LogUtils.e(mAdvertType+"////");
//        }
//        if("音乐广告".equals(tAdvertType.get(5).getName())){
//            mAdvertType=tAdvertType.get(5).getName(); //音乐广告
//            LogUtils.e(mAdvertType+"////");
//        }
    }

    /**
     * 获取所有的广告数据
     * @param tAdvertData
     */
    @Override
    public void getAdvertDataSuccess(List<TAdvertData> tAdvertData) {
        // TODO: 2018/6/28  根据广告类型获取 相应的数据
        if(mAdvertTypes[0].equals(mTAdvertTypes.get(0).getName())){ //当前类型是解锁广告
            LogUtils.e(tAdvertData.get(0).getPicurl()+"////"); ///img/20180628/1530178562.jpg
            String imageUrl= IpConfig.BASE_IMAGE_URL+tAdvertData.get(0).getPicurl();//http://192.168.1.6/upload/img/20180628/1530178562.jpg

            LogUtils.e(imageUrl+"////////////");

            GlideUtils.loadImageView(this,imageUrl,mUnlockAdvertImage);
        }
        if(mAdvertTypes[1].equals(mTAdvertTypes.get(1).getName())){ //当前是首页广告
            //http://192.168.1.6/pictures/20180511/1526026921.jpg
        }

    }
}
