package com.ywb.tuyue.ui.city.cityarticle;


import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.EmptyUtils;
import com.just.library.AgentWeb;
import com.ywb.tuyue.R;
import com.ywb.tuyue.entity.TCityArticle;
import com.ywb.tuyue.base.BaseActivity;
import com.ywb.tuyue.widget.AppTitle;

import org.litepal.LitePal;

import butterknife.BindView;


public class CityArticleActivity extends BaseActivity implements CityArticleContract.View {

    @BindView(R.id.app_title_id)
    AppTitle appTitle;
    @BindView(R.id.fl_web)
    FrameLayout flWeb;

    //video的ID
    int id;
    TCityArticle tGame;

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
        return R.layout.activity_city_article;
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public void initView(View view) {
        BarUtils.setStatusBarAlpha(this, 0);
        //取传过来的分类ID
        if (EmptyUtils.isNotEmpty(mOperation.getParameter("cityarticle"))) {
            id = (int) mOperation.getParameter("cityarticle");
        }
        tGame = LitePal.where("tid = ?", id + "").findFirst(TCityArticle.class);

        //初始化webview
        AgentWeb web = AgentWeb.with(this)//传入Activity
                .setAgentWebParent(flWeb, new FrameLayout.LayoutParams(-1, -1))//传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams
                .useDefaultIndicator()// 使用默认进度条

                .defaultProgressBarColor() // 使用默认进度条颜色
                .setReceivedTitleCallback((view1, title) -> appTitle.setTitle(tGame.getTitle())) //设置 Web 页面的 title 回调
                .createAgentWeb()//
                .ready()
                .go("file://"+tGame.getDownloadContent());
        web.getAgentWebSettings().getWebSettings().setSupportZoom(true);
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public void initInjector() {
        getComponent().inject(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        appTitle.getStatusLine().unregisterBroadcast();
    }
}
