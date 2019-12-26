package com.ywb.tuyue.ui.article;

import com.ywb.tuyue.entity.TArticle;
import com.ywb.tuyue.entity.TArticleType;
import com.ywb.tuyue.base.mvp.BasePView;

import java.util.List;


public class ArticleContract {
    interface View extends BasePView {
        void getTypeListSuccess(List<TArticleType> list );
        void getArticleListSuccess( List<TArticle> list);
    }

    interface  Presenter  {
        void getTypeList( );
        void getArticleList(int typeid );
    }
}
