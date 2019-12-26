package com.ywb.tuyue.ui.video.video;

import com.ywb.tuyue.entity.TVideo;
import com.ywb.tuyue.base.mvp.BasePView;

import java.util.List;


public class VideoContract {
    interface View extends BasePView {
        void getVideoListSuccess(List<TVideo> list);
    }

    interface  Presenter {
        void getVideoList( );
    }
}
