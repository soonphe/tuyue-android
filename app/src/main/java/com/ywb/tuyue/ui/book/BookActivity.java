package com.ywb.tuyue.ui.book;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ywb.tuyue.R;
import com.ywb.tuyue.entity.TBookData;
import com.ywb.tuyue.entity.TBookType;
import com.ywb.tuyue.ui.main.MainActivity;
import com.ywb.tuyue.ui.mvp.BaseActivity;

import com.ywb.tuyue.widget.CustomViewPager;
import com.ywb.tuyue.widget.bgabanner.BGABanner;
import com.ywb.tuyue.widget.head.HeaderView;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.OnClick;

public class BookActivity extends BaseActivity implements BookContract.BookView,BGABanner.Adapter, BGABanner.OnItemClickListener{

    @BindView(R.id.header)
    HeaderView mHeader;
    @BindView(R.id.bookBanner)
    BGABanner mBookBanner;
    @BindView(R.id.bookTab)
    TabLayout mBookTab;
    @BindView(R.id.bookPager)
    CustomViewPager mBookViewPager;

    @Inject
    BookPresenter mBookPresenter;
    private List<String> mBookBannerUrls = new ArrayList<>();  //里列表地址

    @Override
    public int bindLayout() {
        return R.layout.activity_book;
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public void initView(View view) {
        //测试数据
        mBookBannerUrls.add("http://192.168.1.6/pictures/20180511/1526026819.jpg");
        mBookBannerUrls.add("http://192.168.1.6/pictures/20180511/1526026819.jpg");

        mHeader.setTitle(R.string.book);
        mHeader.setRightBtnVisiable(View.GONE);
        mHeader.setLeftBtnClickListsner(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOperation.forwardAndFinish(MainActivity.class, LEFT_RIGHT);
            }
        });

      mBookPresenter.attachView(this);
    }

    @Override
    public void doBusiness(Context mContext) {
        // TODO: 2018/6/29 业务逻辑
        mBookPresenter.getBookType();
        mBookPresenter.getBookData();
    }

    @Override
    public void initInjector() {
     getComponent().inject(this);
    }



    @OnClick({R.id.bookBanner, R.id.bookTab, R.id.bookPager})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bookBanner:
                break;
            case R.id.bookTab:
                break;
            case R.id.bookPager:
                break;
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

    /**
     * 获取所有的书籍类型
     * @param tBookTypes
     */
    @Override
    public void getBookTypeSuccess(List<TBookType> tBookTypes) {
        setBookBanner();
    }

    private void setBookBanner() {
        if (mBookBannerUrls != null && !mBookBannerUrls.isEmpty()) {
            mBookBanner.setData(mBookBannerUrls, null);
        }
        mBookBanner.setAdapter(this);
        mBookBanner.setOnItemClickListener(this);
    }
    /**
     * 获取所有的书籍数据
     * @param tBookData
     */
    @Override
    public void getBookDataSuccess(List<TBookData> tBookData) {

    }

    /**
     *Banner  书吧广告图展示
     * @param banner
     * @param view
     * @param model
     * @param position
     */
    @Override
    public void fillBannerItem(BGABanner banner, View view, Object model, int position) {
        Glide.with(getContext()).load(mBookBannerUrls.get(0)).into((ImageView) view);
    }

    @Override
    public void onBannerItemClick(BGABanner banner, View view, Object model, int position) {

    }
}
