package com.ywb.tuyue.ui.mvp;

import android.os.Bundle;
import android.view.View;

import com.ywb.tuyue.widget.swipebacklayout.SwipeBackLayout;
import com.ywb.tuyue.widget.swipebacklayout.Utils;
import com.ywb.tuyue.widget.swipebacklayout.activity.SwipeBackActivityBase;
import com.ywb.tuyue.widget.swipebacklayout.activity.SwipeBackActivityHelper;



/**
 * android 系统中的四大组件之一Activity基类
 *
 * @version 1.0
 */
public abstract class BaseSwipeBackActivity extends BaseActivity implements IBaseActivity, IBaseConstant, SwipeBackActivityBase {
    protected SwipeBackActivityHelper mHelper;

    public void setSwipeBackEnable(boolean enable, int flag) {
        getSwipeBackLayout().setEnableGesture(enable);
        getSwipeBackLayout().setEdgeTrackingEnabled(flag);
    }

    public void setSwipeBackEnable(boolean enable) {
        getSwipeBackLayout().setEnableGesture(enable);
        getSwipeBackLayout().setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHelper = new SwipeBackActivityHelper(this);
        mHelper.onActivityCreate();
        getSwipeBackLayout().setEnableGesture(true);
        getSwipeBackLayout().setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mHelper.onPostCreate();
    }

    @Override
    public View findViewById(int id) {

        View v = super.findViewById(id);
        if (v == null && mHelper != null)
            return mHelper.findViewById(id);
        return v;
    }

    @Override
    public SwipeBackLayout getSwipeBackLayout() {
        return mHelper.getSwipeBackLayout();
    }

    @Override
    public void scrollToFinishActivity() {
        Utils.convertActivityToTranslucent(this);
        getSwipeBackLayout().scrollToFinishActivity();

    }


}
