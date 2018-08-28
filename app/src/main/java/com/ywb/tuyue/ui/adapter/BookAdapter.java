package com.ywb.tuyue.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ywb.tuyue.R;
import com.ywb.tuyue.entity.TBook;
import com.ywb.tuyue.entity.TGame;
import com.ywb.tuyue.utils.GlideUtils;

import java.util.List;


/**
 * @Author soonphe
 * @Date 2018-08-28 11:25
 * @Description 书吧适配器
 */
public class BookAdapter extends BaseQuickAdapter<TBook, BaseViewHolder> {


    public BookAdapter(int layoutResId) {
        super(layoutResId);
    }

    public BookAdapter(int layoutResId, List<TGame> list) {
        super(layoutResId);
    }


    @Override
    protected void convert(BaseViewHolder helper, TBook item) {

        GlideUtils.loadImageView(mContext, item.getDownloadPic(), helper.getView(R.id.book_list_icon)); //设置书籍封面
        helper.setText(R.id.book_list_name, item.getName());//设置书籍名称
        helper.setText(R.id.book_list_author, item.getAuthor()); //设置书籍作者
    }
}
