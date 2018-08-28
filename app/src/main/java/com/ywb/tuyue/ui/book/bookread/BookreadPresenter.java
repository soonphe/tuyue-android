package com.ywb.tuyue.ui.book.bookread;

import com.ywb.tuyue.api.AppApi;
import com.ywb.tuyue.di.PerActivity;
import com.ywb.tuyue.ui.mvp.BasePresenter;

import javax.inject.Inject;

@PerActivity
public class BookreadPresenter extends BasePresenter<BookreadContract.View> implements BookreadContract.Presenter{

    private AppApi api;

    @Inject
    public BookreadPresenter( AppApi accountApi) {
        this.api = accountApi;
    }

}
