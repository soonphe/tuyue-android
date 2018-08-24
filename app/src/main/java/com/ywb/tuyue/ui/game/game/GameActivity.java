package com.ywb.tuyue.ui.game.game;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.youth.banner.Banner;
import com.ywb.tuyue.R;
import com.ywb.tuyue.entity.TAdvert;
import com.ywb.tuyue.entity.TGame;
import com.ywb.tuyue.ui.mvp.BaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @Author soonphe
 * @Date 2018-08-21 13:12
 * @Description 游戏列表
 */
public class GameActivity extends BaseActivity implements GameContract.View {

    @BindView(R.id.banner_Game)
    Banner bannerGame;
    @BindView(R.id.pagerTab)
    TabLayout pagerTab;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.sm_refresh)
    SmartRefreshLayout smRefresh;

    @Override
    public void startLoading() {

    }

    @Override
    public void endLoading() {

    }

    @Override
    public void onError(String error) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_game;
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public void initInjector() {

    }

    @Override
    public void getAdvertListSuccess(List<TAdvert> list) {

    }

    @Override
    public void getGameListSuccess(List<TGame> list) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
