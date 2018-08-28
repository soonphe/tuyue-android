package com.ywb.tuyue.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ywb.tuyue.R;
import com.ywb.tuyue.entity.TCityArticle;
import com.ywb.tuyue.utils.GlideUtils;

import java.util.List;

/**
 * @Author soonphe
 * @Date 2018-08-28 17:07
 * @Description 城市文章适配器
 */
public class CityArticleAdapter extends BaseQuickAdapter<TCityArticle, BaseViewHolder> {

    public CityArticleAdapter(int layoutResId) {
        super(layoutResId);
    }

    public CityArticleAdapter(int layoutResId, List<TCityArticle> list) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper,TCityArticle item) {


        GlideUtils.loadImageView(mContext, item.getDownloadPic(), helper.getView(R.id.city_item_image));//设置城市文章的封面
        helper.setText(R.id.city_item_name,item.getTitle());//设置城市文章名称
        helper.setText(R.id.city_item_intro,item.getIntro()); //设置城市文章的简介
    }
}
