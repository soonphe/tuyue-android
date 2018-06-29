package com.ywb.tuyue.utils;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by penghao on 2018/3/20.
 * description：
 */

public class UnZip {

    private static final String TAG = "ZipUtils";

    /**
     * DeCompress the ZIP to the path
     *
     * @param zipFileString name of ZIP
     * @param outPathString path to be unZIP
     * @throws Exception
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static String UnZipFolder(String zipFileString, String outPathString) throws Exception {
        // check file exists
        File zipFile = new File(zipFileString);
//        if (zipFileString.endsWith(".rar")) {
//            return UnRarFolder(zipFileString, outPathString);
//        }
        if (!zipFile.exists())
            throw new IOException("file not exists");
//        outPathString += File.separator + zipFile.getName().substring(0, zipFile.getName().lastIndexOf("."));
        // create zip input
        ZipInputStream zipInputStream;
        if (Build.VERSION.SDK_INT >= 24) {
            //加上编码格式，默认是utf-8，强制换成GBK
            Charset gbk = Charset.forName("GBK");
            zipInputStream = new ZipInputStream(new FileInputStream(zipFile), gbk);
        } else {
            zipInputStream = new ZipInputStream(new FileInputStream(zipFile));
        }
        ZipEntry zipEntry;
        String zipPathName = "";
        Log.d(TAG, "start unZip: " + zipFileString);
        // zip
        while ((zipEntry = zipInputStream.getNextEntry()) != null) {
            zipPathName = zipEntry.getName();
            Log.d(TAG, "zipPath: " + zipPathName);
            if (zipEntry.isDirectory()) {
                File folder = new File(outPathString + File.separator + zipPathName);
                if (!folder.exists())
                    folder.mkdirs();
            } else {
                File file = new File(outPathString + File.separator + zipPathName);
                File pFile = file.getParentFile();
                if (!pFile.exists()) {
                    pFile.mkdirs();
                }
                file.createNewFile();
                // outPut file
                FileOutputStream out = new FileOutputStream(file);
                int len;
                byte[] buffer = new byte[1024];
                while ((len = zipInputStream.read(buffer)) != -1) {
                    out.write(buffer, 0, len);
                    out.flush();
                }
                out.close();
            }
        }
        // finish
        Log.d(TAG, "end unZip");
        zipInputStream.close();
        return outPathString;
    }
}
