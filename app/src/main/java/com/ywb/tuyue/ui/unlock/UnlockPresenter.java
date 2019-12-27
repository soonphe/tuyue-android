package com.ywb.tuyue.ui.unlock;

import com.ywb.tuyue.api.AppApi;
import com.ywb.tuyue.constants.Constants;
import com.ywb.tuyue.di.PerActivity;
import com.ywb.tuyue.base.mvp.BasePresenter;
import com.ywb.tuyue.entity.TAdvert;
import com.ywb.tuyue.utils.HTMLFormatUtils;

import org.litepal.LitePal;

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
