package com.ywb.tuyue.ui.adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ywb.tuyue.R;
import com.ywb.tuyue.entity.PMenu;

import java.util.List;

/**
 * @Author soonphe
 * @Date 2017-12-25 20:23
 * @Descprition 菜单Adapter（仅文字）
 */

public class MenuAdapter extends BaseQuickAdapter<PMenu, BaseViewHolder> {

    /**
     * 创建adapter
     *
     * @param layoutResId 布局
     */
    public MenuAdapter(int layoutResId) {
        super(layoutResId);
    }

    public MenuAdapter(int layoutResId, List<PMenu> cards) {
        super(layoutResId, cards);
    }

    @Override
    protected void convert(BaseViewHolder helper, PMenu item) {
        if (item.getMenuId() == 0) {
            helper.setBackgroundRes(R.id.item_menu,R.color.text_red);
//            helper.setBackgroundColor(R.id.item_menu, Color.parseColor("#ff0000"));
        }
        helper.setText(R.id.menu_content, item.getName());
    }

}