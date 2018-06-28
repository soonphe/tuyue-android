package com.ywb.tuyue.ui.main;

import com.ywb.tuyue.entity.TAdvert;
import com.ywb.tuyue.ui.mvp.BasePView;

import java.util.List;

/**
 * Description:
 * Created by wcystart on 2018/6/27.
 */

public class MainContract {
    interface MainView extends BasePView {
        void getMainSuccess(List<TAdvert> dataBeans);
    }

    interface MainPresenter {
        void getMain();
    }
}
