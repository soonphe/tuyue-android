package com.ywb.tuyue.ui.city.citydetail;

import com.ywb.tuyue.api.AppApi;
import com.ywb.tuyue.di.PerActivity;
import com.ywb.tuyue.entity.TCityArticle;
import com.ywb.tuyue.ui.mvp.BasePresenter;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

@PerActivity
public class CitydetailPresenter extends BasePresenter<CitydetailContract.View> implements CitydetailContract.Presenter {

    private AppApi api;

    @Inject
    public CitydetailPresenter(AppApi accountApi) {
        this.api = accountApi;
    }

    @Override
    public void getCityList(String cityid, String typeid) {
        //获取城市文章列表
        List<TCityArticle> list = LitePal.where("cityid=? and typeid=?", cityid, typeid).find(TCityArticle.class);
        if (list.size() > 0) {
            mView.getCityListSuccess(list);
        } else {
            mView.getCityListSuccess(new ArrayList<>());
        }
    }
}
