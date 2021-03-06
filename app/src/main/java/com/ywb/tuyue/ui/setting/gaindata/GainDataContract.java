package com.ywb.tuyue.ui.setting.gaindata;

import com.ywb.tuyue.entity.TAdvert;
import com.ywb.tuyue.entity.TDataVersion;
import com.ywb.tuyue.base.mvp.BasePView;

import java.util.List;


public class GainDataContract {
    interface View extends BasePView {

        void getDataVersionSuccess(TDataVersion tDataVersion);

        void getAdvertSuccess(List<TAdvert> tAdvertList);

        void getOtherDataSuccess();

        void getMovieDataSuccess();

        void uploadUserDataSuccess();

        void uploadStatsDataSuccess();
    }

    interface Presenter {

        //获取当前广告版本与数据版本信息
        void getDataVersion();

        //获取广告list
        void getAdvertList();

        //获取其他数据信息
        void getOtherData();

        //获取1905电影数据
        void getMovieData();

        //上传用户数据
        void uploadUserData();

        //上传用户数据
        void uploadStatsData();
    }
}
