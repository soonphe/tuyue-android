package com.ywb.tuyue.di.module;

import android.app.Activity;

import com.ywb.tuyue.di.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * @Author anna
 * @Date 2017-11-21 20:45
 * @Description 提供activity，@Module声明组建，  @Provides提供对象，  @PerActivity自定义注解，限定注解作用域
 */
@Module
public class ActivityModule {

  private final Activity mActivity;

  public ActivityModule(Activity mActivity) {
    this.mActivity = mActivity;
  }

  @Provides
  @PerActivity
  public Activity provideActivity() {
    return mActivity;
  }
}
