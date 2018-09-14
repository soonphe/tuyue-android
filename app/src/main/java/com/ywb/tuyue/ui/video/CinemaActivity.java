package com.ywb.tuyue.ui.video;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.View;

import com.blankj.utilcode.constant.TimeConstants;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.ywb.tuyue.R;
import com.ywb.tuyue.constants.Constants;
import com.ywb.tuyue.entity.PMenu;
import com.ywb.tuyue.entity.TStats;
import com.ywb.tuyue.ui.adapter.MenuAdapter;
import com.ywb.tuyue.ui.adapter.ViewPagerAdapter;
import com.ywb.tuyue.ui.data.DataContract;
import com.ywb.tuyue.ui.data.DataPresenter;
import com.ywb.tuyue.ui.video.movie.MovieFragment;
import com.ywb.tuyue.ui.video.video.VideoFragment;
import com.ywb.tuyue.ui.mvp.BaseActivity;
import com.ywb.tuyue.widget.AppTitle;
import com.ywb.tuyue.widget.MyViewPager;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

import static com.blankj.utilcode.util.CacheUtils.SEC;
import static com.ywb.tuyue.constants.Constants.NETWORK_AVAILABLE;


/**
 * @Author soonphe
 * @Date 2018-08-21 17:30
 * @Description 影视
 */
public class CinemaActivity extends BaseActivity implements CinemaContract.View, DataContract.View {

    @Inject
    CinemaPresenter presenter;
    @Inject
    DataPresenter dataPresenter;

    @BindView(R.id.app_title_id)
    AppTitle appTitle;
    @BindView(R.id.movie_recycler_menu)
    RecyclerView movieRecyclerMenu;
    @BindView(R.id.movie_pager)
    MyViewPager moviePager;

    private MenuAdapter menuAdapter;

    private int menuChecked = 0;
    private List<Fragment> mCinemaFragments = new ArrayList<>();    //viewpager fragment集合
    List<PMenu> pMenus = new ArrayList<>(); //左部菜单集合
    private long mMovieTime;//模块停留时长

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
        BarUtils.setStatusBarAlpha(this, 0);
        presenter.attachView(this);
        dataPresenter.attachView(this);
        mMovieTime = System.currentTimeMillis();
        mCinemaFragments.add(new MovieFragment());
        mCinemaFragments.add(new VideoFragment());
        moviePager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), mCinemaFragments));
        moviePager.setOffscreenPageLimit(2); //设置ViewPager的缓存界面数，每一侧的界面数(默认是缓存相邻的)
        moviePager.setCurrentItem(0);


        pMenus.add(new PMenu(0, "电影"));
        pMenus.add(new PMenu(1, "小视频"));
        menuAdapter = new MenuAdapter(R.layout.item_menu, pMenus);
        movieRecyclerMenu.setLayoutManager(new LinearLayoutManager(this));
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        appTitle.getStatusLine().unregisterBroadcast();


        //停留时长统计:单位S
        long movieTime = TimeUtils.getTimeSpan(mMovieTime, System.currentTimeMillis(), TimeConstants.SEC);
        String phone = SPUtils.getInstance().getString(Constants.REGIST_PHONE, "");
        //判断是否为管理员
        if (!"111111".equals(phone)) {
            //获取统计信息
            TStats tStats = LitePal.where("phone = ?", phone + "").order("id desc").findFirst(TStats.class);
            if (tStats != null) {
                tStats.setMoviestime((int) (tStats.getMoviestime() + movieTime));
                boolean result = tStats.save();
                //判断当前网络可用且用户数据保存成功
                if (result) {
                    //上传所有数据
                    dataPresenter.uploadData(tStats);
                }
            }
        }
    }

    @Override
    public void uploadDataSuccess() {
        LogUtils.e("电影停留上传成功");
    }
}
