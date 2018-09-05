package com.ywb.tuyue.ui.setting.version;

import com.ywb.tuyue.entity.TVersion;
import com.ywb.tuyue.ui.mvp.BasePView;


public class VersionContract {
    interface View extends BasePView {
        void getVersionSuccess(TVersion tVersion);

        void downloadApkSuccess(String filepath);
    }

    interface Presenter {
        //获取当前版本信息
        void getVersion();

        //下载APK
        void downloadApk(TVersion tVersion);
    }
}
