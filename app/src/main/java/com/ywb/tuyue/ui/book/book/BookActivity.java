package com.ywb.tuyue.ui.book.book;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.blankj.utilcode.constant.TimeConstants;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
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
import com.ywb.tuyue.constants.Constants;
import com.ywb.tuyue.entity.TAdvert;
import com.ywb.tuyue.entity.TBook;
import com.ywb.tuyue.entity.TBookType;
import com.ywb.tuyue.entity.TStats;
import com.ywb.tuyue.entity.TabEntity;
import com.ywb.tuyue.ui.adapter.BookAdapter;
import com.ywb.tuyue.ui.advert.advertise.AdvertContentActivity;
import com.ywb.tuyue.ui.book.bookread.BookreadActivity;
import com.ywb.tuyue.ui.data.DataContract;
import com.ywb.tuyue.ui.data.DataPresenter;
import com.ywb.tuyue.ui.mvp.BaseActivity;
import com.ywb.tuyue.widget.AppTitle;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class BookActivity extends BaseActivity implements BookContract.BookView, DataContract.View {

    @Inject
    BookPresenter presenter;
    @Inject
    DataPresenter dataPresenter;

    @BindView(R.id.app_title_id)
    AppTitle appTitle;
    @BindView(R.id.bookBanner)
    Banner banner;
    @BindView(R.id.tl_2)
    CommonTabLayout tl2;
    //    @BindView(R.id.bookTab)
//    TabLayout bookTab;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.sm_refresh)
    SmartRefreshLayout smRefresh;

    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private BookAdapter bookAdapter;
    private long stayTime;//模块停留时长

    @Override
    public int bindLayout() {
        return R.layout.activity_book;
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

        bookAdapter = new BookAdapter(R.layout.item_book);
        rvList.setLayoutManager(new GridLayoutManager(this, 5));
        rvList.addItemDecoration(new SpaceDecoration(10));
        rvList.setAdapter(bookAdapter);
        rvList.setNestedScrollingEnabled(false);

        bookAdapter.setOnItemClickListener((adapter, view1, position) -> {
            mOperation.addParameter("book", ((TBook) adapter.getItem(position)).getId());
            mOperation.forward(BookreadActivity.class);
        });
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
    public void getTypeListSuccess(List<TBookType> list) {
        for (int i = 0; i < list.size(); i++) {
            mTabEntities.add(new TabEntity(list.get(i).getName()));
        }
        tl2.setTabData(mTabEntities);

        tl2.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                LogUtils.e("当前typename:" + (mTabEntities.get(position)).getTabTitle());
                presenter.getBookList(list.get(position).getTid());
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        presenter.getBookList(list.get(0).getTid());
    }

    @Override
    public void getBookListSuccess(List<TBook> list) {
        if (list.size() > 0) {
            bookAdapter.setNewData(list);
        }
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
    public void startLoading() {

    }

    @Override
    public void endLoading() {

    }

    @Override
    public void onError(String error) {

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
                tOpen.setBooktime((int) (tOpen.getBooktime() + stayDataTime));
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
        LogUtils.e("书吧停留上传成功");
    }

}
