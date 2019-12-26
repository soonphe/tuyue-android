package com.ywb.tuyue.ui.advert;

import com.ywb.tuyue.entity.TAdvert;
import com.ywb.tuyue.base.mvp.BasePView;

import java.util.List;


public class AdvertContract {
    public interface View extends BasePView {
        void getAdvertListSuccess( List<TAdvert> list);
    }

    public interface  Presenter {
        void getAdvertListByType(int typeid );
    }
}
