package com.ywb.tuyue.ui.setting.download;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.BarUtils;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.jude.rollviewpager.Util;
import com.lzy.okserver.OkDownload;
import com.lzy.okserver.task.XExecutor;
import com.ywb.tuyue.R;
import com.ywb.tuyue.base.BaseActivity;
import com.ywb.tuyue.constants.Constants;
import com.ywb.tuyue.constants.enums.DownloadEventEnum;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @Author soonphe
 * @Date 2018-08-28 13:54
 * @Description 全局下载管理Fragment
 */
public class DownloadAllActivity extends BaseActivity implements XExecutor.OnAllTaskEndListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private DownloadAdapter adapter;
    private OkDownload okDownload;

    @Override
    public int bindLayout() {
        return R.layout.activity_download_all;
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public void initInjector() {

    }


    @Override
    public void initView(View view) {
        BarUtils.setStatusBarAlpha(this, 0);

    }

    @Override
    public void doBusiness(Context mContext) {
        okDownload = OkDownload.getInstance();
        okDownload.setFolder(Constants.DOWNLOAD_PATH2); //设置全局下载目录
        okDownload.getThreadPool().setCorePoolSize(2);  //设置同时下载数据
        okDownload.addOnAllTaskEndListener(this);   //设置所有任务监听


        adapter = new DownloadAdapter(getContext());
        adapter.updateData(DownloadAdapter.TYPE_ALL);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerDecoration itemDecoration = new DividerDecoration(Color.GRAY, Util.dip2px(getContext(), 0.5f));
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setAdapter(adapter);

//        adapter.setOnItemClickListener(filePath -> {
//            if (filePath.status == Progress.FINISH && filePath.filePath != null) {
//                FileUtils.startActionFile(filePath.filePath);
//            }
//        });
    }

    @Override
    public void onAllTaskEnd() {
        System.out.println("onFinish:发出消息onAllTaskEnd " );
        EventBus.getDefault().postSticky(DownloadEventEnum.DOWNLOAD_ALL_FINISH);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        okDownload.removeOnAllTaskEndListener(this);
        adapter.unRegister();
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    @OnClick({R.id.removeAll, R.id.pauseAll, R.id.startAll})
    public void onClicked(View view) {
        switch (view.getId()) {
            case R.id.removeAll:
//                mOperation.showBasicDialog("删除所有及本地文件？", (dialog, which) -> {
//                    dialog.dismiss();
//                    okDownload.removeAll();
//                    adapter.updateData(DownloadAdapter.TYPE_ALL);
//                    adapter.notifyDataSetChanged();
//                });
                break;
            case R.id.pauseAll:
                okDownload.pauseAll();
                break;
            case R.id.startAll:
                okDownload.startAll();
                break;

        }

    }


}
