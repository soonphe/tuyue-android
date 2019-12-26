package com.ywb.tuyue.ui.city.city;

import com.ywb.tuyue.api.AppApi;
import com.ywb.tuyue.di.PerActivity;
import com.ywb.tuyue.entity.TCity;
import com.ywb.tuyue.base.mvp.BasePresenter;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


@PerActivity
public class CityPresenter extends BasePresenter<CityContract.View> implements CityContract.Presenter{


    private AppApi api;

    @Inject
    public CityPresenter(AppApi api) {
        this.api = api;
    }

    @Override
    public void getCityList() {
        //获取城市列表
        List<TCity> list = LitePal.findAll(TCity.class);
        if (list.size() > 0) {
            mView.getCityListSuccess(list);
        } else {
            mView.getCityListSuccess(new ArrayList<>());
        }
    }
}
