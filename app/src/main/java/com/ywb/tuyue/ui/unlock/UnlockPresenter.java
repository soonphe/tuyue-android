package com.ywb.tuyue.ui.unlock;

import com.ywb.tuyue.api.AppApi;
import com.ywb.tuyue.di.PerActivity;
import com.ywb.tuyue.entity.TStats;
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



}
