package com.ywb.tuyue.ui.game;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.ywb.tuyue.R;
import com.ywb.tuyue.constants.Constants;
import com.ywb.tuyue.entity.GameList;
import com.ywb.tuyue.ui.adapter.GameListAdapter;
import com.ywb.tuyue.ui.mvp.BaseFragmentV4;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by JuZhongJoy on 2018/6/28.
 * 游戏列表
 */

@SuppressLint("ValidFragment")
public class GameListFragment extends BaseFragmentV4 implements GameListPresenter.GameListSuccess {

    @BindView(R.id.gameList)
    RecyclerView mRecyclclerView;

    GameListAdapter mGameListAdapter;

    @Inject
    GameListPresenterImp mGameListImp;


    @Override
    public void initInjector() {

    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_game;
    }

    @Override
    public void initParams(Bundle params) {
        String string = params.getString(Constants.GAME_TYPE);
        String name = params.getString(Constants.GAME_NAME);
        Log.i("====", string + ",,," + name);
    }

    @Override
    public void initView(View view) {
        mGameListImp.attachView(this);

    }

    @Override
    public void doBusiness(Context mContext) {
        mGameListImp.getGameList();
    }

    @Override
    public void getGameListSuccess(List<GameList> gameLists) {


    }

    @Override
    public void lazyInitBusiness(Context mContext) {

    }


    @Override
    public void startLoading() {

    }

    @Override
    public void endLoading() {

    }

    @Override
    public void onError(String error) {

    }


}
