package com.ywb.tuyue.ui.game;

import android.content.Context;
import android.util.Log;

import com.ywb.tuyue.api.AppApi;
import com.ywb.tuyue.di.PerActivity;
import com.ywb.tuyue.ui.mvp.BasePresenter;

import javax.inject.Inject;

/**
 * Created by JuZhongJoy on 2018/6/28.
 */
@PerActivity
public class GamePresenterImp extends BasePresenter<GamePresenter.GamePresenterSuccess> implements GamePresenter.GameTypes {

    Context context;
    private AppApi api;

    @Inject
    public GamePresenterImp(Context context, AppApi api) {
        this.context = context;
        this.api = api;
    }

    /**
     * 获取类别数据
     */
    @Override
    public void getGameTypes() {
        mView.startLoading();
        mDisposable.add(api.getGameType().subscribe(gameType -> {
                    mView.startLoading();
                    mView.getGameTypeSuccess(gameType);
                },
                throwable -> {
                    mView.onError(throwable.getMessage());
                    mView.endLoading();
                }));
    }
}
