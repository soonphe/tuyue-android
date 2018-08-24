package com.ywb.tuyue.di.component;


import com.ywb.tuyue.di.PerActivity;
import com.ywb.tuyue.di.module.ActivityModule;
import com.ywb.tuyue.ui.video.movie.MovieFragment;
import com.ywb.tuyue.ui.video.video.VideoFragment;
import com.ywb.tuyue.ui.setting.aboutus.AboutUsFragment;
import com.ywb.tuyue.ui.setting.gaindata.GainDataFragment;
import com.ywb.tuyue.ui.setting.version.VersionFragment;

import dagger.Component;

/**
 * @Author anna
 * @Date 2017-11-21 10:53
 * @Description 需要dagger注入的fragment
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class})
public interface FragmentComponent {

    //电影
    void inject(MovieFragment fragment);
    void inject(VideoFragment fragment);

    //设置
    void inject(AboutUsFragment fragment);
    void inject(GainDataFragment fragment);
    void inject(VersionFragment fragment);

}
