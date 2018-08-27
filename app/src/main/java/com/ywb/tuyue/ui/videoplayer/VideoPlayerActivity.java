package com.ywb.tuyue.ui.videoplayer;


import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.EmptyUtils;
import com.blankj.utilcode.util.LogUtils;
import com.bumptech.glide.Glide;
import com.ywb.tuyue.R;
import com.ywb.tuyue.entity.TVideo;
import com.ywb.tuyue.ui.mvp.BaseActivity;

import org.litepal.LitePal;

import butterknife.BindView;
import cn.jzvd.JZVideoPlayerStandard;

/**
 * @Author soonphe
 * @Date 2018-08-24 14:52
 * @Description 视频播放页
 */
public class VideoPlayerActivity extends BaseActivity implements VideoPlayerContract.View {

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

    //video的ID
    int id;
    TVideo tVideo;


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
        //取传过来的分类ID
        if (EmptyUtils.isNotEmpty(mOperation.getParameter("video"))) {
            id = (int) mOperation.getParameter("video");
        }
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

    }

    @Override
    public void initInjector() {
        getComponent().inject(this);
    }



}
