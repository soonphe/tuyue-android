package com.ywb.tuyue.ui.book;

import com.ywb.tuyue.entity.TAdvertData;
import com.ywb.tuyue.entity.TAdvertType;
import com.ywb.tuyue.entity.TBookData;
import com.ywb.tuyue.entity.TBookType;
import com.ywb.tuyue.ui.mvp.BasePView;

import java.util.List;

/**
 * Description:
 * Created by wcystart on 2018/6/29.
 */

public class BookContract {
    interface BookView extends BasePView {
        void getBookTypeSuccess(List<TBookType> tBookTypes);
        void getBookDataSuccess(List<TBookData> tBookData);
    }

    interface BookPresenter {
        void getBookType();
        void getBookData();
    }
}
