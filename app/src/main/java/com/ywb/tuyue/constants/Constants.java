package com.ywb.tuyue.constants;

import android.os.Environment;

import com.blankj.utilcode.util.SDCardUtils;

/**
 * @Author soonphe
 * @Date 2018-08-21 09:41
 * @Description 常量池
 */
public class Constants {

    //本地IP
    public static final String LOCAL_SERVER_IP = "192.168.1.6";
    //服务器IP
    public static final String SERVER_IP = "47.98.121.127";
    public static final String SERVER_PORT = "8081";  //8087
    public static final String HOST = "http://" + SERVER_IP + ":" + SERVER_PORT;//服务器地址
    public static final String BASE_URL = HOST + "/tuyue/";  // /ltcx/
    public static final String BASE_API_URL = BASE_URL + "api/";
    public static final String BASE_IMAGE_URL = "http://" + LOCAL_SERVER_IP + "/upload";

    //1905电影数据地址
    public static final String MOVIE_1905_URL = "http://192.168.1.6:8087/index.php/Home/Interface/index?class=HallUse&method=getVideoList";

    //验证码等待时间
    public static int WAITETIME = 60;
    //基础请求分页
    public static int PAGE_SIZE = 20;
    //网络是否可用
    public static final String NETWORK_AVAILABLE = "NETWORK_AVAILABLE";
    //是否为4G设备
    public static final String IS_MOBILE = "IS_MOBILE";
    //注册用户
    public static final String REGIST_PHONE = "REGIST";
    //黑屏注册页面失效时间倒计时，5分钟
    public static final int REGIST_SCREEN = 5 * 60 * 1000;
    //黑屏页面倒计时时间间隔，1分钟（接受回调）
    public static final int REGIST_SCREEN_COUNT = 60 * 1000;
    //高德定位
    public static final String AMAP_LOCATION = "AMAP_LOCATION";

    //bugly
    public static String BUGLY_APPID = "13b1fdb50f";//buglyid
    public static String DCS_KEY = "http://dcsapi.com/?k=181751184&url=";//永中云转换

    public static final String NIGHT_THEME = "THEME";
    public static final String USER_TOKEN = "USER_TOKEN";
    public static final String USER_ID = "USER_ID";
    public static final String USER_NAME = "USER_NAME";
    public static final String USER_PWD = "USER_PWD";
    public static final String USER_TELEPHONE = "USER_TELEPHONE";
    public static final String USER_BADGENUMBER = "USER_BADGENUMBER"; //消息模块 badge数量
    public static final String LAST_WEATHER = "LAST_WEATHER"; //天气

    public static final String APK_VERSION = "APK_VERSION";   //APK版本
    public static final String ADVERT_VERSION = "ADVERT_VERSION";    //广告版本
    public static final String DATA_VERSION = "DATA_VERSION";   //数据版本
    public static final String DOWNLOAD_COUNT = "DOWNLOAD_COUNT";   //下载模块次数——13次

    public static final String GAME_UNZIP = "GAME_UNZIP";   //游戏解压路径


    /**
     * 项目根SD卡目录
     **/
    public static final String PROJECT_ROOT = "tuyue";
    public static final String SDPATHLIST = SDCardUtils.getSDCardPaths().toString();
    public static final String SDPATH = SDPATHLIST.substring(1, SDPATHLIST.length() - 1);
    public static final String DOWNLOAD_PATH = SDPATH + "/Download";
//    public static final String DOWNLOAD_PATH = SDPATH + "/Download" + "/"+ PROJECT_ROOT;

    //图片压缩地址
    public static String SAVED_IMAGE_DIR_PATH = Environment.getExternalStorageDirectory().getPath()
            + "/tuyue/compressImage/";


}
