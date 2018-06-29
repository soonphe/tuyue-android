package com.ywb.tuyue.constants;


import android.os.Environment;

import com.blankj.utilcode.util.SDCardUtils;

/**
 * Description:访问数据IP配置类
 * Created by wcystart on 2018/6/25.
 */

public class IpConfig {

    //http://192.168.1.6:8080/tuyue/api/advert/getList
    //http://192.168.1.6:8080/tuyue/api/sysUser/getList

    public static String SERVER_IP = "192.168.1.6";
    public static String SERVER_PORT = "8080";
    public static String HOST = "http://" + SERVER_IP + ":" + SERVER_PORT;//服务器地址  http://192.168.1.6:8080
    public static String BASE_URL = HOST + "/tuyue/api/";


    /*---------------------------------------------图片请求通用地址-------------------------------*/
    public static String BASE_IMAGE_URL="http://"+SERVER_IP+"/upload";  //http://192.168.1.6/upload/img/20180628/1530178562.jpg


    //验证码等待时间
    public static int WAITETIME = 60;
    //基础请求分页
    public static int PAGE_SIZE = 15;
    //高德定位
    public static String AMAP_LOCATION = "AMAP_LOCATION";

    //buglyid
    public static String BUGLY_APPID = "a5de514a0f";
    public static String DCS_KEY = "http://dcsapi.com/?k=181751184&url=";//永中云转换

    //BeeCloud
    public static String BEECLOUD_APPID = "c5d1cba1-5e3f-4ba0-941d-9b0a371fe719";
    public static String BEECLOUD_SECRET = "4bfdd244-574d-4bf3-b034-0c751ed34fee";


    public static String NIGHT_THEME = "THEME";
    public static String USER = "USER";
    public static String USER_ID = "USER_ID";
    public static String USER_TOKEN = "USER_TOKEN";
    public static String USER_NAME = "USER_NAME";
    public static String USER_PWD = "USER_PWD";


    /**
     * 项目根SD卡目录
     **/
    public static final String PROJECT_ROOT = "ltcx";
    public static final String SDPATH = SDCardUtils.getSDCardPaths()
            + PROJECT_ROOT + "/";
    public static final String DOWNLOAD_PATH = SDPATH + "download" + "/";
    //图片压缩地址
    public static String SAVED_IMAGE_DIR_PATH = Environment.getExternalStorageDirectory().getPath()
            + "/ltcx/compressImage/";
}
