package com.ywb.tuyue.ui.video;

import com.ywb.tuyue.api.AppApi;
import com.ywb.tuyue.di.PerActivity;
import com.ywb.tuyue.base.mvp.BasePresenter;

import javax.inject.Inject;


@PerActivity
public class CinemaPresenter extends BasePresenter<CinemaContract.View> implements CinemaContract.Presenter {

    private AppApi api;

    @Inject
    public CinemaPresenter(AppApi api) {
        this.api = api;
    }


}
