package com.ywb.tuyue.ui.unlock;

import android.content.Context;

import com.ywb.tuyue.api.AppApi;
import com.ywb.tuyue.di.PerActivity;
import com.ywb.tuyue.ui.mvp.BasePresenter;

import javax.inject.Inject;

/**
 * @Author anna
 * @Date 2017-12-06 13:54
 * @Description
 */
@PerActivity
public class UnlockPresenter extends BasePresenter<UnlockContract.UnlockView> implements UnlockContract.UnlockPresenter {
    Context context;
    private AppApi api;
    @Inject
    public UnlockPresenter(Context context, AppApi accountApi) {
        this.context = context;
        this.api = accountApi;
    }


    @Override
    public void getAdvertType() {
        mView.startLoading();
        mDisposable.add(api.getAdvertType().subscribe(advertsType -> {
                    mView.endLoading();
                    mView.getAdvertTypeSuccess(advertsType);
                },
                throwable -> {
                    mView.onError(throwable.getMessage());
                    mView.endLoading();
                }
        ));
    }

    @Override
    public void getAdvertData() {
        mView.startLoading();
        mDisposable.add(api.getTypeData().subscribe(advertsData -> {
                    mView.endLoading();
                    mView.getAdvertDataSuccess(advertsData);
                },
                throwable -> {
                    mView.onError(throwable.getMessage());
                    mView.endLoading();
                }
        ));
    }
}
