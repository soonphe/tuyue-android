package com.ywb.tuyue.ui.unlock;

import com.ywb.tuyue.api.AppApi;
import com.ywb.tuyue.di.PerActivity;
import com.ywb.tuyue.entity.TOpen;
import com.ywb.tuyue.ui.mvp.BasePresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * @Author anna
 * @Date 2017-12-06 13:54
 * @Description
 */
@PerActivity
public class UnlockPresenter extends BasePresenter<UnlockContract.UnlockView> implements UnlockContract.UnlockPresenter {

    private AppApi api;

    @Inject
    public UnlockPresenter(AppApi accountApi) {
        this.api = accountApi;
    }


    @Override
    public void uploadData(List<TOpen> list) {
        mDisposable.add(api.uploadOpen(list)
                .subscribe(categoryMenus -> mView.uploadDataSuccess(),
                        throwable ->
                                mView.onError(throwable.getMessage())
                )
        );
    }
}
