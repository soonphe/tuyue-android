package com.ywb.tuyue.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jxj on 2016/1/18.
 */


public abstract class CommonAdapter<T> extends BaseAdapter {
    protected Context context;
    public List<T> mdatas =new ArrayList<T>();
    protected T[] datas;
    protected LayoutInflater layoutInflater;
    private int layoutId;


    public CommonAdapter(Context context, T[] datas, int layoutId) {
        this.context = context;
        this.datas = datas;
        layoutInflater = layoutInflater.from(context);
        this.layoutId = layoutId;
    }


    public CommonAdapter(Context context, List<T> datas, int layoutId) {
        this.context = context;
        this.mdatas = datas;
        layoutInflater = layoutInflater.from(context);
        this.layoutId = layoutId;
    }

    public void onDataChange(T[] _data) {
        datas = concat(datas, _data);
        this.notifyDataSetChanged();

    }

    public static <T> T[] concat(T[] first, T[] second) {
        T[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }




    @Override
    public int getCount() {
        return mdatas.size();
    }

    @Override
    public T getItem(int position) {
        return mdatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = ViewHolder.get(context, convertView, parent, layoutId, position);
        convert(holder, getItem(position),holder.getPosition());
        return holder.getmConvertView();
    }

    public abstract void convert(ViewHolder holder, T t,int position);


}
