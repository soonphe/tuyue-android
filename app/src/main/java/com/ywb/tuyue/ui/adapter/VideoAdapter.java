package com.ywb.tuyue.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ywb.tuyue.R;
import com.ywb.tuyue.entity.TVideo;
import com.ywb.tuyue.utils.GlideUtils;

import java.util.List;


/**
 * @Author soonphe
 * @Date 2018-08-24 11:48
 * @Description 电影适配器
 */
public class VideoAdapter extends BaseQuickAdapter<TVideo, BaseViewHolder> {

    public VideoAdapter(int layoutResId) {
        super(layoutResId);
    }

    public VideoAdapter(int layoutResId, List<TVideo> list) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, TVideo tVideo) {
        GlideUtils.loadImageView(mContext, tVideo.getDownloadPic(), helper.getView(R.id.movie_icon));//设置电影封面
        helper.setText(R.id.movie_title,tVideo.getName());//设置电影名称

    }
}
