package com.ywb.tuyue.ui.data;

import com.ywb.tuyue.entity.TStats;
import com.ywb.tuyue.base.mvp.BasePView;

public class DataContract {
    public interface View extends BasePView {
        void uploadDataSuccess();
    }

    public interface  Presenter  {
        void uploadData(TStats list);
    }
}
