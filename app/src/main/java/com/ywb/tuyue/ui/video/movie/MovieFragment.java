package com.ywb.tuyue.ui.video.movie;


import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.ywb.tuyue.R;
import com.ywb.tuyue.base.BaseFragmentV4;
import com.ywb.tuyue.entity.TMovie;
import com.ywb.tuyue.ui.adapter.MovieAdapter;
import com.ywb.tuyue.ui.videoplayer.VideoPlayerActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * @Author soonphe
 * @Date 2018-08-21 19:26
 * @Description 电影-1905电影
 */
public class MovieFragment extends BaseFragmentV4 implements MovieContract.View {

    @Inject
    MoviePresenter presenter;

    @BindView(R.id.video_recycler)
    RecyclerView videoRecycler;
    @BindView(R.id.video_refresh)
    SmartRefreshLayout videoRefresh;

    private MovieAdapter movieAdapter;


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
        return R.layout.fragment_video;
    }

    @Override
    public void initParams(Bundle params) {

    }

    @Override
    public void initView(View view) {

        presenter.attachView(this);

        movieAdapter = new MovieAdapter(R.layout.item_video);
        videoRecycler.setLayoutManager(new GridLayoutManager(getContext(), 4));
        videoRecycler.addItemDecoration(new SpaceDecoration(10));
        videoRecycler.setAdapter(movieAdapter);
        videoRecycler.setNestedScrollingEnabled(false);

        movieAdapter.setOnItemClickListener((adapter, view1, position) -> {
            mOperation.addParameter("movie", ((TMovie)adapter.getItem(position)).getId());
            mOperation.forward(VideoPlayerActivity.class);
        });
    }

    @Override
    public void doBusiness(Context mContext) {
        presenter.getMovieList();
    }

    @Override
    public void getMovieListSuccess(List<TMovie> list) {
        if (list.size()>0){
            movieAdapter.setNewData(list);
        }
    }

    @Override
    public void lazyInitBusiness(Context mContext) {

    }

    @Override
    public void initInjector() {
        getComponent().inject(this);
    }
}
