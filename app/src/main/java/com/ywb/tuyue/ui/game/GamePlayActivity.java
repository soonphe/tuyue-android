package com.ywb.tuyue.ui.game;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.just.library.AgentWeb;
import com.ywb.tuyue.R;
import com.ywb.tuyue.constants.Constants;
import com.ywb.tuyue.ui.mvp.BaseActivity;
import com.ywb.tuyue.utils.UiHelper;
import com.ywb.tuyue.utils.UnZip;
import com.ywb.tuyue.widget.head.HeaderView;

import java.io.File;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by JuZhongJoy on 2018/6/29.
 */

public class GamePlayActivity extends BaseActivity {

    @BindView(R.id.title)
    HeaderView title;
    @BindView(R.id.rootView)
    RelativeLayout rootView;

    private String mGametitle;//游戏标题
    private String mGameContent;//游戏地址
    private String murl;
    private static final String webPath = "/storage/emulated/0/Android/data/com.ywb.tuyue/files/Download/web/";
    private File indexFile;
    private AgentWeb agentWeb;

    @Override
    public int bindLayout() {
        return R.layout.activity_game_play;
    }

    @Override
    public void initParms(Bundle parms) {
        mGametitle = (String) parms.get(Constants.GAME_NAME);
        mGameContent = (String) parms.get(Constants.GAME_NAME);
        Log.i("mGameContent", mGameContent);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void initView(View view) {
        initHeader();
//        UiHelper.getInstance().showLoading(getApplicationContext());
//        if (!TextUtils.isEmpty(mGameContent) && mGameContent.endsWith("zip")) {
//            unGameZip(mGameContent);
//        } else {
//            murl = "file://" + mGameContent;
//            goWeb();
//        }
    }

    private void initHeader() {
        title.setTitle(mGametitle);
        title.setRightBtnVisiable(View.GONE);
        title.setLeftBtnClickListsner(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    mOperation.forwardAndFinish(GameActivity.class,LEFT_RIGHT);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void unGameZip(String thirdUrl) {
        Observable.create(subscribe -> {
//            String downLoadPath = MyDownLoadManager.getLocalUrl(thirdUrl);
            String downLoadPath = null;
            if (!TextUtils.equals(thirdUrl, downLoadPath)) {
                Log.d("unZip--->", "downLoadPath: " + downLoadPath);
                File outFile = new File(downLoadPath);
                String outPath = outFile.getParent() + "/web/";
                Log.d("unZip--->", "outPath: " + outPath);
                try {
                    File file = new File(outPath + mGametitle);
                    if (!file.exists()) {
                        UnZip.UnZipFolder(downLoadPath, outPath + mGametitle);
                        Log.d("unZip--->", "UnZipFolder: " + outPath + mGametitle);
                        //解压完后，如果zip文件存在，就删除。
                        if (outFile.exists()) {
                            outFile.delete();
                        }
                    } else {
                        Log.d("unZip--->", "存在吗: " + outFile.exists());
                        //如果新的zip文件存在，就重新解压。
                        if (outFile.exists()) {
                            //删除文件夹和其文件
                            UnZip.UnZipFolder(downLoadPath, outPath + mGametitle);
                            Log.d("unZip--->", "新的zip文件: " + outPath + mGametitle);
                            outFile.delete();
                        }
                    }
                    indexFile = new File(outPath + mGametitle + "/index.html");

                } catch (Exception e) {
                    e.printStackTrace();
                    indexFile = new File(webPath + mGametitle + "/index.html");
                }
            } else {
                indexFile = new File(webPath + mGametitle + "/index.html");
            }
            subscribe.onNext(Activity.RESULT_OK);
            subscribe.onComplete();
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(Integer -> {
            UiHelper.getInstance().dismissLoading();
            if (indexFile.exists()) {
                murl = "file://" + indexFile.getAbsolutePath();
            } else {
                Toast.makeText(getApplicationContext(), "缺少index.html文件", Toast.LENGTH_SHORT).show();
            }
            goWeb();
        });

    }

    private void goWeb() {
        try {
            agentWeb = AgentWeb//
                    .with(getContext())//
                    .setAgentWebParent(rootView, new LinearLayout.LayoutParams(-1, -1))
                    .useDefaultIndicator()//
                    .defaultProgressBarColor()//
                    .createAgentWeb()//
                    .ready()//
                    .go(murl);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (agentWeb != null) {
            if (agentWeb.handleKeyEvent(keyCode, event)) {
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPause() {
        if (agentWeb != null)
            agentWeb.getWebLifeCycle().onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        if (agentWeb != null)
            agentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        if (agentWeb != null)
            agentWeb.getWebLifeCycle().onDestroy();
        super.onDestroy();
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public void initInjector() {
        getComponent().inject(this);
    }
}
