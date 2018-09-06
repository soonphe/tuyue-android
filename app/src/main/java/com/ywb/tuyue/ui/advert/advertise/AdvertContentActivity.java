package com.ywb.tuyue.ui.advert.advertise;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.EmptyUtils;
import com.just.library.AgentWeb;
import com.ywb.tuyue.R;
import com.ywb.tuyue.entity.TAdvert;
import com.ywb.tuyue.ui.mvp.BaseActivity;
import com.ywb.tuyue.widget.AppTitle;

import org.litepal.LitePal;

import butterknife.BindView;


/**
 * @Author soonphe
 * @Date 2018-08-30 10:23
 * @Description 广告详情页
 */
public class AdvertContentActivity extends BaseActivity {

    @BindView(R.id.app_title_id)
    AppTitle appTitle;
    @BindView(R.id.fl_web)
    FrameLayout flWeb;

    //advert的ID
    int id;
    TAdvert tAdvert;


    @Override
    public int bindLayout() {
        return R.layout.activity_advertise_detail;
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public void initView(View view) {
        BarUtils.setStatusBarAlpha(this, 0);
        //取传过来的分类ID
        if (EmptyUtils.isNotEmpty(mOperation.getParameter("advert"))) {
            id = (int) mOperation.getParameter("advert");
        }
        tAdvert = LitePal.find(TAdvert.class,id);

        //初始化webview
        AgentWeb web = AgentWeb.with(this)//传入Activity
                .setAgentWebParent(flWeb, new FrameLayout.LayoutParams(-1, -1))//传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams
                .useDefaultIndicator()// 使用默认进度条
                .defaultProgressBarColor() // 使用默认进度条颜色
                .setReceivedTitleCallback((view1, title) -> appTitle.setTitle(tAdvert.getTitle())) //设置 Web 页面的 title 回调
                .createAgentWeb()//
                .ready()
                .go("file://"+tAdvert.getDownloadContent());
        web.getAgentWebSettings().getWebSettings().setSupportZoom(true);
    }

    @Override
    public void doBusiness(Context mContext) {

    }


    @Override
    public void initInjector() {

//        getComponent().inject(this);
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        appTitle.getStatusLine().unregisterBroadcast();
    }


}
