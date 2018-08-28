package com.ywb.tuyue.ui.book.book;

import com.ywb.tuyue.entity.TAdvert;
import com.ywb.tuyue.entity.TBook;
import com.ywb.tuyue.entity.TBookType;
import com.ywb.tuyue.ui.mvp.BasePView;

import java.util.List;


public class BookContract {
    interface BookView extends BasePView {
        void getAdvertListSuccess(List<TAdvert> list );
        void getTypeListSuccess(List<TBookType> list );
        void getBookListSuccess( List<TBook> list);
    }

    interface BookPresenter {
        void getAdvertList( );
        void getTypeList( );
        void getBookList(int typeid );
    }
}
