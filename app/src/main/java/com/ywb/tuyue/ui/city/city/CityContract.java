package com.ywb.tuyue.ui.city.city;

import com.ywb.tuyue.entity.TCity;
import com.ywb.tuyue.base.mvp.BasePView;

import java.util.List;


public class CityContract {
    interface View extends BasePView {
        void getCityListSuccess( List<TCity> list);
    }

    interface  Presenter  {
        void getCityList( );
    }
}
