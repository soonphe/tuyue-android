package com.ywb.tuyue.ui.cinema.video;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.ywb.tuyue.R;
import com.ywb.tuyue.entity.TVideo;
import com.ywb.tuyue.ui.mvp.BaseFragmentV4;

import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;


/**
 * @Author soonphe
 * @Date 2018-08-21 19:26
 * @Description 电影-小视频
 */
public class VideoFragment extends BaseFragmentV4 implements VideoContract.View {

    @BindView(R.id.video_recycler)
    RecyclerView videoRecycler;
    @BindView(R.id.video_refresh)
    SmartRefreshLayout videoRefresh;
    Unbinder unbinder;

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
    public void getVideoListSuccess(List<TVideo> list) {

    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_video;
    }

    @Override
    public void initParams(Bundle params) {

    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public void lazyInitBusiness(Context mContext) {

    }

    @Override
    public void initInjector() {

    }

}
