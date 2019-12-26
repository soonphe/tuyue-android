package com.ywb.tuyue.base.mvp;


import io.reactivex.disposables.CompositeDisposable;

/**
 * @Author soonphe
 * @Date 2017-12-01 15:13
 * @Description BasePresenter 持有View对象
 */
public abstract class BasePresenter<T> {
    public    T                   mView;//View
    protected CompositeDisposable mDisposable;

    /**
     * 添加view引用和订阅事件
     */
    public void attachView(T view) {
        this.mView = view;
        mDisposable = new CompositeDisposable();
    }

    /**
     * 切断view引用和订阅事件
     */
    public void detachView() {
        mView = null;
        mDisposable.clear();
    }

}
