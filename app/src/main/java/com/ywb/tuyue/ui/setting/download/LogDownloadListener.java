package com.ywb.tuyue.ui.setting.download;

import com.blankj.utilcode.util.LogUtils;
import com.lzy.okgo.model.Progress;
import com.lzy.okserver.download.DownloadListener;
import com.ywb.tuyue.entity.TAdvert;
import com.ywb.tuyue.entity.TArticle;
import com.ywb.tuyue.entity.TBook;
import com.ywb.tuyue.entity.TCity;
import com.ywb.tuyue.entity.TCityArticle;
import com.ywb.tuyue.entity.TFood;
import com.ywb.tuyue.entity.TGame;
import com.ywb.tuyue.entity.TMovie;
import com.ywb.tuyue.entity.TVideo;
import com.ywb.tuyue.ui.mvp.BaseEvents;

import org.greenrobot.eventbus.EventBus;
import org.litepal.crud.LitePalSupport;

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
        System.out.println("onStart:发出消息onStart " + progress);
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
        LogUtils.e("任务完成回调");
        //在每一个资源完成时，绑定本地路径到数据库中
        LitePalSupport object = (LitePalSupport) progress.extra1;   //实体对象
        int type = (int) progress.extra2;   //类型：0图片 1文件
        String localFilePath = progress.filePath;   //下载到的文件地址
        if (object instanceof TAdvert) {
            if (type == 0) {
                ((TAdvert) object).setDownloadPic(localFilePath);
            } else {
                ((TAdvert) object).setDownloadContent(localFilePath);
            }
            (object).update(((TAdvert) object).getId());
        } else if (object instanceof TVideo) {
            if (type == 0) {
                ((TVideo) object).setDownloadPic(localFilePath);
            } else {
                ((TVideo) object).setDownloadFile(localFilePath);
            }
            (object).update(((TVideo) object).getId());
        } else if (object instanceof TGame) {
            if (type == 0) {
                ((TGame) object).setDownloadPic(localFilePath);
            } else {
                ((TGame) object).setDownloadFile(localFilePath);
            }
            (object).update(((TGame) object).getId());
        } else if (object instanceof TBook) {
            if (type == 0) {
                ((TBook) object).setDownloadPic(localFilePath);
            } else {
                ((TBook) object).setDownloadFile(localFilePath);
            }
            (object).update(((TBook) object).getId());
        } else if (object instanceof TFood) {
            ((TFood) object).setDownloadPic(localFilePath);
            (object).update(((TFood) object).getId());
        } else if (object instanceof TCity) {
            if (type == 0) {
                ((TCity) object).setDownloadPic(localFilePath);
            } else {
                ((TCity) object).setDownloadContent(localFilePath);
            }
            (object).update(((TCity) object).getId());
        } else if (object instanceof TCityArticle) {
            if (type == 0) {
                ((TCityArticle) object).setDownloadPic(localFilePath);
            } else {
                ((TCityArticle) object).setDownloadContent(localFilePath);
            }
            (object).update(((TCityArticle) object).getId());
        } else if (object instanceof TArticle) {
            if (type == 0) {
                ((TArticle) object).setDownloadPic(localFilePath);
            } else {
                ((TArticle) object).setDownloadFile(localFilePath);
            }
            (object).update(((TArticle) object).getId());
        } else if (object instanceof TMovie) {
            if (type == 0) {
                LogUtils.e("设置电影封面");
                ((TMovie) object).setDownloadPic(localFilePath);
            } else {
                LogUtils.e("设置电影文件");
                ((TMovie) object).setDownloadFile(localFilePath);
            }
            (object).update(((TMovie) object).getId());
        }
    }

    @Override
    public void onRemove(Progress progress) {
        System.out.println("onRemove: " + progress);
    }
}
