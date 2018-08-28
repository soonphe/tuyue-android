package com.ywb.tuyue.ui.article;

import com.ywb.tuyue.api.AppApi;
import com.ywb.tuyue.di.PerActivity;
import com.ywb.tuyue.entity.TArticle;
import com.ywb.tuyue.entity.TArticleType;
import com.ywb.tuyue.ui.mvp.BasePresenter;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

@PerActivity
public class ArticlePresenter extends BasePresenter<ArticleContract.View> implements ArticleContract.Presenter{


    private AppApi api;

    @Inject
    public ArticlePresenter(AppApi accountApi) {
        this.api = accountApi;
    }

    @Override
    public void getTypeList() {
        //获取类型列表
        List<TArticleType> list = LitePal.findAll(TArticleType.class);
        if (list.size() > 0) {
            mView.getTypeListSuccess(list);
        } else {
            mView.getTypeListSuccess(new ArrayList<>());
        }
    }

    @Override
    public void getArticleList(int typeid) {

        //获取城市文章列表
        List<TArticle> list = LitePal.where("typeid=?", typeid+"").find(TArticle.class);
        if (list.size() > 0) {
            mView.getArticleListSuccess(list);
        } else {
            mView.getArticleListSuccess(new ArrayList<>());
        }
    }
}
