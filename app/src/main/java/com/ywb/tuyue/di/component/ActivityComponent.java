package com.ywb.tuyue.di.component;

import android.app.Activity;

import com.ywb.tuyue.di.PerActivity;
import com.ywb.tuyue.di.module.ActivityModule;
import com.ywb.tuyue.ui.advertise.AdvertiseDetailActivity;
import com.ywb.tuyue.ui.book.BookActivity;
import com.ywb.tuyue.ui.video.CinemaActivity;
import com.ywb.tuyue.ui.game.game.GameActivity;
import com.ywb.tuyue.ui.game.gameplay.GamePlayActivity;
import com.ywb.tuyue.ui.main.MainActivity;
import com.ywb.tuyue.ui.setting.SettingActivity;
import com.ywb.tuyue.ui.splash.SplashActivity;
import com.ywb.tuyue.ui.unlock.UnlockActivity;
import com.ywb.tuyue.ui.videoplayer.VideoPlayerActivity;

import dagger.Component;


/**
 * @Author anna
 * @Date 2017-11-21 10:52
 * @Description 获取依赖对象，dependencies声明依赖关系
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();

    /**
     * inject方法：将依赖需求方对象送到Component类中，注入所需对象
     * 注：inject方法的参数不能使用父类接受
     *
     * @param activity
     */

    void inject(MainActivity activity);//主界面

    void inject(SplashActivity activity);//欢迎页

    void inject(UnlockActivity unlockActivity);//解锁屏页

    void inject(CinemaActivity cinemaActivity); //电影

    void inject(VideoPlayerActivity videoPlayerActivity); //视频播放界面

    void inject(BookActivity bookActivity);//书吧

    void inject(AdvertiseDetailActivity advertiseDetailActivity);//广告详情页

    void inject(GameActivity activity);//游戏列表页
    void inject(GamePlayActivity gamePlayActivity); //玩游戏页面

    void inject(SettingActivity activity);  //设置界面






}
