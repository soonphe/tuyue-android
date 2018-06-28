package com.ywb.tuyue.ui.main;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import com.blankj.utilcode.util.LogUtils;
import com.ywb.tuyue.R;
import com.ywb.tuyue.entity.CardBean;
import com.ywb.tuyue.entity.TAdvert;
import com.ywb.tuyue.ui.advertise.AdvertiseDetailActivity;
import com.ywb.tuyue.ui.book.BookActivity;
import com.ywb.tuyue.ui.cinema.CinemaActivity;
import com.ywb.tuyue.ui.city.CityActivity;
import com.ywb.tuyue.ui.food.FoodActivity;
import com.ywb.tuyue.ui.game.GameActivity;
import com.ywb.tuyue.ui.music.MusicActivity;
import com.ywb.tuyue.ui.mvp.BaseActivity;
import com.ywb.tuyue.ui.settings.SettingsActivity;
import com.ywb.tuyue.ui.subway.SubwayActivity;
import com.ywb.tuyue.widget.card.Card1;
import com.ywb.tuyue.widget.card.Card2;
import com.ywb.tuyue.widget.card.CustomerCard;
import com.ywb.tuyue.widget.head.HeaderView;
import org.litepal.tablemanager.Connector;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.OnClick;
/**
 * @Author wcystart
 * @Date 2018-6-25  14.06
 * @Description 主界面
 */
public class MainActivity extends BaseActivity implements MainContract.MainView {
    @BindView(R.id.header)
    HeaderView mHeader;
    @BindView(R.id.head_back)
    ImageButton mBack; //返回
    @BindView(R.id.head_setting)
    ImageButton mSettings; //设置
    @BindView(R.id.advertise1)
    ImageView mAdvertise1;//广告一
    @BindView(R.id.advertise2)
    ImageView mAdvertise2;//广告二
    @BindView(R.id.movie)
    Card1 mMovie; //电影
    @BindView(R.id.game)
    Card1 mGame;//游戏
    @BindView(R.id.music)
    Card2 mMusic; //音乐
    @BindView(R.id.food)
    Card2 mFood; //点餐
    @BindView(R.id.book)
    Card2 mBook; //书吧
    @BindView(R.id.city)
    Card2 mCity; //城市
    @BindView(R.id.subway)
    Card2 mSubway; //成铁风采
    private static List<CardBean> cardBeans = new ArrayList<>();
    int[] cardIds = {R.id.movie, R.id.game, R.id.music, R.id.food, R.id.book,R.id.city,R.id.subway};
    @Inject
    MainPresenter mMainPresenter;

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

    @Override
    public void initView(View view) {
        mMainPresenter.attachView(this);
        mHeader.setLeftBtnVisiable(View.GONE);
        mHeader.setImgTitleOnly(R.drawable.main_title);
        mHeader.setRightBtnClickListsner(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOperation.forwardAndFinish(SettingsActivity.class,LEFT_RIGHT);
            }
        });

        mAdvertise1.setImageResource(R.drawable.test_001);
        mAdvertise2.setImageResource(R.drawable.test_02);

//        cardBeans.add(new CardBean(R.mipmap.dianying,R.string.EMovieName,R.string.ZMovieName));
//        cardBeans.add(new CardBean(R.mipmap.game,R.string.EGameName,R.string.ZGameName));
//        cardBeans.add(new CardBean(R.mipmap.music,R.string.EMusicName,R.string.ZMusicName));
//        cardBeans.add(new CardBean(R.mipmap.food,R.string.EFoodName,R.string.ZFoodName));
//        cardBeans.add(new CardBean(R.mipmap.book,R.string.EBookName,R.string.ZBookName));
//        cardBeans.add(new CardBean(R.mipmap.city,R.string.ECityName,R.string.ZCityName));
//        cardBeans.add(new CardBean(R.mipmap.subway,R.string.ESubwayName,R.string.ZSubwayName));

        cardBeans.add(new CardBean(R.mipmap.dianying,"Cinema","影院"));
        cardBeans.add(new CardBean(R.mipmap.game,"Game","游戏"));
        cardBeans.add(new CardBean(R.mipmap.music,"Music","音乐"));
        cardBeans.add(new CardBean(R.mipmap.food,"Order","点餐"));
        cardBeans.add(new CardBean(R.mipmap.book,"Book","书吧"));
        cardBeans.add(new CardBean(R.mipmap.city,"City","城市"));
        cardBeans.add(new CardBean(R.mipmap.subway,"Suburban style","成铁风采"));
        for (int i = 0; i < cardIds.length; i++) {
            CustomerCard customerCard = findViewById(cardIds[i]);
            customerCard.setCardBean(cardBeans.get(i));
        }
    }

    @Override
    public void doBusiness(Context mContext) {
        // TODO: 2018/6/25 处理业务逻辑
        mMainPresenter.getMain();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @OnClick({R.id.advertise1, R.id.advertise2, R.id.movie, R.id.game, R.id.music, R.id.food, R.id.book, R.id.city, R.id.subway})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.advertise1://广告图标一

                SQLiteDatabase db = Connector.getDatabase();//数据库创建完成
                
                 mOperation.forwardAndFinish(AdvertiseDetailActivity.class,LEFT_RIGHT);
                break;
            case R.id.advertise2://广告图标二

                mOperation.forwardAndFinish(AdvertiseDetailActivity.class,LEFT_RIGHT);
                break;
            case R.id.movie: //影院
                mOperation.forwardAndFinish(CinemaActivity.class,LEFT_RIGHT);
                break;
            case R.id.game://游戏
                mOperation.forwardAndFinish(GameActivity.class,LEFT_RIGHT);
                break;
            case R.id.music: //音乐
                mOperation.forwardAndFinish(MusicActivity.class,LEFT_RIGHT);
                break;
            case R.id.food://点餐
                mOperation.forwardAndFinish(FoodActivity.class,LEFT_RIGHT);
                break;
            case R.id.book://书吧
                mOperation.forwardAndFinish(BookActivity.class,LEFT_RIGHT);
                break;
            case R.id.city://城市
                mOperation.forwardAndFinish(CityActivity.class,LEFT_RIGHT);
                break;
            case R.id.subway://成铁风采
                mOperation.forwardAndFinish(SubwayActivity.class,LEFT_RIGHT);
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

    @Override
    public void getMainSuccess(List<TAdvert> dataBeans) {
        // TODO: 2018/6/27 网络请求回调成功
        LogUtils.e(dataBeans.get(0).getTitle()+"///"); //途悦2
        LogUtils.e(dataBeans.get(1).getTitle()+"///"); //途悦1
        LogUtils.e(dataBeans.get(0).getPicurl()+"///"); // /pictures/20180511/1526026781.jpg///
        LogUtils.e(dataBeans.get(1).getPicurl()+"///"); // /pictures/20180511/1526026819.jpg///
        //将拿到的url进行数据库存储
        //创建数据库
    }
}
