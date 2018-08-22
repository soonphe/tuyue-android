package com.ywb.tuyue.ui.cinema;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.util.BarUtils;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.ywb.tuyue.R;
import com.ywb.tuyue.entity.PMenu;
import com.ywb.tuyue.ui.adapter.MenuAdapter;
import com.ywb.tuyue.ui.adapter.ViewPagerAdapter;
import com.ywb.tuyue.ui.cinema.movie.MovieFragment;
import com.ywb.tuyue.ui.cinema.video.VideoFragment;
import com.ywb.tuyue.ui.mvp.BaseActivity;
import com.ywb.tuyue.widget.MyViewPager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;


/**
 * @Author soonphe
 * @Date 2018-08-21 17:30
 * @Description 影视
 */
public class CinemaActivity extends BaseActivity implements CinemaContract.View {

    @Inject
    CinemaPresenter presenter;

    @BindView(R.id.movie_recycler_menu)
    RecyclerView movieRecyclerMenu;
    @BindView(R.id.movie_pager)
    MyViewPager moviePager;

    private MenuAdapter menuAdapter;

    private int menuChecked = 0;
    private List<Fragment> mCinemaFragments = new ArrayList<>();    //viewpager fragment集合
    List<PMenu> pMenus = new ArrayList<>(); //左部菜单集合

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
        return R.layout.activity_cinema;
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public void initView(View view) {
        presenter.attachView(this);
        BarUtils.setStatusBarAlpha(this, 0);
        mCinemaFragments.add(new MovieFragment());
        mCinemaFragments.add(new VideoFragment());
        moviePager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), mCinemaFragments));
        moviePager.setOffscreenPageLimit(2); //设置ViewPager的缓存界面数，每一侧的界面数(默认是缓存相邻的)
        moviePager.setCurrentItem(0);


        pMenus.add(new PMenu(0, "电影"));
        pMenus.add(new PMenu(1, "小视频"));
        menuAdapter = new MenuAdapter(R.layout.item_menu, pMenus);
        movieRecyclerMenu.setLayoutManager(new LinearLayoutManager(getContext()));
        movieRecyclerMenu.addItemDecoration(new SpaceDecoration(1));
        movieRecyclerMenu.setAdapter(menuAdapter);
        movieRecyclerMenu.setNestedScrollingEnabled(false);

        menuAdapter.setOnItemClickListener((adapter, view1, position) -> {
            //点击变色
            if (position != menuChecked) {
                view1.setBackgroundColor(getResources().getColor(R.color.text_red));
                movieRecyclerMenu.getChildAt(menuChecked).setBackgroundColor(getResources().getColor(R.color.transparent));
                menuChecked = position;
                moviePager.setCurrentItem(position);

            }

        });

    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public void initInjector() {
        getComponent().inject(this);
    }


}
