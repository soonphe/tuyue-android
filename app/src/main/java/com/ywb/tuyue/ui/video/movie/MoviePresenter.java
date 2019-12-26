package com.ywb.tuyue.ui.video.movie;

import com.ywb.tuyue.api.AppApi;
import com.ywb.tuyue.di.PerActivity;
import com.ywb.tuyue.entity.TMovie;
import com.ywb.tuyue.base.mvp.BasePresenter;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

@PerActivity
public class MoviePresenter extends BasePresenter<MovieContract.View> implements MovieContract.Presenter{

    private AppApi api;

    @Inject
    public MoviePresenter(AppApi api) {
        this.api = api;
    }

    @Override
    public void getMovieList( ) {

        List<TMovie> list = LitePal.findAll(TMovie.class);
        if (list.size() > 0) {
            mView.getMovieListSuccess(list);
        } else {
            mView.getMovieListSuccess(new ArrayList<>());
        }
    }
}
