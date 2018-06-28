package com.ywb.tuyue.di.module;

import android.app.NotificationManager;
import android.content.Context;
import android.view.LayoutInflater;

import com.blankj.utilcode.util.CacheUtils;
import com.blankj.utilcode.util.StringUtils;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.ywb.tuyue.components.okhttp.HttpLoggingInterceptor;
import com.ywb.tuyue.constants.IpConfig;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * @Author anna
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
        OkHttpClient.Builder builder = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true);
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addNetworkInterceptor(new StethoInterceptor());
        builder.addInterceptor(logging);
        //请求header拦截器——存在userId/token即添加
        builder.addInterceptor(
                chain -> {
                    Request originalRequest = chain.request();
                    if (StringUtils.isEmpty(CacheUtils.getInstance().getString(IpConfig.USER_ID)+"") || StringUtils.isEmpty(CacheUtils.getInstance().getString(IpConfig.USER_TOKEN))) {
                        return chain.proceed(originalRequest);
                    }
                    Request authorised = originalRequest.newBuilder()
                            .addHeader("userId", CacheUtils.getInstance().getString(IpConfig.USER_ID).toString())
                            .addHeader("token", CacheUtils.getInstance().getString(IpConfig.USER_TOKEN)+"")
                            .build();
                    return chain.proceed(authorised);
                }
        );
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
