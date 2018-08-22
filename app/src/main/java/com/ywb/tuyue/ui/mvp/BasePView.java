package com.ywb.tuyue.ui.mvp;

/**
 * @Author soonphe
 * @Date 2018-08-21 13:18
 * @Description 全局加载与报错展示
 */
public interface BasePView {
    void startLoading();

    void endLoading();

    void onError(String error);

}
