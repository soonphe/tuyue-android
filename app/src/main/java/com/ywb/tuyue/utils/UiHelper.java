package com.ywb.tuyue.utils;

import android.app.Activity;
import android.content.Context;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.ywb.tuyue.R;
import com.ywb.tuyue.ui.adapter.OnItemClickListener;
import com.ywb.tuyue.ui.adapter.SimpleAdapter;
import com.ywb.tuyue.widget.LoadingDialog;

import java.util.List;

/**
 * Created by penghao on 2017/12/25.
 * description：
 */

public class UiHelper {

    private PopupWindow mPop;
    private LoadingDialog dialog;


    public UiHelper() {
    }

    public static final class UiHelperHolder {
        public static UiHelper uiHelper = new UiHelper();
    }

    public static UiHelper getInstance() {
        return UiHelperHolder.uiHelper;
    }

    public void showLoading(Context context) {
        if (dialog == null)
            dialog = new LoadingDialog(context);
        dialog.showDialog();
    }

    public void dismissLoading() {
        if (dialog != null)
            dialog.dismissDialog();
    }

    public void destoryDialog() {
        dismissLoading();
        dialog = null;
    }

    public void showPop(Activity c, final View view, final List<String> data) {
        View contentView = LayoutInflater.from(c).inflate(R.layout.simple_item, null);
        SimpleAdapter adapter;
        RecyclerView recyclerView = contentView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(c));
        recyclerView.addItemDecoration(new DividerItemDecoration(c, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter = new SimpleAdapter(c, data));
        mPop = new PopupWindow(view, view.getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT);
        mPop.setContentView(contentView);
        mPop.setFocusable(true);//获取焦点
        mPop.setOutsideTouchable(true);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int pos) {
                ((TextView) view).setText(data.get(pos));
                mPop.dismiss();
            }
        });
        //解决andriod7.0位置弹出异常
//        if (Build.VERSION.SDK_INT < 24) {
            mPop.showAsDropDown(view);
//        } else {
//            mPop.showAtLocation(view, Gravity.LEFT, 0, DensityUtil.getStatusHeight() + view.getHeight());
////            int[] a = new int[2];
////            view.getLocationInWindow(a);
////            mPop.showAtLocation(c.getWindow().getDecorView(), Gravity.CENTER, view.getWidth(), view.getHeight() + a[1]);
////            mPop.update();
//        }
    }
}
