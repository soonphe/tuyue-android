package com.ywb.tuyue.api;


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

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @Author anna
 * @Date 2017-12-01 11:44
 * @Description
 */

public interface AppApiService {

    /**
     * 获取1905电影列表
     *
     * @return
     */
    @GET("Home/Interface/index?class=HallUse&method=getVideoList")
    Observable<TMovieBean> getMovieList();

    /**
     * 获取广告类型
     *
     * @return
     */
    @GET("advertType/getList")
    Observable<List<TAdvertType>> getAdvertType();

    /**
     * 获取所有的广告数据
     *
     * @return
     */
    @GET("advert/getList")
    Observable<List<TAdvert>> getAdvertList(@Query("pageSize") int pageSize);

    /**
     * 获取视频类型
     *
     * @return
     */
    @GET("videoType/getList")
    Observable<List<TVideoType>> getVideoType();

    /**
     * 获取所有视频
     *
     * @return
     */
    @GET("video/getList")
    Observable<List<TVideo>> getVideoList(@Query("pageSize") int pageSize);

    /**
     * 获取游戏类别
     *
     * @return
     */
    @GET("gameType/getList")
    Observable<List<TGameType>> getGameType();

    /**
     * 获取游戏列表
     *
     * @return
     */
    @GET("game/getList")
    Observable<List<TGame>> getGameList(@Query("pageSize") int pageSize);

    /**
     * 获取书籍类型列表
     *
     * @return
     */
    @GET("bookType/getList")
    Observable<List<TBookType>> getBookType();

    /**
     * 获取所有书籍
     *
     * @return
     */
    @GET("book/getList")
    Observable<List<TBook>> getBookList(@Query("pageSize") int pageSize);

    /**
     * 获取菜单类型列表
     *
     * @return
     */
    @GET("foodType/getList")
    Observable<List<TFoodType>> getFoodType();

    /**
     * 获取所有菜单
     *
     * @return
     */
    @GET("food/getList")
    Observable<List<TFood>> getFoodList(@Query("pageSize") int pageSize);

    /**
     * 获取所有城市
     *
     * @return
     */
    @GET("city/getList")
    Observable<List<TCity>> getCityList(@Query("pageSize") int pageSize);

    /**
     * 获取城市文章列表
     *
     * @return
     */
    @GET("cityArticle/getList")
    Observable<List<TCityArticle>> getCityArticleList(@Query("typeId") int typeId, @Query("pageSize") int pageSize);

    /**
     * 获取城铁文章类型列表
     *
     * @return
     */
    @GET("articleType/getList")
    Observable<List<TArticleType>> getArticleType();

    /**
     * 获取所有城铁文章
     *
     * @return
     */
    @GET("article/getList")
    Observable<List<TArticle>> getArticleList( @Query("pageSize") int pageSize);

    /**
     * 获取APK最新版本
     *
     * @return
     */
    @GET("version/selectTopOne")
    Observable<TVersion> getVersion();

    /**
     * 获取数据最新版本
     *
     * @return
     */
    @GET("dataVersion/selectTopOne")
    Observable<TDataVersion> getDataVersion();


}
