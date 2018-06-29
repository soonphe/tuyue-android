package com.ywb.tuyue.ui.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * created by mhdt 2017/loading_4/16
 * RecyclerView.Adapter基类
 */
public abstract class BaseRecycleViewAdapter<T> extends RecyclerView.Adapter<BaseRecycleViewAdapter.BaseViewHolder> {
    protected Context c;
    protected List<T> mList;

    public BaseRecycleViewAdapter(Context c, List<T> mList) {
        this.c = c;
        this.mList = mList;
    }

    protected View resIdToView(ViewGroup viewGroup, int resId) {
        return LayoutInflater
                .from(c).inflate(resId, viewGroup, false);
    }

    protected boolean isFullSpanType(int type) {
        return false;
    }

    @Override
    public void onAttachedToRecyclerView(final RecyclerView recyclerView) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    RecyclerView.Adapter adapter = recyclerView.getAdapter();
                    if (isFullSpanType(adapter.getItemViewType(position))) {
                        return gridLayoutManager.getSpanCount();
                    }
                    return 1;
                }
            });
        }
    }

    @Override
    public void onViewAttachedToWindow(BaseViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        int position = holder.getLayoutPosition();
        int type = getItemViewType(position);
        if (isFullSpanType(type)) {
            ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
            if (layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
                StaggeredGridLayoutManager.LayoutParams lp = (StaggeredGridLayoutManager.LayoutParams) layoutParams;
                lp.setFullSpan(true);
            }
        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public abstract class BaseViewHolder<V> extends RecyclerView.ViewHolder {
        protected int position;
        T bean;
        V rootView;

        public BaseViewHolder(View itemView) {
            super(itemView);
            rootView = (V) itemView;
        }

        public void bind(int pos) {
            position = pos;
            if (pos < mList.size())
                bean = mList.get(pos);
            registerOnItemClickListener(itemView, pos);
            onBind();
        }

        public abstract void onBind();
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void registerOnItemClickListener(final View itemView, final int pos) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null)
                    onItemClickListener.onItemClick(itemView, pos);
            }
        });
    }

}
