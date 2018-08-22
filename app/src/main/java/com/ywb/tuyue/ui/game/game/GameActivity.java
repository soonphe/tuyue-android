package com.ywb.tuyue.ui.game.game;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.View;

import com.youth.banner.Banner;
import com.ywb.tuyue.R;
import com.ywb.tuyue.ui.mvp.BaseActivity;

import butterknife.BindView;


/**
 * @Author soonphe
 * @Date 2018-08-21 13:12
 * @Description 游戏列表
 */
public class GameActivity extends BaseActivity implements GameContract.View {

    @BindView(R.id.pagerTab)
    TabLayout pagerTab;
//    @BindView(R.id.pager)
//    CustomViewPager pager;
    @BindView(R.id.banner_Game)
    Banner bannerGame;

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

}
