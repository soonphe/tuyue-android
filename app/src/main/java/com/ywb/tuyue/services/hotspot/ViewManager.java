package com.ywb.tuyue.services.hotspot;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

import java.lang.reflect.Field;

/**
 * Created by ZY on 2016/8/10.
 * 管理者，单例模式
 */

public class ViewManager {

    private FloatBall floatBall;
    private WindowManager windowManager;
    private static ViewManager manager;
    private LayoutParams floatBallParams;
    private Context context;

    //私有化构造函数
    private ViewManager(Context context) {
        this.context = context;
        init();
    }

    public void init() {
        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        floatBall = new FloatBall(context);

        OnClickListener clickListener = new OnClickListener() {

            @Override
            public void onClick(View v) {
                windowManager.removeView(floatBall);

                Intent intent = new Intent();
                ComponentName cn = new ComponentName("com.connxun.ltcx",
                        "com.ywb.tuyue.ui.main.MainActivity");
                intent.setComponent(cn);
                context.startActivity(intent);
                ServiceUtil.stopService();
            }
        };
        floatBall.setOnClickListener(clickListener);
    }

    //显示浮动小球
    public void showFloatBall() {
        if (floatBallParams == null) {
            floatBallParams = new LayoutParams();
            floatBallParams.width = floatBall.width;
            floatBallParams.height = floatBall.height - getStatusHeight();
            floatBallParams.gravity = Gravity.TOP | Gravity.CENTER_HORIZONTAL;
            floatBallParams.type = LayoutParams.TYPE_TOAST;
            floatBallParams.flags = LayoutParams.FLAG_NOT_FOCUSABLE | LayoutParams.FLAG_NOT_TOUCH_MODAL;
            floatBallParams.format = PixelFormat.RGBA_8888;
        }
        windowManager.addView(floatBall, floatBallParams);
    }

    //获取ViewManager实例
    public static ViewManager getInstance(Context context) {
        if (manager == null) {
            manager = new ViewManager(context);
        }
        return manager;
    }

    //获取屏幕宽度
    public int getScreenWidth() {
        Point point = new Point();
        windowManager.getDefaultDisplay().getSize(point);
        return point.x;
    }

    //获取屏幕高度
    public int getScreenHeight() {
        Point point = new Point();
        windowManager.getDefaultDisplay().getSize(point);
        return point.y;
    }

    //获取状态栏高度
    public int getStatusHeight() {
        try {
            Class<?> c = Class.forName("com.android.internal.R$dimen");
            Object object = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = (Integer) field.get(object);
            return context.getResources().getDimensionPixelSize(x);
        } catch (Exception e) {
            return 0;
        }
    }
}