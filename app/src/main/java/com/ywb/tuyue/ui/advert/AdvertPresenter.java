package com.ywb.tuyue.ui.advert;

import com.ywb.tuyue.api.AppApi;
import com.ywb.tuyue.di.PerActivity;
import com.ywb.tuyue.entity.TAdvert;
import com.ywb.tuyue.base.mvp.BasePresenter;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

@PerActivity
public class AdvertPresenter extends BasePresenter<AdvertContract.View> implements AdvertContract.Presenter{

    private AppApi api;

    @Inject
    public AdvertPresenter(AppApi accountApi) {
        this.api = accountApi;
    }


    @Override
    public void getAdvertListByType(int typeid) {
        List<TAdvert> list = LitePal.where("typeid=?", typeid+"").order("sort asc").find(TAdvert.class);
        if (list.size() > 0) {
            mView.getAdvertListSuccess(list);
        } else {
            mView.getAdvertListSuccess(new ArrayList<>());
        }
    }
}
