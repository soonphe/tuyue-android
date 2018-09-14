package com.ywb.tuyue.ui.setting.download;

import com.lzy.okgo.model.Progress;
import com.lzy.okserver.download.DownloadListener;
import com.ywb.tuyue.ui.mvp.BaseEvents;

import org.greenrobot.eventbus.EventBus;

import java.io.File;

/**
 * @Author soonphe
 * @Date 2018-08-28 13:54
 * @Description 全局下载监听 日志打印
 */
public class LogDownloadListener extends DownloadListener {

    public LogDownloadListener() {
        super("LogDownloadListener");
    }

    @Override
    public void onStart(Progress progress) {
        System.out.println("onStart: " + progress);
    }

    @Override
    public void onProgress(Progress progress) {
        System.out.println("onProgress: " + progress);
    }

    @Override
    public void onError(Progress progress) {
        System.out.println("onError: " + progress);
        progress.exception.printStackTrace();
    }

    @Override
    public void onFinish(File file, Progress progress) {
        System.out.println("onFinish: " + progress);
    }

    @Override
    public void onRemove(Progress progress) {
        System.out.println("onRemove: " + progress);
    }
}
