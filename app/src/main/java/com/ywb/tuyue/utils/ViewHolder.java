package com.ywb.tuyue.utils;

import android.content.Context;
import android.graphics.Paint;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by jiajie on 2016/1/16.
 */
public class ViewHolder {
    private SparseArray<View> mViews;
    private int mPostion;
    private View mConvertView;


    public ViewHolder(Context context,
                      int layoutId, ViewGroup parent, int postion) {
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        mViews = new SparseArray<>();
        mConvertView.setTag(this);
        mPostion=postion;
    }

    public static ViewHolder get(Context context, View convertView,
                                 ViewGroup parent, int layoutId, int postion) {
        if (convertView == null) {
            return new ViewHolder(context, layoutId, parent, postion);
        } else {
            ViewHolder holder = (ViewHolder) convertView.getTag();
            holder.mPostion = postion;
            return holder;
        }


    }

    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public View getmConvertView() {
        return mConvertView;
    }

    public ViewHolder setText(int ViewId, String data) {
        TextView tv = getView(ViewId);
        tv.setText(data);
        return this;
    }

    public ViewHolder setImagView(int ViewId, String url) {
        ImageView imageView = getView(ViewId);
//        GMImageLoaderIUtil.loadImage(url, imageView);
        return this;
    }

    public ViewHolder setRatingBar() {

        return this;
    }

    //给textview 设置从中间穿过的 横线（打折线）
    public ViewHolder setThroughLine(int viewId) {
        TextView tv = getView(viewId);
        tv.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        return this;
    }


    public int getPosition(){

        return mPostion;
    }


}
