package com.ywb.tuyue.ui.main;

import com.ywb.tuyue.api.AppApi;
import com.ywb.tuyue.di.PerActivity;
import com.ywb.tuyue.entity.TStats;
import com.ywb.tuyue.entity.TUser;
import com.ywb.tuyue.ui.mvp.BasePresenter;

import javax.inject.Inject;

@PerActivity
public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    private AppApi api;

    @Inject
    public MainPresenter(AppApi accountApi) {
        this.api = accountApi;
    }

    @Override
    public void uploadUser(TUser tUser) {
        mDisposable.add(api.uploadUser("973570", tUser)
                .subscribe(categoryMenus -> mView.uploadUserSuccess(),
                        throwable ->
                                mView.onError(throwable.getMessage())
                )
        );
    }


}
