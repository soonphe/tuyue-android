package com.ywb.tuyue.base.mvp;

/**
 * @Author soonphe
 * @Date 2017-12-01 15:13
 * @Description BasePView
 */
public interface BasePView {
    /**
     * 开始加载
     */
    void startLoading();

    /**
     * 结束加载
     */
    void endLoading();

    /**
     * 错误异常
     * @param error
     */
    void onError(String error);
}
