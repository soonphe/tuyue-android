package com.ywb.tuyue.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ywb.tuyue.R;
import com.ywb.tuyue.entity.TCity;
import com.ywb.tuyue.utils.GlideUtils;

import java.util.List;


/**
 * @Author soonphe
 * @Date 2018-08-28 14:02
 * @Description 城市适配器
 */
public class CityAdapter extends BaseQuickAdapter<TCity, BaseViewHolder> {

    public CityAdapter(int layoutResId) {
        super(layoutResId);
    }

    public CityAdapter(int layoutResId, List<TCity> list) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, TCity item) {

        GlideUtils.loadImageView(mContext, item.getDownloadPic(), helper.getView(R.id.city_item_image));//设置城市封面
        helper.setText(R.id.city_item_name,item.getName());//设置城市名称

    }
}
