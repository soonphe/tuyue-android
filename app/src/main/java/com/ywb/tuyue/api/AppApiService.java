package com.ywb.tuyue.api;


import com.ywb.tuyue.entity.GameList;
import com.ywb.tuyue.entity.GameType;
import com.ywb.tuyue.entity.TAdvertData;
import com.ywb.tuyue.entity.TAdvertType;
import com.ywb.tuyue.entity.TBookData;
import com.ywb.tuyue.entity.TBookType;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @Author anna
 * @Date 2017-12-01 11:44
 * @Description
 */

public interface AppApiService {
    /**
     * 获取所有的广告类型
     *
     * @return
     */
    @GET("advertType/getList")
    //Observable<TAdvertData> getUnlockAdvert(@Query("id") Integer id);
    Observable<List<TAdvertType>> getAdvertType();

    /**
     * 获取所有的广告数据
     */

    @GET("advert/getList") // http://192.168.1.6:8080/tuyue/api/advert/getList
    Observable<List<TAdvertData>> getTypeData();



    /**
     * 游戏类别
     * @return
     */
    @GET("gameType/getList")
    Observable<List<GameType>> getGameType();

    /**
     * 游戏列表
     * @return
     */
    @GET("game/getList")
    Observable<List<GameList>> getGameList();

    /**
     * 获取书籍类型列表
     * @return
     */
    @GET("bookType/getList")
    Observable<List<TBookType>> getBookType();

    @GET("book/getList")
    Observable<List<TBookData>> getBookData();

}
