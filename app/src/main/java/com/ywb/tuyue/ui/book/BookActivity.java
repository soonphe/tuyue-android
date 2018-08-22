package com.ywb.tuyue.ui.book;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.View;

import com.youth.banner.Banner;
import com.ywb.tuyue.R;
import com.ywb.tuyue.ui.mvp.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class BookActivity extends BaseActivity implements BookContract.BookView{

//    @BindView(R.id.header)
//    HeaderView mHeader;
    @BindView(R.id.bookBanner)
    Banner mBookBanner;
    @BindView(R.id.bookTab)
    TabLayout mBookTab;
//    @BindView(R.id.bookPager)
//    CustomViewPager mBookViewPager;

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

//        mHeader.setTitle(R.string.book);
//        mHeader.setRightBtnVisiable(View.GONE);
//        mHeader.setLeftBtnClickListsner(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mOperation.forwardAndFinish(MainActivity.class, LEFT_RIGHT);
//            }
//        });

      mBookPresenter.attachView(this);
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public void initInjector() {
     getComponent().inject(this);
    }



    @OnClick({R.id.bookBanner, R.id.bookTab})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bookBanner:
                break;
            case R.id.bookTab:
                break;
//            case R.id.bookPager:
//                break;
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
