package com.ywb.tuyue.ui.game;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ywb.tuyue.R;
import com.ywb.tuyue.constants.Constants;
import com.ywb.tuyue.entity.GameType;
import com.ywb.tuyue.ui.main.MainActivity;
import com.ywb.tuyue.ui.mvp.BaseActivity;
import com.ywb.tuyue.widget.CustomViewPager;
import com.ywb.tuyue.widget.bgabanner.BGABanner;
import com.ywb.tuyue.widget.head.HeaderView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * 游戏页面
 */
public class GameActivity extends BaseActivity implements BGABanner.Adapter, BGABanner.OnItemClickListener, GamePresenter.GamePresenterSuccess {

    @BindView(R.id.header)
    HeaderView mHeader;
    @BindView(R.id.gameBanner)
    BGABanner mBanner;   //广告轮播
    @BindView(R.id.pagerTab)
    TabLayout mPagerTab;  //类别导航
    @BindView(R.id.pager)
    CustomViewPager mViewPager;  //viewpager

    @Inject
    GamePresenterImp mPresenterImp;

    private List<GameListFragment> mGameListFragments = new ArrayList<>();  //列表页
    private List<String> mGameMenus = new ArrayList<>();  //导航
    private List<String> mGameMenusUrl = new ArrayList<>();  //里列表地址
    private List<GameType> mGameTypes = new ArrayList<>();

    @Override
    public int bindLayout() {
        return R.layout.activity_game;
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public void initView(View view) {
        initHeader();

        mPresenterImp.attachView(this);

        //测试数据
        mGameMenusUrl.add("http://192.168.1.6/pictures/20180511/1526026819.jpg");
        mGameMenusUrl.add("http://192.168.1.6/pictures/20180511/1526026819.jpg");
    }

    private void initHeader() {
        mHeader.setTitle(R.string.game);
        mHeader.setRightBtnVisiable(View.GONE);
        mHeader.setLeftBtnClickListsner(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOperation.forwardAndFinish(MainActivity.class, LEFT_RIGHT);
            }
        });
    }

    private void setBanner() {
        if (mGameMenusUrl != null && !mGameMenusUrl.isEmpty()) {
            mBanner.setData(mGameMenusUrl, null);
        }
        mBanner.setAdapter(this);
        mBanner.setOnItemClickListener(this);
    }

    private void setViewPager() {
        mGameListFragments.clear();
        for (int i = 0; i < mGameMenus.size(); i++) {
            mOperation.addParameter(Constants.GAME_TYPE, mGameTypes.get(i).getId());
            mOperation.addParameter(Constants.GAME_NAME, mGameTypes.get(i).getName());
            mGameListFragments.add(new GameListFragment());
        }

        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mGameListFragments.get(position);
            }

            @Override
            public int getCount() {
                return mGameListFragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mGameMenus.get(position);
            }
        });
        //----
        for (int i = 0; i < mGameMenus.size(); i++) {
            if (i == 0) {
                mPagerTab.addTab(mPagerTab.newTab().setText(mGameMenus.get(i)), true);
            } else {
                mPagerTab.addTab(mPagerTab.newTab().setText(mGameMenus.get(i)));
            }
        }
        mPagerTab.setupWithViewPager(mViewPager);
    }

    @Override
    public void fillBannerItem(BGABanner banner, View view, Object model, int position) {
        Glide.with(getContext()).load(R.drawable.test_02).into((ImageView) view);
    }

    @Override
    public void onBannerItemClick(BGABanner banner, View view, Object model, int position) {

    }

    @Override
    public void doBusiness(Context mContext) {
        mPresenterImp.getGameTypes();
    }

    @Override
    public void initInjector() {
        getComponent().inject(this);
    }

    @Override
    public void getGameTypeSuccess(List<GameType> gameType) {
        mGameTypes = gameType;
        if (gameType.size() > 0) {
            for (GameType types : gameType) {
                mGameMenus.add(types.getName());
            }
            setBanner();
            setViewPager();
        }
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
