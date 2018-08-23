package com.ywb.tuyue.ui.setting.gaindata;

import com.ywb.tuyue.entity.TAdvert;
import com.ywb.tuyue.entity.TDataVersion;
import com.ywb.tuyue.ui.mvp.BasePView;

import java.util.List;


public class GainDataContract {
    interface View extends BasePView {
        void getDataVersionSuccess(TDataVersion tDataVersion);

        void getAdvertSuccess(List<TAdvert> tAdvertList);

        void getOtherDataSuccess();
    }

    interface Presenter {
        //获取当前广告版本与数据版本信息
        void getDataVersion();

        //获取修行广告list
        void getAdvertList();

        //获取其他数据信息
        void getOrtherData();


    }
}
