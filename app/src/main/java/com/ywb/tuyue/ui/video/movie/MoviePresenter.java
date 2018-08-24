package com.ywb.tuyue.ui.video.movie;

import com.ywb.tuyue.api.AppApi;
import com.ywb.tuyue.di.PerActivity;
import com.ywb.tuyue.ui.mvp.BasePresenter;
import com.ywb.tuyue.vo.PCommonSearchVO;

import javax.inject.Inject;

@PerActivity
public class MoviePresenter extends BasePresenter<MovieContract.View> implements MovieContract.Presenter{

    private AppApi api;

    @Inject
    public MoviePresenter(AppApi api) {
        this.api = api;
    }

    @Override
    public void getVideoList(PCommonSearchVO pCommonSearchVO) {
//        mDisposable.add(api.getVideoList(pCommonSearchVO).subscribe(list -> {
//                    mView.getVideoListSuccess(list);
//                },
//                throwable -> mView.onError(throwable.getMessage()))
//        );
    }
}
