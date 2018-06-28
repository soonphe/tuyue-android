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
import com.ywb.tuyue.ui.mvp.BaseFragmentV4;

import butterknife.BindView;

/**
 * Created by JuZhongJoy on 2018/6/28.
 * 游戏列表
 */

@SuppressLint("ValidFragment")
public class GameListFragment extends BaseFragmentV4{

    @BindView(R.id.gameList)
    RecyclerView mRecyclclerView;


    @Override
    public void initInjector() {
//        getComponent().inject(this);
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_game;
    }

    @Override
    public void initParams(Bundle params) {
        String string = params.getString(Constants.GAME_TYPE);
        String name=params.getString(Constants.GAME_NAME);
        Log.i("====",string+",,,"+name);
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public void lazyInitBusiness(Context mContext) {

    }
}
