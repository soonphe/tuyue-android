package com.ywb.tuyue.api;


import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.ywb.tuyue.constants.Constants;
import com.ywb.tuyue.dto.TStatsDto;
import com.ywb.tuyue.dto.TUserDto;
import com.ywb.tuyue.entity.TAdvert;
import com.ywb.tuyue.entity.TAdvertType;
import com.ywb.tuyue.entity.TArticle;
import com.ywb.tuyue.entity.TArticleType;
import com.ywb.tuyue.entity.TBook;
import com.ywb.tuyue.entity.TBookType;
import com.ywb.tuyue.entity.TCity;
import com.ywb.tuyue.entity.TCityArticle;
import com.ywb.tuyue.entity.TDataVersion;
import com.ywb.tuyue.entity.TFood;
import com.ywb.tuyue.entity.TFoodType;
import com.ywb.tuyue.entity.TGame;
import com.ywb.tuyue.entity.TGameType;
import com.ywb.tuyue.entity.TMovieBean;
import com.ywb.tuyue.entity.TVersion;
import com.ywb.tuyue.entity.TVideo;
import com.ywb.tuyue.entity.TVideoType;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Author soonphe
 * @Date 2017-11-20 18:04
 * @Description API
 */

public class AppApi implements AppApiService {

    @Inject
    OkHttpClient okHttpClient;

    private AppApiService service;

    public AppApi(OkHttpClient mOkHttpClient) {
        Retrofit retrofit =
                new Retrofit.Builder()
                        .client(mOkHttpClient)
                        .baseUrl(Constants.BASE_API_URL)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(com.ywb.tuyue.components.retrofit.GsonConverterFactory.create())
                        .build();
        service = retrofit.create(AppApiService.class);
    }

    /**
     * 自定义请求路径
     *
     * @param baseUrl 自定义请求路径
     */
    public AppApiService getAppApi(String baseUrl) {
        Retrofit retrofit =
                new Retrofit.Builder()
                        .client(okHttpClient)
                        .baseUrl(baseUrl)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        //这里使用的是默认json解析
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
        return retrofit.create(AppApiService.class);
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
//                        .addConverterFactory(com.ywb.tuyue.components.retrofit.GsonConverterFactory.create())
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
        return upstream -> upstream.
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread());
    }


    @Override
    public Observable<TMovieBean> getMovieList() {
        return getDynamicAppApi("http://192.168.1.6:8087/index.php/").getMovieList().compose(bindUntil());
    }

    @Override
    public Observable<List<TAdvertType>> getAdvertType() {
        return service.getAdvertType().compose(bindUntil());
    }

    @Override
    public Observable<List<TAdvert>> getAdvertList(int pCommonSearchVO) {
        return service.getAdvertList(pCommonSearchVO).compose(bindUntil());
    }

    @Override
    public Observable<List<TVideoType>> getVideoType() {
        return service.getVideoType().compose(bindUntil());
    }

    @Override
    public Observable<List<TVideo>> getVideoList(int pageSize) {
        return service.getVideoList(pageSize).compose(bindUntil());
    }

    @Override
    public Observable<List<TGameType>> getGameType() {
        return service.getGameType().compose(bindUntil());
    }

    @Override
    public Observable<List<TGame>> getGameList(int pageSize) {
        return service.getGameList(pageSize).compose(bindUntil());
    }

    @Override
    public Observable<List<TBookType>> getBookType() {
        return service.getBookType().compose(bindUntil());
    }

    @Override
    public Observable<List<TBook>> getBookList(int pageSize) {
        return service.getBookList(pageSize).compose(bindUntil());
    }

    @Override
    public Observable<List<TFoodType>> getFoodType() {
        return service.getFoodType().compose(bindUntil());
    }

    @Override
    public Observable<List<TFood>> getFoodList(int pageSize) {
        return service.getFoodList(pageSize).compose(bindUntil());
    }

    @Override
    public Observable<List<TCity>> getCityList(int pageSize) {
        return service.getCityList(pageSize).compose(bindUntil());
    }

    @Override
    public Observable<List<TCityArticle>> getCityArticleList(int typeId, int pageSize) {
        return service.getCityArticleList(typeId,pageSize).compose(bindUntil());
    }

    @Override
    public Observable<List<TArticleType>> getArticleType() {
        return service.getArticleType().compose(bindUntil());
    }

    @Override
    public Observable<List<TArticle>> getArticleList(int pageSize) {
        return service.getArticleList(pageSize).compose(bindUntil());
    }


    @Override
    public Observable<TVersion> getVersion() {
        return service.getVersion().compose(bindUntil());
    }

    @Override
    public Observable<TDataVersion> getDataVersion() {
        return service.getDataVersion().compose(bindUntil());
    }

    @Override
    public Observable<Object> uploadUser(String captcher,TUserDto tUser) {
        return service.uploadUser( captcher,tUser).compose(bindUntil());
    }

    @Override
    public Observable<Object> uploadStats(TStatsDto list) {
        return service.uploadStats(list).compose(bindUntil());
    }

    @Override
    public Observable<Object> uploadStatsList(List<TStatsDto> list) {
        return service.uploadStatsList(list).compose(bindUntil());
    }



}
