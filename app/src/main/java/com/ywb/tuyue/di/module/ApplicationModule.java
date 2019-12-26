package com.ywb.tuyue.di.module;

import android.app.NotificationManager;
import android.content.Context;
import android.view.LayoutInflater;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.ywb.tuyue.BuildConfig;
import com.ywb.tuyue.components.okhttp.InterceptorUtils;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * @Author soonphe
 * @Date 2017-11-21 10:56
 * @Description 提供Context，OkHttpClient，LayoutInflater，NotificationManager
 */
@Module
public class ApplicationModule {

    private final Context context;

    public ApplicationModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public Context provideApplicationContext() {
        return context.getApplicationContext();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                //全局的连接超时时间，默认60秒
                .connectTimeout(10, TimeUnit.SECONDS)
                //全局的读取超时时间
                .readTimeout(10, TimeUnit.SECONDS)
                //全局的写入超时时间
                .writeTimeout(10, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true);
        //日志拦截器
        builder.addInterceptor(InterceptorUtils.getHttpLoggingInterceptor(BuildConfig.DEBUG));
        //缓存拦截器
        builder.addInterceptor(InterceptorUtils.getCacheInterceptor());
        //Stetho网络监控拦截器
        builder.addNetworkInterceptor(new StethoInterceptor());
        //...其他拦截器
        return builder.build();
    }


    @Provides
    @Singleton
    LayoutInflater provideLayoutInflater(Context context) {
        return (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Provides
    @Singleton
    NotificationManager provideNotificationManager(Context mContext) {
        return (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
    }

}
