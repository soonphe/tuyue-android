package com.ywb.tuyue.di.component;


import com.ywb.tuyue.di.PerActivity;
import com.ywb.tuyue.di.module.ActivityModule;
import com.ywb.tuyue.ui.game.GameListFragment;
import com.ywb.tuyue.ui.main.MainActivity;

import dagger.Component;

/**
 * @Author anna
 * @Date 2017-11-21 10:53
 * @Description 需要dagger注入的fragment
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class})
public interface FragmentComponent {
void inject(GameListFragment gameListFragment);

}
