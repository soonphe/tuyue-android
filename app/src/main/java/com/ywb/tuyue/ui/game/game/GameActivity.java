package com.ywb.tuyue.ui.game.game;


import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.constant.TimeConstants;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.bumptech.glide.Glide;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;
import com.ywb.tuyue.R;
import com.ywb.tuyue.base.BaseActivity;
import com.ywb.tuyue.constants.Constants;
import com.ywb.tuyue.entity.TAdvert;
import com.ywb.tuyue.entity.TGame;
import com.ywb.tuyue.entity.TGameType;
import com.ywb.tuyue.entity.TStats;
import com.ywb.tuyue.entity.TabEntity;
import com.ywb.tuyue.ui.adapter.GameAdapter;
import com.ywb.tuyue.ui.advert.advertise.AdvertContentActivity;
import com.ywb.tuyue.ui.data.DataContract;
import com.ywb.tuyue.ui.data.DataPresenter;
import com.ywb.tuyue.ui.game.gameplay.GamePlayActivity;
import com.ywb.tuyue.widget.AppTitle;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;


/**
 * @Author soonphe
 * @Date 2018-08-21 13:12
 * @Description 游戏列表
 */
public class GameActivity extends BaseActivity implements GameContract.View, DataContract.View {


    @Inject
    GamePresenter presenter;
    @Inject
    DataPresenter dataPresenter;

    @BindView(R.id.app_title_id)
    AppTitle appTitle;
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
    private long stayTime;//模块停留时长

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
        dataPresenter.attachView(this);
        stayTime = System.currentTimeMillis();
        //banner图
        banner.setImageLoader(new GlideImageLoader());


        gameAdapter = new GameAdapter(R.layout.item_game);
        rvList.setLayoutManager(new GridLayoutManager(this, 3));
        rvList.addItemDecoration(new SpaceDecoration(10));
        rvList.setAdapter(gameAdapter);
        rvList.setNestedScrollingEnabled(false);

        gameAdapter.setOnItemClickListener((adapter, view1, position) -> {
            mOperation.addParameter("game", ((TGame) adapter.getItem(position)).getTid());
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        appTitle.getStatusLine().unregisterBroadcast();

        //停留时长统计:单位S
        long stayDataTime = TimeUtils.getTimeSpan(stayTime, System.currentTimeMillis(), TimeConstants.SEC);
        String phone = SPUtils.getInstance().getString(Constants.REGIST_PHONE, "");
        //判断这里是否存在用户，如果存在则要记录数据
        if (!"111111".equals(phone)) {
            //判断今天是否创建过统计数据——有数据则更新数据+1
            TStats tOpen = LitePal.where("phone = ?", phone + "").order("id desc").findFirst(TStats.class);
            if (tOpen != null) {
                tOpen.setGametime((int) (tOpen.getGametime() + stayDataTime));
                boolean result = tOpen.save();
                //判断当前网络可用且用户数据保存成功
                if (result) {
                    //上传所有数据
                    dataPresenter.uploadData(tOpen);
                }
            }
        }
    }

    @Override
    public void uploadDataSuccess() {
        LogUtils.e("电影停留上传成功");
    }
}
