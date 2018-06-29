package com.ywb.tuyue.ui.game;

import android.content.Context;

import com.ywb.tuyue.api.AppApi;
import com.ywb.tuyue.di.PerActivity;
import com.ywb.tuyue.ui.mvp.BasePresenter;

/**
 * Created by JuZhongJoy on 2018/6/29.
 */
@PerActivity
public class GameListPresenterImp extends BasePresenter<GameListPresenter.GameListSuccess> implements GameListPresenter.GameLists {

    private Context context;
    private AppApi api;

    public GameListPresenterImp(Context context, AppApi appApi) {
        this.context = context;
        this.api = appApi;
    }

    @Override
    public void getGameList() {
        mView.startLoading();
        mDisposable.add(api.getGameList().subscribe(gameLists -> {
                    mView.startLoading();
                    mView.getGameListSuccess(gameLists);
                },
                throwable -> {
                    mView.onError(throwable.getMessage());
                    mView.endLoading();
                }));
    }
}
