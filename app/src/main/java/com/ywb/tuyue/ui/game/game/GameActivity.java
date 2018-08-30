package com.ywb.tuyue.ui.game.game;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.LogUtils;
import com.bumptech.glide.Glide;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;
import com.ywb.tuyue.R;
import com.ywb.tuyue.entity.TAdvert;
import com.ywb.tuyue.entity.TGame;
import com.ywb.tuyue.entity.TGameType;
import com.ywb.tuyue.entity.TabEntity;
import com.ywb.tuyue.ui.adapter.GameAdapter;
import com.ywb.tuyue.ui.advert.advertise.AdvertContentActivity;
import com.ywb.tuyue.ui.game.gameplay.GamePlayActivity;
import com.ywb.tuyue.ui.mvp.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;


/**
 * @Author soonphe
 * @Date 2018-08-21 13:12
 * @Description 游戏列表
 */
public class GameActivity extends BaseActivity implements GameContract.View {


    @Inject
    GamePresenter presenter;

    @BindView(R.id.banner_Game)
    Banner banner;
//    @BindView(R.id.pagerTab)
//    TabLayout pagerTab;
    @BindView(R.id.tl_2)
    CommonTabLayout tl2;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.sm_refresh)
    SmartRefreshLayout smRefresh;

    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private GameAdapter gameAdapter;

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
        BarUtils.setStatusBarAlpha(this, 0);
        presenter.attachView(this);
        //banner图
        banner.setImageLoader(new GlideImageLoader());


        gameAdapter = new GameAdapter(R.layout.item_game);
        rvList.setLayoutManager(new GridLayoutManager(getContext(), 3));
        rvList.addItemDecoration(new SpaceDecoration(10));
        rvList.setAdapter(gameAdapter);
        rvList.setNestedScrollingEnabled(false);

        gameAdapter.setOnItemClickListener((adapter, view1, position) -> {
            mOperation.addParameter("game", ((TGame) adapter.getItem(position)).getId());
            mOperation.forward(GamePlayActivity.class);
        });
    }

    /**
     * 轮播图加载图片
     */
    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {

//            String url = Constants.BASE_IMAGE_URL + ((PCarousel) path).getPicUrl();
            Glide.with(context).load(((TAdvert) path).getDownloadPic()).into(imageView);
        }
    }

    @Override
    public void doBusiness(Context mContext) {
        presenter.getAdvertList();
        presenter.getTypeList();
    }

    @Override
    public void initInjector() {
        getComponent().inject(this);
    }

    @Override
    public void getAdvertListSuccess(List<TAdvert> list) {
        banner.update(list);
        banner.setImages(list);
        banner.setDelayTime(4000);
        banner.start();

        banner.setOnBannerListener(position ->
        {
            mOperation.addParameter("advert", list.get(position).getId());
            mOperation.forward(AdvertContentActivity.class);
        });

    }

    @Override
    public void getTypeListSuccess(List<TGameType> list) {
        for (int i = 0; i < list.size(); i++) {
            mTabEntities.add(new TabEntity(list.get(i).getName()));
//            mTabEntities.add(new TabEntity(mTitles[i]));
        }
        tl2.setTabData(mTabEntities);

        tl2.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                presenter.getGameList(list.get(position).getTid() );
            }
            @Override
            public void onTabReselect(int position) {

            }
        });
        presenter.getGameList(  list.get(0).getTid());
    }

    @Override
    public void getGameListSuccess(List<TGame> list) {
        if (list.size() > 0) {
            gameAdapter.setNewData(list);
        }
    }
}
