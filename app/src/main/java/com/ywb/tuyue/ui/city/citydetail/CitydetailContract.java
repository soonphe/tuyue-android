package com.ywb.tuyue.ui.city.citydetail;

import com.ywb.tuyue.entity.TCityArticle;
import com.ywb.tuyue.ui.mvp.BasePView;

import java.util.List;


public class CitydetailContract {
    interface View extends BasePView {
        void getCityListSuccess(List<TCityArticle> list);
    }

    interface Presenter {
        void getCityList(String cityid, String typeid);
    }
}
