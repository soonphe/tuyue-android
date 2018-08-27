package com.ywb.tuyue.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ywb.tuyue.R;
import com.ywb.tuyue.entity.TGame;
import com.ywb.tuyue.utils.GlideUtils;

import java.util.List;

/**
 * @Author soonphe
 * @Date 2018-08-27 09:44
 * @Description 游戏适配器
 */
public class GameAdapter extends BaseQuickAdapter<TGame, BaseViewHolder> {


    public GameAdapter(int layoutResId) {
        super(layoutResId);
    }

    public GameAdapter(int layoutResId, List<TGame> list) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, TGame tVideo) {
        GlideUtils.loadImageView(mContext, tVideo.getDownloadPic(), helper.getView(R.id.movie_icon));//设置电影封面
        helper.setText(R.id.movie_title,tVideo.getName());//设置电影名称

    }
}
