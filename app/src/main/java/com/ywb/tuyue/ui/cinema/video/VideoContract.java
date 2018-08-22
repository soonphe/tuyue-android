package com.ywb.tuyue.ui.cinema.video;

import com.ywb.tuyue.entity.TVideo;
import com.ywb.tuyue.ui.mvp.BasePView;
import com.ywb.tuyue.vo.PCommonSearchVO;

import java.util.List;


public class VideoContract {
    interface View extends BasePView {
        void getVideoListSuccess(List<TVideo> list);
    }

    interface  Presenter {
        void getVideoList(PCommonSearchVO pCommonSearchVO);
    }
}
