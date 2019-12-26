package com.ywb.tuyue.di.module;


import com.ywb.tuyue.api.AppApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * @Author soonphe
 * @Date 2017-11-21 20:46
 * @Description  传入OkHttpClient用以提供Retrofit
 */
@Module
public class ApiModule {

    @Provides
    @Singleton
    public AppApi provideAppApi(OkHttpClient okHttpClient) {
        return new AppApi( okHttpClient);
    }

}
