package com.ywb.tuyue.ui.game.gameplay;


import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.FrameLayout;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.EmptyUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.just.library.AgentWeb;
import com.ywb.tuyue.R;
import com.ywb.tuyue.entity.TGame;
import com.ywb.tuyue.ui.mvp.BaseActivity;
import com.ywb.tuyue.utils.UnZip;
import com.ywb.tuyue.widget.AppTitle;

import org.litepal.LitePal;

import butterknife.BindView;

import static com.ywb.tuyue.constants.Constants.GAME_UNZIP;

/**
 * @Author soonphe
 * @Date 2018-08-21 13:16
 * @Description 玩游戏
 */
public class GamePlayActivity extends BaseActivity implements GamePlayContract.View {

    @BindView(R.id.app_title_id)
    AppTitle appTitle;
    @BindView(R.id.fl_web)
    FrameLayout flWeb;

    //video的ID
    int id;
    TGame tGame;
    String mUrl;

    @Override
    public int bindLayout() {
        return R.layout.activity_game_play;
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void initView(View view) {
        BarUtils.setStatusBarAlpha(this, 0);
        //取传过来的分类ID
        if (EmptyUtils.isNotEmpty(mOperation.getParameter("game"))) {
            id = (int) mOperation.getParameter("game");
        }
        tGame = LitePal.find(TGame.class, id);

        //判断是否需要解压
        if (StringUtils.isEmpty(SPUtils.getInstance().getString(GAME_UNZIP + tGame.getId(), ""))) {
            //解压压缩包(压缩包路径，解压后路径)
            try {
                UnZip.UnZipFolder(tGame.getDownloadFile(), "/storage/emulated/0/download/" + "game" + tGame.getId());
                SPUtils.getInstance().put(GAME_UNZIP + tGame.getId(), "/storage/emulated/0/download/" + "game" + tGame.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //拼接本地路径（需要添加前缀 file://）
        mUrl = "file://" + SPUtils.getInstance().getString(GAME_UNZIP + tGame.getId(), "") + "/index.html";

        //初始化webview
        AgentWeb web = AgentWeb.with(this)//传入Activity
                .setAgentWebParent(flWeb, new FrameLayout.LayoutParams(-1, -1))//传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams
                .useDefaultIndicator()// 使用默认进度条
                .defaultProgressBarColor() // 使用默认进度条颜色
                .setReceivedTitleCallback((view1, title) -> appTitle.setTitle(title)) //设置 Web 页面的 title 回调
                .createAgentWeb()//
                .ready()
                .go(mUrl == null || mUrl.length() == 0 ? "" : mUrl);
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
    public void startLoading() {

    }

    @Override
    public void endLoading() {

    }

    @Override
    public void onError(String error) {

    }

}
