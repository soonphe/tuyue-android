package com.ywb.tuyue.ui.main;

import com.ywb.tuyue.entity.TStats;
import com.ywb.tuyue.entity.TUser;
import com.ywb.tuyue.ui.mvp.BasePView;


public class MainContract {
    interface View extends BasePView {
        void uploadUserSuccess();

    }

    interface  Presenter  {
        void uploadUser(TUser tUser);

    }
}
