package com.ywb.tuyue.ui.video.movie;


import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.ywb.tuyue.R;
import com.ywb.tuyue.entity.TVideo;
import com.ywb.tuyue.ui.mvp.BaseFragmentV4;

import java.util.List;

/**
 * @Author soonphe
 * @Date 2018-08-21 19:26
 * @Description 电影-1905电影
 */
public class MovieFragment extends BaseFragmentV4 implements MovieContract.View {

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
