package com.ywb.tuyue.ui.main;

import com.blankj.utilcode.util.SPUtils;
import com.ywb.tuyue.api.AppApi;
import com.ywb.tuyue.di.PerActivity;
import com.ywb.tuyue.entity.TStats;
import com.ywb.tuyue.entity.TUser;
import com.ywb.tuyue.ui.mvp.BasePresenter;

import javax.inject.Inject;

import static com.ywb.tuyue.constants.Constants.NETWORK_AVAILABLE;

@PerActivity
public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    private AppApi api;

    @Inject
    public MainPresenter(AppApi accountApi) {
        this.api = accountApi;
    }

    @Override
    public void uploadUser(TUser tUser) {
        //有网则上传数据
        if (SPUtils.getInstance().getBoolean(NETWORK_AVAILABLE)) {
            mDisposable.add(api.uploadUser("973570", tUser)
                    .subscribe(categoryMenus -> {
                                //上传成功后更新数据状态码
                                tUser.setDelflag(true);
                                tUser.update(tUser.getId());
                                mView.uploadUserSuccess();
                            }, throwable -> mView.onError(throwable.getMessage())
                    )
            );
        }
    }


}
