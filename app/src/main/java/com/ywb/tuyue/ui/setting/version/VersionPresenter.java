package com.ywb.tuyue.ui.setting.version;

import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.ywb.tuyue.api.AppApi;
import com.ywb.tuyue.constants.Constants;
import com.ywb.tuyue.di.PerActivity;
import com.ywb.tuyue.entity.TMovie;
import com.ywb.tuyue.entity.TVersion;
import com.ywb.tuyue.ui.mvp.BasePresenter;

import org.litepal.LitePal;

import java.io.File;

import javax.inject.Inject;

import static com.ywb.tuyue.constants.Constants.DOWNLOAD_PATH;
import static com.ywb.tuyue.constants.Constants.NETWORK_AVAILABLE;

@PerActivity
public class VersionPresenter extends BasePresenter<VersionContract.View> implements VersionContract.Presenter {

    private AppApi api;

    @Inject
    public VersionPresenter(AppApi api) {
        this.api = api;
    }

    @Override
    public void getVersion() {
        //判断当前网络可用——可用则上传
        if (SPUtils.getInstance().getBoolean(NETWORK_AVAILABLE)) {
            mDisposable.add(api.getVersion().subscribe(list -> {
                        if (LitePal.findFirst(TVersion.class) != null) {
                            LitePal.deleteAll(TVersion.class);
                        }
                        list.save();
                        mView.getVersionSuccess(list);

                    },
                    throwable -> mView.onError(throwable.getMessage()))
            );
        }
    }

    @Override
    public void downloadApk(TVersion tVersion) {
        String downpath = tVersion.getFilepath();
        //截取最后的文件名和尾缀
        String localFilePath = DOWNLOAD_PATH + downpath.substring(downpath.lastIndexOf("/"), downpath.length());
        if (FileUtils.isFileExists(localFilePath)) {
            LogUtils.e("本地文件存在" + localFilePath);
            mView.downloadApkSuccess(localFilePath);
        } else {
            mView.startLoading();
            OkGo.<File>get(Constants.BASE_IMAGE_URL + downpath)
                    .tag(this)
                    .execute(new FileCallback() {
                        @Override
                        public void onSuccess(Response<File> response) {
                            tVersion.setDownloadFile(response.body().toString());
                            (tVersion).update(tVersion.getId());
                            mView.endLoading();
                            mView.downloadApkSuccess(response.body().toString());
                        }

                        @Override
                        public void downloadProgress(Progress progress) {
                            //回调下载进度
                            super.downloadProgress(progress);
                            LogUtils.i(progress.fileName + progress.currentSize +
                                    "下载进度为：" + progress.fraction * 100 + "%");
                        }

                        @Override
                        public void onError(Response<File> response) {
                            super.onError(response);
                        }


                    });
        }

    }

}
