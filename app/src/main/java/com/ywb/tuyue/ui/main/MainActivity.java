package com.ywb.tuyue.ui.main;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.BarUtils;
import com.ywb.tuyue.R;
import com.ywb.tuyue.ui.advertise.AdvertiseDetailActivity;
import com.ywb.tuyue.ui.article.ArticleActivity;
import com.ywb.tuyue.ui.book.book.BookActivity;
import com.ywb.tuyue.ui.video.CinemaActivity;
import com.ywb.tuyue.ui.city.city.CityActivity;
import com.ywb.tuyue.ui.food.FoodActivity;
import com.ywb.tuyue.ui.game.game.GameActivity;
import com.ywb.tuyue.ui.mvp.BaseActivity;
import com.ywb.tuyue.ui.setting.SettingActivity;
import com.ywb.tuyue.widget.AppTitle;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Author wcystart
 * @Date 2018-6-25  14.06
 * @Description 主界面
 */
public class MainActivity extends BaseActivity {
    //    @BindView(R.id.header)
//    HeaderView mHeader;
    @BindView(R.id.app_title)
    AppTitle appTitle;
    @BindView(R.id.advertise1)
    ImageView advertise1;
    @BindView(R.id.advertise2)
    ImageView advertise2;
    @BindView(R.id.iv_parentView)
    LinearLayout ivParentView;
    @BindView(R.id.ll_movie)
    LinearLayout llMovie;
    @BindView(R.id.ll_game)
    LinearLayout llGame;
    @BindView(R.id.ll_book)
    LinearLayout llBook;
    @BindView(R.id.ll_food)
    LinearLayout llFood;
    @BindView(R.id.ll_city)
    LinearLayout llCity;
    @BindView(R.id.ll_subway)
    LinearLayout llSubway;


    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public void initInjector() {
        getComponent().inject(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void initView(View view) {

        BarUtils.setStatusBarAlpha(this, 0);
        advertise1.setImageResource(R.mipmap.main_header_01);
        advertise2.setImageResource(R.mipmap.main_header_02);

    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @OnClick({R.id.advertise1, R.id.advertise2, R.id.iv_parentView, R.id.ll_movie, R.id.ll_game,
            R.id.ll_book, R.id.ll_food, R.id.ll_city, R.id.ll_subway,R.id.app_bar_btn2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.app_bar_btn2:
                mOperation.forward(SettingActivity.class);
                break;
            case R.id.advertise1:
                //  SQLiteDatabase db = Connector.getDatabase();//数据库创建完成
                mOperation.forward(AdvertiseDetailActivity.class);
                break;
            case R.id.advertise2:
                mOperation.forward(AdvertiseDetailActivity.class);
                break;
            case R.id.iv_parentView:
                mOperation.forward(AdvertiseDetailActivity.class);
                break;
            case R.id.ll_movie:
                mOperation.forward(CinemaActivity.class);
                break;
            case R.id.ll_game:
                mOperation.forward(GameActivity.class);
                break;
            case R.id.ll_book:
                mOperation.forward(BookActivity.class);
                break;
            case R.id.ll_food:
                mOperation.forward(FoodActivity.class);
                break;
            case R.id.ll_city:
                mOperation.forward(CityActivity.class);
                break;
            case R.id.ll_subway:
                mOperation.forward(ArticleActivity.class);
                break;

        }
    }

}
