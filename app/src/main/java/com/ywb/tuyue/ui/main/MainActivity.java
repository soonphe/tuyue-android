package com.ywb.tuyue.ui.main;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.ywb.tuyue.R;
import com.ywb.tuyue.constants.Constants;
import com.ywb.tuyue.entity.TAdvert;
import com.ywb.tuyue.entity.TStats;
import com.ywb.tuyue.entity.TUser;
import com.ywb.tuyue.ui.advert.AdvertContract;
import com.ywb.tuyue.ui.advert.AdvertPresenter;
import com.ywb.tuyue.ui.advert.advertise.AdvertContentActivity;
import com.ywb.tuyue.ui.article.ArticleActivity;
import com.ywb.tuyue.ui.book.book.BookActivity;
import com.ywb.tuyue.ui.city.city.CityActivity;
import com.ywb.tuyue.ui.data.DataContract;
import com.ywb.tuyue.ui.data.DataPresenter;
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

import static com.ywb.tuyue.constants.Constants.IS_MOBILE;
import static com.ywb.tuyue.constants.Constants.NETWORK_AVAILABLE;

/**
 * @Author soonphe
 * @Date 2018-08-30 10:37
 * @Description 首页
 */
public class MainActivity extends BaseActivity implements AdvertContract.View, DataContract.View, MainContract.View {

    @Inject
    AdvertPresenter advertPresenter;
    @Inject
    DataPresenter dataPresenter;
    @Inject
    MainPresenter presenter;

    @BindView(R.id.app_title)
    AppTitle appTitle;
    @BindView(R.id.advertise1)
    ImageView advertise1;
    @BindView(R.id.advertise2)
    ImageView advertise2;
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
        dataPresenter.attachView(this);
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
        if (list.size() > 0) {
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

    /**
     * 不需要用户验证的模块
     *
     * @param view
     */
    @OnClick({R.id.app_bar_btn2, R.id.advertise1, R.id.advertise2})
    public void onClicked(View view) {
        switch (view.getId()) {
            case R.id.app_bar_btn2:
                mOperation.forward(SettingActivity.class);
                break;
            case R.id.advertise1:
                // TODO: 2018-09-03 这里需要提供测试用户数据清除
                //清空用户手机号
                SPUtils.getInstance().remove(Constants.REGIST_PHONE);
                mOperation.addParameter("advert", list.get(0).getId());
                mOperation.forward(AdvertContentActivity.class);
                break;
            case R.id.advertise2:
                mOperation.addParameter("advert", list.get(1).getId());
                mOperation.forward(AdvertContentActivity.class);
                break;
        }
    }

    /**
     * 需要用户验证的模块
     *
     * @param view
     */
    @OnClick({R.id.ll_movie, R.id.ll_game, R.id.ll_book, R.id.ll_food, R.id.ll_city, R.id.ll_subway})
    public void onViewClicked(View view) {
        String phone = SPUtils.getInstance().getString(Constants.REGIST_PHONE, "");

        //获取该手机号最新一条统计信息
        TStats tStats = LitePal.where("phone = ? ", phone).order("id desc").findFirst(TStats.class);
        //判断用户注册是否过期&&数据库不存在此手机号
        if (StringUtils.isEmpty(phone) || tStats == null) {
            //如果没有数据，则直接弹窗注册
            onDialog();
        } else {
            switch (view.getId()) {
                case R.id.ll_movie:
                    //更新统计信息+跳转activity
                    tStats.setMovies(tStats.getMovies() + 1);
                    forwardActivity(tStats, CinemaActivity.class);
                    break;
                case R.id.ll_game:
                    tStats.setGame(tStats.getGame() + 1);
                    forwardActivity(tStats, GameActivity.class);
                    break;
                case R.id.ll_book:
                    tStats.setBook(tStats.getBook() + 1);
                    forwardActivity(tStats, BookActivity.class);
                    break;
                case R.id.ll_food:
                    tStats.setFood(tStats.getFood() + 1);
                    forwardActivity(tStats, FoodActivity.class);
                    break;
                case R.id.ll_city:
                    tStats.setCity(tStats.getCity() + 1);
                    forwardActivity(tStats, CityActivity.class);
                    break;
                case R.id.ll_subway:
                    tStats.setSubway(tStats.getSubway() + 1);
                    forwardActivity(tStats, ArticleActivity.class);
                    break;
            }
        }
    }

    /**
     * 跳转activity+保存统计数据
     *
     * @param activity
     */
    public void forwardActivity(TStats tStats, Class activity) {
        boolean result = tStats.save();
        //判断当前网络可用且统计数据保存成功
        if ( result) {
            //上传所有数据
            dataPresenter.uploadData(tStats);
        }
        mOperation.forward(activity);
    }

    @Override
    public void uploadDataSuccess() {
        LogUtils.e("统计数据上传成功");
    }

    @Override
    public void uploadUserSuccess() {
        LogUtils.e("用户数据上传成功");
    }

    /**
     * 展示注册对话框
     */
    public void onDialog() {
        int[] gender = {0};//1男2女
        int[] age = {0};//0：20以下，1:20-40，2:40-60,3:60以上

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
            gender[0] = 1;
        });
        chWoman.setOnClickListener(v -> {
            chWoman.setChecked(true);
            chMan.setChecked(false);
            gender[0] = 2;
        });
        below20.setOnClickListener(v -> {
            below20.setChecked(true);
            twentyTofourth.setChecked(false);
            fourthToSixty.setChecked(false);
            aboveSixty.setChecked(false);
            age[0] = 0;
        });
        twentyTofourth.setOnClickListener(v -> {
            below20.setChecked(false);
            twentyTofourth.setChecked(true);
            fourthToSixty.setChecked(false);
            aboveSixty.setChecked(false);
            age[0] = 1;
        });
        fourthToSixty.setOnClickListener(v -> {
            below20.setChecked(false);
            twentyTofourth.setChecked(false);
            fourthToSixty.setChecked(true);
            aboveSixty.setChecked(false);
            age[0] = 2;
        });
        aboveSixty.setOnClickListener(v -> {
            below20.setChecked(false);
            twentyTofourth.setChecked(false);
            fourthToSixty.setChecked(false);
            aboveSixty.setChecked(true);
            age[0] = 3;
        });

        button.setOnClickListener(v -> {
            KeyboardUtils.hideSoftInput(this);
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
            if ("973570".equals(code)
                    || "217560".equals(code)
                    || "285973".equals(code)
                    || "579134".equals(code)
                    || "497186".equals(code)) {
                SPUtils.getInstance().put(Constants.REGIST_PHONE, phone);

                String date = TimeUtils.millis2String(System.currentTimeMillis(),
                        new SimpleDateFormat("yyyy-MM-dd"));
                String dataAndTime = TimeUtils.millis2String(System.currentTimeMillis());
                //新建用户数据并保存
                TUser tUser = new TUser(
                        age[0],
                        dataAndTime + "",
                        DeviceUtils.getUniqueId(this) + "",
                        phone + "",
                        gender[0]
                );
                if (tUser.save()) {
                    SPUtils.getInstance().put(Constants.REGIST_PHONE, phone + "");
                    //上传所有数据
                    presenter.uploadUser(tUser);

                    //用户注册成功后创建统计数据
                    TStats stats = new TStats();
                    stats.setPhone(phone);
                    stats.setRegtime(dataAndTime);
                    stats.setCreatedate(date);
                    stats.setIsmobile(SPUtils.getInstance().getBoolean(IS_MOBILE));
                    stats.setImcode(DeviceUtils.getUniqueId(this) + "");
                    stats.save();
                }

                materialDialog.cancel();
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
