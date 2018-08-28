package com.ywb.tuyue.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ywb.tuyue.R;
import com.ywb.tuyue.entity.TArticle;
import com.ywb.tuyue.entity.TGame;
import com.ywb.tuyue.utils.GlideUtils;

import java.util.List;

/**
 * @Author soonphe
 * @Date 2018-08-28 19:31
 * @Description 文章适配器
 */
public class ArticleAdapter extends BaseQuickAdapter<TArticle, BaseViewHolder> {

    public ArticleAdapter(int layoutResId) {
        super(layoutResId);
    }

    public ArticleAdapter(int layoutResId, List<TGame> list) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, TArticle item) {

        GlideUtils.loadImageView(mContext, item.getDownloadPic(), helper.getView(R.id.subway_image)); //设置封面
        helper.setText(R.id.subwayList_title, item.getTitle());//设置标题
        if (item.getClassify() == 1) {
            helper.setVisible(R.id.icon_play, true);
        } else {
            helper.setVisible(R.id.icon_play, false);
        }
    }
}
