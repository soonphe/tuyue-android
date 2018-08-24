package com.micro.player.service;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName: DrmService
 * @Version: V1.0
 */
public class DrmService {
    public static final String TAG = "com.micro.player.service.DrmService";
    //	private static String path = "/sdcard\0";
    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
    private static int port = 0;
    private static String drm_service_loaclip = "http://127.0.0.1";
    private static DrmService drmService = new DrmService();


    private DrmService() {

    }

    /**
     * 获取drm服务对象
     *
     * @return
     */
    public static DrmService getService() {
        return drmService;
    }



    /**
     * 初始化drm设置
     *
     * @return
     */
    @SuppressLint("LongLogTag")
    public boolean initDrmdecoder() {
        boolean rtn = false;
        try {
            String dtr = "/log-"
                    + formatter.format(new Date());
            port = startDecoder((Environment.getExternalStorageDirectory().getPath() + dtr).getBytes());
            Log.d(TAG, "DrmService  Is try to start port:" + port + ",127.0.0.1:" + port);
            // port <=0 时，视作开启失败
            if (port <= 0) {
                Log.d(TAG, "DrmService  Start port failure:" + "," + port);
            } else {
                Log.d(TAG, "DrmService running checked port:" + port + ",127.0.0.1:" + port);
                rtn = true;
            }

        } catch (Exception e) {
            Log.d(TAG, "DrmService initDrmdecoder erro  " +
                    e.toString());
        }
        return rtn;
    }

    @SuppressLint("LongLogTag")
    public boolean startService() {
        endDecoder();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //临时处理方案
            try {
                int times = 0;
                do {
                    String dtr = "/log-"
                            + formatter.format(new Date());
                    port = startDecoder((Environment.getExternalStorageDirectory().getPath() + dtr).getBytes());
                    times++;
                } while (port <= 0 && times < 5);

            } catch (Exception e) {
            }
            return port > 0;
        } else {
            boolean rtn = false;
            int errorcount = 0;
            try {
                while (!IsStart(port)) {
                    String dtr = "/log-"
                            + formatter.format(new Date());
                    port = startDecoder((Environment.getExternalStorageDirectory().getPath() + dtr).getBytes());
                    Log.d(TAG, "DrmService  Is try to start port:" + port + ",127.0.0.1:" + port);
                    // port <=0 时，视作开启失败
                    if (port <= 0) {
                        Log.d(TAG, "DrmService  Start port failure:" + ",port + ");
                    }
                    Thread.sleep(200);
                    //如果开始
                    errorcount++;

                    if (errorcount > 5) {
                        Log.d(TAG, "DrmService  Start port over 5 times:" + " ," + errorcount);
                        break;
                    }
                }

                if (IsStart(port)) {
                    Log.d(TAG, "DrmService running checked port:" + port + ",127.0.0.1:" + port);
                    rtn = true;
                }

            } catch (Exception e) {
                Log.d(TAG, "DrmService initDrmdecoder erro  " +
                        e.toString());
            }
            return rtn;
        }
    }


    /**
     * 获取可播放url
     *
     * @param url
     * @return
     */
    @SuppressLint("LongLogTag")
    public Uri getUrl(String url, String clientid, String ip, String sn) {
        Uri rtn_uri = Uri.EMPTY;
        try {
            rtn_uri = Uri.parse(drm_service_loaclip + ":"
                    + Integer.toString(port)
                    + "/zqc.ts?ip=" + ip
                    + "&clientid=" + clientid
                    + "&custom="
                    + "{\"sn\":\"" + sn + "\"}"
                    + "&video_id=" + ""
                    + "&source=" + url);
//			AppInfo.setErrorinfos("DrmService last getUrl:",
//					rtn_uri.toString() + "");
        } catch (Exception e) {
            e.printStackTrace();
//			AppInfo.setErrorinfos("DrmService getUrl error port :", port + "");
        }

        if (IsStart(port)) {
            Log.d(TAG, "DrmService getUrl running checked port:" + port + ",127.0.0.1:" + port);
        } else {
            Log.d(TAG, "DrmService getUrl not running checked port:" + port + ",127.0.0.1:" + port);
        }
//		rtn_uri = Uri.parse("http://192.168.3.97/Uploads/head_video/201605030942113255.mov");
        return rtn_uri;
    }


    /**
     * 判断服务是否启动
     *
     * @return
     */
    private boolean IsStart(int port) {
        boolean isstart = false;
        try {
            Process process = Runtime.getRuntime().exec("netstat -antulp|grep " + port);
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()), 1024);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains("127.0.0.1:" + port))
                    isstart = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isstart;

    }

    /**
     * jni方法
     *   启动解码程序
     * @param path
     * @return
     */
    public native int startDecoder(byte[] path);

    /**
     *
     * @return
     */
    public native int endDecoder();

    static {
        System.loadLibrary("decode");
    }

}
