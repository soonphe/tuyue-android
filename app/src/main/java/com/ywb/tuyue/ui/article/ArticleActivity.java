package com.ywb.tuyue.ui.article;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.LogUtils;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.ywb.tuyue.R;
import com.ywb.tuyue.entity.TArticle;
import com.ywb.tuyue.entity.TArticleType;
import com.ywb.tuyue.entity.TabEntity;
import com.ywb.tuyue.ui.adapter.ArticleAdapter;
import com.ywb.tuyue.ui.mvp.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;


/**
 * @Author soonphe
 * @Date 2018-08-28 19:11
 * @Description 城市文章
 */
public class ArticleActivity extends BaseActivity implements ArticleContract.View {

    @Inject
    ArticlePresenter presenter;

    @BindView(R.id.tl_2)
    CommonTabLayout tl2;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.sm_refresh)
    SmartRefreshLayout smRefresh;

    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private ArticleAdapter articleAdapter;

    @Override
    public void startLoading() {

    }

    @Override
    public void endLoading() {

    }

    @Override
    public void onError(String error) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_article;
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public void initView(View view) {
        BarUtils.setStatusBarAlpha(this, 0);
        presenter.attachView(this);

        articleAdapter = new ArticleAdapter(R.layout.item_article);
        rvList.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rvList.addItemDecoration(new SpaceDecoration(10));
        rvList.setAdapter(articleAdapter);
        rvList.setNestedScrollingEnabled(false);
        articleAdapter.setOnItemClickListener((adapter, view1, position) -> {
//            mOperation.addParameter("book", ((TBook) adapter.getItem(position)).getId());
//            mOperation.forward(BookreadActivity.class);
        });
    }

    @Override
    public void doBusiness(Context mContext) {
        presenter.getTypeList();
    }

    @Override
    public void getTypeListSuccess(List<TArticleType> list) {
        for (int i = 0; i < list.size(); i++) {
            mTabEntities.add(new TabEntity(list.get(i).getName()));
        }
        tl2.setTabData(mTabEntities);

        tl2.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                LogUtils.e("当前typename:" + (mTabEntities.get(position)).getTabTitle());
                presenter.getArticleList(list.get(position).getTid());
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        presenter.getArticleList(list.get(0).getTid());
    }

    @Override
    public void getArticleListSuccess(List<TArticle> list) {

        if (list.size() > 0) {
            articleAdapter.setNewData(list);
        }
    }

    @Override
    public void initInjector() {
        getComponent().inject(this);
    }

}
