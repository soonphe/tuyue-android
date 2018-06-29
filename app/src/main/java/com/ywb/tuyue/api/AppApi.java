package com.ywb.tuyue.api;


import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.ywb.tuyue.components.okhttp.HttpLoggingInterceptor;
import com.ywb.tuyue.constants.IpConfig;
import com.ywb.tuyue.entity.GameList;
import com.ywb.tuyue.entity.GameType;
import com.ywb.tuyue.entity.TAdvertData;
import com.ywb.tuyue.entity.TAdvertType;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Author anna
 * @Date 2017-11-20 18:04
 * @Description 网络请求
 */

public class AppApi implements AppApiService {
    private AppApiService service;

    public AppApi(OkHttpClient mOkHttpClient) {
        Retrofit retrofit =
                new Retrofit.Builder()
                        .client(mOkHttpClient)
                        .baseUrl(IpConfig.BASE_URL) //http://192.168.9.145:8080/api/
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(com.ywb.tuyue.components.retrofit.GsonConverterFactory.create())
                        .build();
        service = retrofit.create(AppApiService.class);
    }

    /**
     * 动态请求路径+默认json解析
     *
     * @param baseUrl
     */
    public AppApiService getDynamicAppApi(String baseUrl) {

        OkHttpClient.Builder builder = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true);
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addNetworkInterceptor(new StethoInterceptor());
        builder.addInterceptor(logging);

        Retrofit retrofit =
                new Retrofit.Builder()
                        .client(builder.build())
                        .baseUrl(baseUrl)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        //使用默认Gson解析
                        .addConverterFactory(GsonConverterFactory.create())
//                        .addConverterFactory(com.connxun.wuye.components.retrofit.GsonConverterFactory.create())
                        .build();
        return retrofit.create(AppApiService.class);
    }

    /**
     * 封装.subscribeOn(Schedulers.io()).
     * observeOn(AndroidSchedulers.mainThread());
     *
     * @return
     */
    private ObservableTransformer bindUntil() {
        return new ObservableTransformer() {
            @Override
            public ObservableSource apply(Observable upstream) {
                return upstream.
                        subscribeOn(Schedulers.io()).
                        observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     *
     * 获取所有广告的类型
     * @return
     */
    @Override
    public Observable<List<TAdvertType>> getAdvertType() {
        return service.getAdvertType().compose(bindUntil());
    }

    /**
     * 获取所有的广告数据
     */
    @Override
    public Observable<List<TAdvertData>> getTypeData() {
        return service.getTypeData().compose(bindUntil());
    }



    /**
     * 游戏类别
     * @return
     */
    @Override
    public Observable<List<GameType>> getGameType() {
        return service.getGameType().compose(bindUntil());
    }

    /**
     * 游戏列表
     * @return
     */
    @Override
    public Observable<List<GameList>> getGameList() {
        return service.getGameList().compose(bindUntil());
    }
}
