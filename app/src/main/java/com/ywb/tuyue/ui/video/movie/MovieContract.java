package com.ywb.tuyue.ui.video.movie;

import com.ywb.tuyue.entity.TVideo;
import com.ywb.tuyue.ui.mvp.BasePView;
import com.ywb.tuyue.vo.PCommonSearchVO;

import java.util.List;


public class MovieContract {
    interface View extends BasePView {
        void getVideoListSuccess(List<TVideo> list);
    }

    interface  Presenter {
        void getVideoList(PCommonSearchVO pCommonSearchVO);
    }
}
