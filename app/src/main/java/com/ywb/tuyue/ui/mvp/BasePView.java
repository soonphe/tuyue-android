package com.ywb.tuyue.ui.mvp;

/**
 * Created by wushange on  2016/06/30.
 */
public interface BasePView {
    void startLoading();

    void endLoading();

    void onError(String error);

}
