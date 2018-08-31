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
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.ywb.tuyue.R;
import com.ywb.tuyue.constants.Constants;
import com.ywb.tuyue.entity.TAdvert;
import com.ywb.tuyue.entity.TUser;
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
import com.ywb.tuyue.utils.DeviceUtils;
import com.ywb.tuyue.utils.GlideUtils;
import com.ywb.tuyue.widget.AppTitle;

import org.litepal.LitePal;

import java.text.SimpleDateFormat;
import java.util.Date;
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
    boolean result = false;

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
        if (list.size()>0){
            this.list = list;
            //这里只选取最新的两张图片
            GlideUtils.loadImageView(this,
                    list.get(0).getDownloadPic(), advertise1);
            GlideUtils.loadImageView(this,
                    list.get(1).getDownloadPic(), advertise2);
        }

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
        String phone = SPUtils.getInstance().getString(Constants.REGIST_PHONE, "");
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
                if (StringUtils.isEmpty(phone)) {
                    onDialog(CinemaActivity.class);
                } else {
                    mOperation.forward(CinemaActivity.class);
                }
                break;
            case R.id.ll_game:
                if (StringUtils.isEmpty(phone)) {
                    onDialog(GameActivity.class);
                } else {
                    mOperation.forward(GameActivity.class);
                }
                break;
            case R.id.ll_book:
                if (StringUtils.isEmpty(phone)) {
                    onDialog(BookActivity.class);
                } else {
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
    public void onDialog(Class activity) {
        String[] gender = {""};//1男2女
        String[] age = {""};//0：20以下，1:20-40，2:40-60,3:60以上

        MaterialDialog materialDialog = mOperation
                .showCustomerDialog("", R.layout.dialog_register, true);
        //不允许点击外侧关闭
        materialDialog.setCanceledOnTouchOutside(false);
        //设置关闭监听
//        materialDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
//            @Override
//            public void onCancel(DialogInterface dialog) {
////                KeyboardUtils.toggleSoftInput();
//                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
//                //如果window上view获取焦点 && view不为空
//                if(imm.isActive()&&getCurrentFocus()!=null){
//                    //拿到view的token 不为空
//                    if (getCurrentFocus().getWindowToken()!=null) {
//                        //表示软键盘窗口总是隐藏，除非开始时以SHOW_FORCED显示。
//                        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
//                    }
//                }
//            }
//        });
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
            gender[0] = "1";
        });
        chWoman.setOnClickListener(v -> {
            chWoman.setChecked(true);
            chMan.setChecked(false);
            gender[0] = "2";
        });
        below20.setOnClickListener(v -> {
            below20.setChecked(true);
            twentyTofourth.setChecked(false);
            fourthToSixty.setChecked(false);
            aboveSixty.setChecked(false);
            age[0] = "0";
        });
        twentyTofourth.setOnClickListener(v -> {
            below20.setChecked(false);
            twentyTofourth.setChecked(true);
            fourthToSixty.setChecked(false);
            aboveSixty.setChecked(false);
            age[0] = "1";
        });
        fourthToSixty.setOnClickListener(v -> {
            below20.setChecked(false);
            twentyTofourth.setChecked(false);
            fourthToSixty.setChecked(true);
            aboveSixty.setChecked(false);
            age[0] = "2";
        });
        aboveSixty.setOnClickListener(v -> {
            below20.setChecked(false);
            twentyTofourth.setChecked(false);
            fourthToSixty.setChecked(false);
            aboveSixty.setChecked(true);
            age[0] = "3";
        });

        button.setOnClickListener(v -> {

            String phone = etPhone.getText().toString();
            String code = etCode.getText().toString();
            //管理员账户
            if ("111111".equals(phone)) {
                materialDialog.cancel();
                return;
            } else if (phone.length() != 11 || code.length() != 6) {
                ToastUtils.showShort("输入格式不正确，请重新输入");
                return;
            }
            //微信验证码
            if ("973570".equals(code) || "217560".equals(code) || "285973".equals(phone)
                    || "579134".equals(code) || "497186".equals(code)) {
                SPUtils.getInstance().put(Constants.REGIST_PHONE, phone);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// HH:mm:ss
                String now = simpleDateFormat.format(new Date(System.currentTimeMillis()));
                //判断今天是否创建过此phone的Tuser数据
                TUser tUser = LitePal.where("phone = ? and createtime = ?", phone, now).findFirst(TUser.class);
                if (tUser == null) {
                    tUser = new TUser(age[0] + "",
                            now + "",
                            DeviceUtils.getUniqueId(this) + "",
                            phone + "",
                            gender[0] + "");
                    if (tUser.save()) {
                        LogUtils.e("用户数据保存成功");
                    }
                }
                materialDialog.cancel();
                mOperation.forward(activity);
            } else {
                ToastUtils.showShort("验证码不正确，请重新输入");
            }

        });

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
