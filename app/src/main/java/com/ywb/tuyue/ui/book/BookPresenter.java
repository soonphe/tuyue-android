package com.ywb.tuyue.ui.book;

import android.content.Context;

import com.ywb.tuyue.api.AppApi;
import com.ywb.tuyue.ui.mvp.BasePresenter;
import com.ywb.tuyue.ui.unlock.UnlockContract;

import javax.inject.Inject;

/**
 * Description:
 * Created by wcystart on 2018/6/29.
 */

public class BookPresenter extends BasePresenter<BookContract.BookView> implements BookContract.BookPresenter {

    Context context;
    private AppApi api;
    @Inject
    public BookPresenter(Context context, AppApi accountApi) {
        this.context = context;
        this.api = accountApi;
    }
    @Override
    public void getBookType() {
        mView.startLoading();
        mDisposable.add(api.getBookType().subscribe(bookType -> {
                    mView.endLoading();
                    mView.getBookTypeSuccess(bookType);
                },
                throwable -> {
                    mView.onError(throwable.getMessage());
                    mView.endLoading();
                }
        ));
    }

    @Override
    public void getBookData() {
        mView.startLoading();
        mDisposable.add(api.getBookData().subscribe(bookData -> {
                    mView.endLoading();
                    mView.getBookDataSuccess(bookData);
                },
                throwable -> {
                    mView.onError(throwable.getMessage());
                    mView.endLoading();
                }
        ));
    }
}
