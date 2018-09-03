package com.ywb.tuyue.ui.data;

import android.content.Context;

import com.ywb.tuyue.entity.TStats;
import com.ywb.tuyue.ui.mvp.BasePView;
import com.ywb.tuyue.ui.mvp.BasePresenter;
import com.ywb.tuyue.ui.mvp.BaseView;

public class DataContract {
    public interface View extends BasePView {
        void uploadDataSuccess();
    }

    public interface  Presenter  {
        void uploadData(TStats list);
    }
}
