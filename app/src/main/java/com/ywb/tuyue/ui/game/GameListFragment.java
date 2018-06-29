package com.ywb.tuyue.ui.game;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.ywb.tuyue.R;
import com.ywb.tuyue.constants.Constants;
import com.ywb.tuyue.entity.GameList;
import com.ywb.tuyue.ui.adapter.GameListAdapter;
import com.ywb.tuyue.ui.mvp.BaseFragmentV4;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by JuZhongJoy on 2018/6/28.
 * 游戏列表
 */

@SuppressLint("ValidFragment")
public class GameListFragment extends BaseFragmentV4 implements GameListPresenter.GameListSuccess, GameListAdapter.GameItem {

    @BindView(R.id.gameList)
    RecyclerView mRecyclclerView;

    GameListAdapter mGameListAdapter;

    @Inject
    GameListPresenterImp mGameListImp;

    private List<GameList> mGameLists = new ArrayList<>();//接收游戏列表数据


    @Override
    public void initInjector() {
        getComponent().inject(this);
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_game;
    }

    @Override
    public void initParams(Bundle params) {

    }

    @Override
    public void initView(View view) {
        mGameListImp.attachView(this);

    }

    @Override
    public void doBusiness(Context mContext) {
        mGameListImp.getGameList();
    }

    /**
     * 填充列表,设置适配器、数据、点击事件
     *
     * @param gameLists
     */
    @Override
    public void getGameListSuccess(List<GameList> gameLists) {
        mGameLists = gameLists;
        mRecyclclerView.setHasFixedSize(true);
        mRecyclclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        mGameListAdapter = new GameListAdapter(getContext());
        mRecyclclerView.setAdapter(mGameListAdapter);
        mGameListAdapter.setdata(gameLists);
        mGameListAdapter.setOnItemListener(this);
    }

    /**
     * 卡片点击事件，进行解压，读取游戏内容
     *
     * @param position
     */
    @Override
    public void onItemClick(int position) {
        GameList gameList = mGameLists.get(position);
        goHtml(gameList.getName(), gameList.getFilepath());
    }

    private void goHtml(String title, String filepath) {
        mOperation.addParameter(Constants.GAME_NAME, title);
       // mOperation.addParameter(Constants.GAME_CONTENT, title);
        mOperation.forward(GamePlayActivity.class,LEFT_RIGHT);
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
