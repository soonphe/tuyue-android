package com.ywb.tuyue.ui.video.video;

import com.ywb.tuyue.api.AppApi;
import com.ywb.tuyue.di.PerActivity;
import com.ywb.tuyue.entity.TVideo;
import com.ywb.tuyue.ui.mvp.BasePresenter;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

@PerActivity
public class VideoPresenter extends BasePresenter<VideoContract.View> implements VideoContract.Presenter {

    private AppApi api;

    @Inject
    public VideoPresenter(AppApi api) {
        this.api = api;
    }

    @Override
    public void getVideoList() {

        List<TVideo> list = LitePal.findAll(TVideo.class);
        if (list.size() > 0) {
            mView.getVideoListSuccess(list);
        } else {
            mView.getVideoListSuccess(new ArrayList<>());
        }


    }
}
