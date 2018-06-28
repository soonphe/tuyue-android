package com.ywb.tuyue.api;


import com.ywb.tuyue.entity.GameType;
import com.ywb.tuyue.entity.TAdvert;
import com.ywb.tuyue.entity.TUnlockAdvert;

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
     * 根据类别获取解锁屏页的广告
     *
     * @return
     */
    @GET("advertType/getOne")
    Observable<TUnlockAdvert> getUnlockAdvert(@Query("id") Integer id);


    /**
     * 所有的广告详情页中的数据列表
     */

    @GET("advert/getList")
    // http://192.168.1.6:8080/tuyue/api/advert/getList
    Observable<List<TAdvert>> getMainPage();

    /**
     * 游戏类别
     * @return
     */
    @GET("gameType/getList")
    Observable<List<GameType>> getGameType();
}
