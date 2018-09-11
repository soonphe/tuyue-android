package com.ywb.tuyue.ui.videoplayer;


import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.EmptyUtils;
import com.blankj.utilcode.util.LogUtils;
import com.bumptech.glide.Glide;
import com.ywb.tuyue.R;
import com.ywb.tuyue.entity.TAdvert;
import com.ywb.tuyue.entity.TMovie;
import com.ywb.tuyue.entity.TVideo;
import com.ywb.tuyue.ui.advert.AdvertContract;
import com.ywb.tuyue.ui.advert.AdvertPresenter;
import com.ywb.tuyue.ui.mvp.BaseActivity;
import com.ywb.tuyue.utils.GlideUtils;

import org.litepal.LitePal;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jzvd.JZMediaManager;
import cn.jzvd.JZUserAction;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static cn.jzvd.JZVideoPlayer.CURRENT_STATE_AUTO_COMPLETE;
import static cn.jzvd.JZVideoPlayer.CURRENT_STATE_NORMAL;
import static cn.jzvd.JZVideoPlayer.CURRENT_STATE_PAUSE;
import static cn.jzvd.JZVideoPlayer.CURRENT_STATE_PLAYING;
import static cn.jzvd.JZVideoPlayer.CURRENT_STATE_PREPARING;
import static cn.jzvd.JZVideoPlayer.releaseAllVideos;

/**
 * @Author soonphe
 * @Date 2018-08-24 14:52
 * @Description 视频播放页
 */
public class VideoPlayerActivity extends BaseActivity implements AdvertContract.View, VideoPlayerContract.View {

    @BindView(R.id.movie_name)
    TextView movieName;
    @BindView(R.id.movie_director)
    TextView movieDirector;
    @BindView(R.id.movie_actor)
    TextView movieActor;
    @BindView(R.id.movie_introduct)
    TextView movieIntroduct;
    @BindView(R.id.videoplayer)
    JZVideoPlayerStandard videoplayer;
    @BindView(R.id.my_custom_advert)
    ImageView advert;
    @BindView(R.id.my_custom_second)
    TextView second;

    //video的ID
    int id;
    TVideo tVideo;
    TMovie tMovie;
    List<TAdvert> list;
    //设置播放电影之前的广告图
    int secondStr = 4;

    @Inject
    AdvertPresenter advertPresenter;


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
        return R.layout.activity_videoplayer;
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public void initView(View view) {

        BarUtils.setStatusBarAlpha(this, 0);
        advertPresenter.attachView(this);
        //取传过来的分类ID
        if (EmptyUtils.isNotEmpty(mOperation.getParameter("video"))) {
            id = (int) mOperation.getParameter("video");
            tVideo = LitePal.find(TVideo.class, id);
            movieName.setText(tVideo.getName());
            movieDirector.setText(tVideo.getDirector());
            movieActor.setText(tVideo.getActor());
            movieIntroduct.setText(tVideo.getProfile());

            videoplayer.setUp(tVideo.getDownloadFile() + "",
                    JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL,
                    tVideo.getName() + "");
            //设置视频第一帧缩略图
            Glide.with(this)
                    .load(tVideo.getDownloadPic())
                    .into(videoplayer.thumbImageView);

        }
        if (EmptyUtils.isNotEmpty(mOperation.getParameter("movie"))) {
            id = (int) mOperation.getParameter("movie");
            tMovie = LitePal.find(TMovie.class, id);
            movieName.setText(tMovie.getFile_name());
            movieDirector.setText(tMovie.getDirect());
            movieActor.setText(tMovie.getStarring());
            movieIntroduct.setText(tMovie.getDrama_cn());

            videoplayer.setUp(tMovie.getDownloadFile() + "",
                    JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL,
                    tMovie.getFile_name() + "");
            //设置视频第一帧缩略图
            Glide.with(this)
                    .load(tMovie.getDownloadPic())
                    .into(videoplayer.thumbImageView);

            //从数据库获取暂停广告
            videoplayer.startButton.setOnClickListener(v -> {
                if (videoplayer.currentState == CURRENT_STATE_NORMAL) {
                    videoplayer.setVisibility(GONE);
                    advert.setVisibility(VISIBLE);
                    second.setVisibility(VISIBLE);
                    Observable//
                            .interval(0, 1, TimeUnit.SECONDS)//设置0延迟，每隔一秒发送一条数据
                            .take(secondStr + 1).map(aLong -> secondStr - aLong)//设置循环secondStr+1次
                            .observeOn(AndroidSchedulers.mainThread())//
                            .subscribeOn(Schedulers.io())//
                            .subscribe(new Observer<Long>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(Long aLong) {
                                    second.setText("广告剩余 " + aLong + " 秒");
                                }

                                @Override
                                public void onError(Throwable e) {

                                }

                                @Override
                                public void onComplete() {
                                    videoplayer.setVisibility(VISIBLE);
                                    advert.setVisibility(GONE);
                                    second.setVisibility(GONE);
                                    //开始播放
                                    videoplayer.startVideo();
                                }
                            });

                }else if (videoplayer.currentState == CURRENT_STATE_PLAYING){
//                    videoplayer.setState(CURRENT_STATE_NORMAL);
                    JZMediaManager.pause();
                    videoplayer.onStatePause();
//                    releaseAllVideos();   //重置所有播放
                    LogUtils.e("暂停");
                } else if (videoplayer.currentState == CURRENT_STATE_PAUSE) {
                    JZMediaManager.start();
                    videoplayer.onStatePlaying();
                    LogUtils.e("播放暂停广告");
                } else if (videoplayer.currentState == CURRENT_STATE_AUTO_COMPLETE) {
                    videoplayer.startVideo();
                }
            });
        }

    }

    @OnClick(R.id.my_custom_second)
    public void onViewClicked() {


    }

    @Override
    public void onBackPressed() {
        if (videoplayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        videoplayer.releaseAllVideos();
    }

    /**
     * 避免切换为竖屏
     *
     * @param newConfig
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            LogUtils.e(TAG, "竖屏");
        }
//        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            LogUtils.e(TAG, "横屏");
//        }
    }

    @Override
    public void doBusiness(Context mContext) {
        advertPresenter.getAdvertListByType(9);
    }

    @Override
    public void initInjector() {
        getComponent().inject(this);
    }


    @Override
    public void getAdvertListSuccess(List<TAdvert> list) {
        if (list.size() > 0) {
            this.list = list;
            //这里只选取最新的一张图片
            GlideUtils.loadImageView(this,
                    list.get(0).getDownloadPic(), advert);
        }
    }
}
