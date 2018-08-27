package com.ywb.tuyue.ui.game.game;

import com.ywb.tuyue.api.AppApi;
import com.ywb.tuyue.di.PerActivity;
import com.ywb.tuyue.entity.TAdvert;
import com.ywb.tuyue.entity.TGame;
import com.ywb.tuyue.ui.mvp.BasePresenter;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


@PerActivity
public class GamePresenter extends BasePresenter<GameContract.View> implements GameContract.Presenter {

    private AppApi api;

    @Inject
    public GamePresenter(AppApi api) {
        this.api = api;
    }

    @Override
    public void getAdvertList() {
        //获取游戏广告
        List<TAdvert> list = LitePal
                .where("typeid = ?", "5")
                .find(TAdvert.class);
        if (list.size() > 0) {
            mView.getAdvertListSuccess(list);
        } else {
            mView.getAdvertListSuccess(new ArrayList<>());
        }
    }

    @Override
    public void getGameList() {
        //获取游戏列表
        List<TGame> list = LitePal.findAll(TGame.class);
        if (list.size() > 0) {
            mView.getGameListSuccess(list);
        } else {
            mView.getGameListSuccess(new ArrayList<>());
        }
    }
}
