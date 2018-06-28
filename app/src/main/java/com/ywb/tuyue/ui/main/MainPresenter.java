package com.ywb.tuyue.ui.main;

import android.content.Context;

import com.ywb.tuyue.api.AppApi;
import com.ywb.tuyue.ui.mvp.BasePresenter;

import javax.inject.Inject;

/**
 * Description:
 * Created by wcystart on 2018/6/27.
 */

public class MainPresenter extends BasePresenter<MainContract.MainView> implements MainContract.MainPresenter {
    private Context mContext;
    private AppApi mApi;
    @Inject
    public MainPresenter(Context context, AppApi api){
        this.mContext=context;
        this.mApi=api;
    }


    @Override
    public void getMain() {

        mView.startLoading();
        mDisposable.add(mApi.getMainPage().subscribe(mainData -> {
                    mView.endLoading();
                    mView.getMainSuccess(mainData);
                },
                throwable -> {
                    mView.onError(throwable.getMessage());
                    mView.endLoading();
                }));
    }
}
