package com.ywb.tuyue.di.component;

import android.content.Context;
import android.view.LayoutInflater;

import com.ywb.tuyue.MyApplication;
import com.ywb.tuyue.api.AppApi;
import com.ywb.tuyue.base.BaseActivity;
import com.ywb.tuyue.di.module.ApiModule;
import com.ywb.tuyue.di.module.ApplicationModule;
import com.ywb.tuyue.di.module.DBModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @Author soonphe
 * @Date 2017-11-21 10:53
 * @Description ApplicationComponent
 */
@Singleton
@Component(modules = {ApplicationModule.class, ApiModule.class, DBModule.class})
public interface ApplicationComponent {
    Context getContext();

    LayoutInflater getLayoutInflater();

    AppApi getAccountApi();

    void inject(MyApplication mApplication);

    void inject(BaseActivity mBaseActivity);
}
