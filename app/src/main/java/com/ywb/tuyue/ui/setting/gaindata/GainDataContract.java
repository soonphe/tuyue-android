package com.ywb.tuyue.ui.setting.gaindata;

import com.ywb.tuyue.entity.TAdvert;
import com.ywb.tuyue.entity.TDataVersion;
import com.ywb.tuyue.ui.mvp.BasePView;

import java.util.List;


public class GainDataContract {
    interface View extends BasePView {
        void getDataVersionSuccess(TDataVersion tDataVersion);
        void getAdvertSuccess(List<TAdvert> tAdvertList);
    }

    interface  Presenter  {
        //获取当前广告版本与数据版本信息
        void getDataVersion();
        //获取广告信息
        void getAdvertList();


    }
}
