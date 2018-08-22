package com.ywb.tuyue.ui.book;

import com.ywb.tuyue.api.AppApi;
import com.ywb.tuyue.ui.mvp.BasePresenter;

import javax.inject.Inject;

/**
 * Description:
 * Created by wcystart on 2018/6/29.
 */

public class BookPresenter extends BasePresenter<BookContract.BookView> implements BookContract.BookPresenter {


    private AppApi api;

    @Inject
    public BookPresenter( AppApi accountApi) {
        this.api = accountApi;
    }

}
