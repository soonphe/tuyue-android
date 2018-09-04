package com.ywb.tuyue.ui.setting;


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
import com.ywb.tuyue.ui.mvp.BaseActivity;
import com.ywb.tuyue.ui.setting.aboutus.AboutUsFragment;
import com.ywb.tuyue.ui.setting.gaindata.GainDataFragment;
import com.ywb.tuyue.ui.setting.hotspot.HotspotFragment;
import com.ywb.tuyue.ui.setting.network.NetworkFragment;
import com.ywb.tuyue.ui.setting.version.VersionFragment;
import com.ywb.tuyue.widget.MyViewPager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;


/**
 * @Author soonphe
 * @Date 2018-08-22 11:01
 * @Description 设置
 */
public class SettingActivity extends BaseActivity implements SettingContract.View {

    @Inject
    SettingPresenter presenter;

    @BindView(R.id.recycler_menu)
    RecyclerView recyclerMenu;
    @BindView(R.id.mypager)
    MyViewPager mypager;

    private MenuAdapter menuAdapter;

    private int menuChecked = 0;
    private List<Fragment> mFragments = new ArrayList<>();  //viewpager fragment集合
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
        return R.layout.activity_settings;
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public void initView(View view) {

        presenter.attachView(this);
        BarUtils.setStatusBarAlpha(this, 0);
        mFragments.add(new GainDataFragment());
        mFragments.add(new VersionFragment());
        mFragments.add(new NetworkFragment());
        mFragments.add(new HotspotFragment());
        mFragments.add(new AboutUsFragment());
        mypager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), mFragments));
        mypager.setOffscreenPageLimit(5); //设置ViewPager的缓存界面数，每一侧的界面数(默认是缓存相邻的)
        mypager.setCurrentItem(0);

        //创建左部菜单
        pMenus.add(new PMenu(0, "数据同步"));
        pMenus.add(new PMenu(1, "系统版本"));
        pMenus.add(new PMenu(2, "网络设置"));
        pMenus.add(new PMenu(4, "热点设置"));
        pMenus.add(new PMenu(5, "关于我们"));
        menuAdapter = new MenuAdapter(R.layout.item_menu, pMenus);
        recyclerMenu.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerMenu.addItemDecoration(new SpaceDecoration(1));
        recyclerMenu.setAdapter(menuAdapter);
        recyclerMenu.setNestedScrollingEnabled(false);

        menuAdapter.setOnItemClickListener((adapter, view1, position) -> {
            //点击变色
            if (position != menuChecked) {
                view1.setBackgroundColor(getResources().getColor(R.color.text_red));
                recyclerMenu.getChildAt(menuChecked).setBackgroundColor(getResources().getColor(R.color.transparent));
                menuChecked = position;
                mypager.setCurrentItem(position);
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
