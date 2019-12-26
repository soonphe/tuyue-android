package com.ywb.tuyue.ui.video.movie;

import com.ywb.tuyue.entity.TMovie;
import com.ywb.tuyue.base.mvp.BasePView;

import java.util.List;


public class MovieContract {
    interface View extends BasePView {
        void getMovieListSuccess(List<TMovie> list);
    }

    interface  Presenter {
        void getMovieList();
    }
}
