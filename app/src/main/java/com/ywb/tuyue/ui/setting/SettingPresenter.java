package com.ywb.tuyue.ui.setting;

import com.ywb.tuyue.api.AppApi;
import com.ywb.tuyue.di.PerActivity;
import com.ywb.tuyue.ui.mvp.BasePresenter;

import javax.inject.Inject;

@PerActivity
public class SettingPresenter extends BasePresenter<SettingContract.View> implements SettingContract.Presenter{



    private AppApi api;

    @Inject
    public SettingPresenter(AppApi api) {
        this.api = api;
    }

}
