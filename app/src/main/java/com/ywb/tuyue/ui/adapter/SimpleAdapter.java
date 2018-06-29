package com.ywb.tuyue.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ywb.tuyue.R;

import java.util.List;

/**
 * Created by penghao on 2017/12/25.
 * description：
 */

public class SimpleAdapter extends BaseRecycleViewAdapter<String> {

    public SimpleAdapter(Context c, List<String> mList) {
        super(c, mList);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = resIdToView(parent, R.layout.item_simple);
        return new MyViewHolder(view);
    }


    private class MyViewHolder extends BaseViewHolder {
        TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.content);
        }

        @Override
        public void onBind() {
            textView.setText(mList.get(position));
        }
    }
}
