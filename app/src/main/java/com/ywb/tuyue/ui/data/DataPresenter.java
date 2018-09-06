package com.ywb.tuyue.ui.data;

import android.content.Context;

import com.blankj.utilcode.util.SPUtils;
import com.ywb.tuyue.api.AppApi;
import com.ywb.tuyue.di.PerActivity;
import com.ywb.tuyue.dto.TStatsDto;
import com.ywb.tuyue.entity.TStats;
import com.ywb.tuyue.ui.mvp.BasePresenter;

import javax.inject.Inject;

import static com.ywb.tuyue.constants.Constants.NETWORK_AVAILABLE;

@PerActivity
public class DataPresenter extends BasePresenter<DataContract.View> implements DataContract.Presenter {

    private AppApi api;

    @Inject
    public DataPresenter(AppApi accountApi) {
        this.api = accountApi;
    }

    @Override
    public void uploadData(TStats tStats) {
        TStatsDto tStatsDto=new TStatsDto(tStats);
        //判断当前网络可用——可用则上传
        if (SPUtils.getInstance().getBoolean(NETWORK_AVAILABLE)) {
            mDisposable.add(api.uploadStats(tStatsDto)
                    .subscribe(categoryMenus -> mView.uploadDataSuccess(),
                            throwable ->
                                    mView.onError(throwable.getMessage())
                    )
            );
        }
    }
}
