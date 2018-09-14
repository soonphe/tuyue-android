package com.ywb.tuyue.utils;

import android.os.Environment;
import android.support.annotation.NonNull;

import java.io.File;

/**
 * Description: 获取SD路径 在SD卡根目录路径下创建不同的文件夹   以及根据文件路径截取文件名
 * Created by wcystart on 2018/8/8.
 */

public class LocalPathUtils {
    /**
     * 获取文件名
     *
     * @param fileUrl
     * @return
     */
    @NonNull
    public static String getFileName(String fileUrl) {
        return fileUrl.substring(fileUrl.lastIndexOf("/") + 1, fileUrl.length());
    }
    @NonNull
    public static String getLastDotFileName(String fileUrl) {
        return fileUrl.substring(fileUrl.lastIndexOf(".") + 1, fileUrl.length());
    }

    /**
     * 创建文件路径
     *
     * @param subDir 子文件夹  /storage/emulated/0/download/  +子目录名
     * @return
     */
    public static String getPath(String subDir) {
        String downloadPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/download/" + subDir;
        File file = new File(downloadPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        return downloadPath;
    }
}
