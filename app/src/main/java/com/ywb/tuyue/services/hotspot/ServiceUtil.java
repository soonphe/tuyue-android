package com.ywb.tuyue.services.hotspot;

import android.content.Context;
import android.content.Intent;

import com.ywb.tuyue.services.FloatBallService;


/**悬浮窗service启动，销毁工具类
 * Created by Administrator on 2018/5/30.
 */

public class ServiceUtil {

    private static Intent intent;
    private static Context mContext;

    public static void startService(Context context){
        mContext=context;
        intent = new Intent(context, FloatBallService.class);
        context.startService(intent);
    }

    public static  void stopService(){
        mContext.stopService(intent);
    }
}