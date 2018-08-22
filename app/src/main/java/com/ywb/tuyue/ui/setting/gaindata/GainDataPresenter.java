package com.ywb.tuyue.ui.setting.gaindata;

import com.ywb.tuyue.api.AppApi;
import com.ywb.tuyue.di.PerActivity;
import com.ywb.tuyue.ui.mvp.BasePresenter;

import javax.inject.Inject;

@PerActivity
public class GainDataPresenter extends BasePresenter<GainDataContract.View> implements GainDataContract.Presenter{

    private AppApi api;

    @Inject
    public GainDataPresenter(AppApi api) {
        this.api = api;
    }

    @Override
    public void getDataVersion() {
        mDisposable.add(api.getDataVersion().subscribe(list -> {
                    mView.getDataVersionSuccess(list);
                },
                throwable -> mView.onError(throwable.getMessage()))
        );
    }

    @Override
    public void getAdvertList() {
        mDisposable.add(api.getAdvertList(1000).subscribe(list -> {
                    mView.getAdvertSuccess(list);
                },
                throwable -> mView.onError(throwable.getMessage()))
        );
    }
}
