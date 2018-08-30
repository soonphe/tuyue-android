package com.ywb.tuyue.ui.main;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.blankj.utilcode.util.BarUtils;
import com.ywb.tuyue.R;
import com.ywb.tuyue.entity.TAdvert;
import com.ywb.tuyue.ui.advert.AdvertContract;
import com.ywb.tuyue.ui.advert.AdvertPresenter;
import com.ywb.tuyue.ui.advert.advertise.AdvertContentActivity;
import com.ywb.tuyue.ui.article.ArticleActivity;
import com.ywb.tuyue.ui.book.book.BookActivity;
import com.ywb.tuyue.ui.city.city.CityActivity;
import com.ywb.tuyue.ui.food.FoodActivity;
import com.ywb.tuyue.ui.game.game.GameActivity;
import com.ywb.tuyue.ui.mvp.BaseActivity;
import com.ywb.tuyue.ui.setting.SettingActivity;
import com.ywb.tuyue.ui.video.CinemaActivity;
import com.ywb.tuyue.utils.GlideUtils;
import com.ywb.tuyue.widget.AppTitle;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Author soonphe
 * @Date 2018-08-30 10:37
 * @Description 首页
 */
public class MainActivity extends BaseActivity implements AdvertContract.View, MainContract.View {

    @Inject
    AdvertPresenter advertPresenter;
    @Inject
    MainPresenter presenter;

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

    List<TAdvert> list;

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
        advertPresenter.attachView(this);
        presenter.attachView(this);

        advertise1.setImageResource(R.mipmap.main_header_01);
        advertise2.setImageResource(R.mipmap.main_header_02);

    }

    @Override
    public void doBusiness(Context mContext) {
        advertPresenter.getAdvertListByType(3);
    }

    @Override
    public void getAdvertListSuccess(List<TAdvert> list) {
        this.list = list;
        //这里只选取最新的两张图片
        GlideUtils.loadImageView(this,
                list.get(0).getDownloadPic(), advertise1);
        GlideUtils.loadImageView(this,
                list.get(1).getDownloadPic(), advertise2);
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
            R.id.ll_book, R.id.ll_food, R.id.ll_city, R.id.ll_subway, R.id.app_bar_btn2})
    public void onViewClicked(View view) {
        boolean result;
        switch (view.getId()) {
            case R.id.app_bar_btn2:
                mOperation.forward(SettingActivity.class);
                break;
            case R.id.advertise1:
                mOperation.addParameter("advert", list.get(0).getId());
                mOperation.forward(AdvertContentActivity.class);
                break;
            case R.id.advertise2:
                mOperation.addParameter("advert", list.get(1).getId());
                mOperation.forward(AdvertContentActivity.class);
                break;
            case R.id.iv_parentView:
                mOperation.forward(AdvertContentActivity.class);
                break;
            case R.id.ll_movie:
                result = onDialog();
                if (result) {
                    mOperation.forward(CinemaActivity.class);
                }
                break;
            case R.id.ll_game:
                result = onDialog();
                if (result) {
                    mOperation.forward(GameActivity.class);
                }
                break;
            case R.id.ll_book:
                result = onDialog();
                if (result) {
                    mOperation.forward(BookActivity.class);
                }
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

    /**
     * 展示注册对话框
     */
    public boolean onDialog() {
        final boolean[] result = {false};
        String gender = "";//1男2女
        String age = "";//0：20以下，1:20-40，2:40-60,3:60以上

        MaterialDialog materialDialog = mOperation.showCustomerDialog("", R.layout.dialog_register);
        CheckBox chMan = materialDialog.getCustomView().findViewById(R.id.ck_man);
        CheckBox chWoman = materialDialog.getCustomView().findViewById(R.id.ck_woman);
        CheckBox below20 = materialDialog.getCustomView().findViewById(R.id.ck_below20);
        CheckBox twentyTofourth = materialDialog.getCustomView().findViewById(R.id.ck_twentyTofourth);
        CheckBox fourthToSixty = materialDialog.getCustomView().findViewById(R.id.ck_fourthToSixty);
        CheckBox aboveSixty = materialDialog.getCustomView().findViewById(R.id.ck_aboveSixty);
        TextView etPhone = materialDialog.getCustomView().findViewById(R.id.et_phone);
        TextView etCode = materialDialog.getCustomView().findViewById(R.id.et_Code);
        Button button = materialDialog.getCustomView().findViewById(R.id.bt_Save);
        chMan.setOnClickListener(v -> {
            chMan.setChecked(true);
            chWoman.setChecked(false);
        });
        chWoman.setOnClickListener(v -> {
            chWoman.setChecked(true);
            chMan.setChecked(false);
        });
        below20.setOnClickListener(v -> {
            below20.setChecked(true);
            twentyTofourth.setChecked(false);
            fourthToSixty.setChecked(false);
            aboveSixty.setChecked(false);
        });
        below20.setOnClickListener(v -> {
            below20.setChecked(false);
            twentyTofourth.setChecked(true);
            fourthToSixty.setChecked(false);
            aboveSixty.setChecked(false);
        });
        below20.setOnClickListener(v -> {
            below20.setChecked(false);
            twentyTofourth.setChecked(false);
            fourthToSixty.setChecked(true);
            aboveSixty.setChecked(false);
        });
        below20.setOnClickListener(v -> {
            below20.setChecked(false);
            twentyTofourth.setChecked(false);
            fourthToSixty.setChecked(false);
            aboveSixty.setChecked(true);
        });

        button.setOnClickListener(v -> {
            result[0] = true;
            materialDialog.cancel();
        });
        return result[0];

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
