package com.ywb.tuyue.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.ywb.tuyue.R;

/**
 * Description: 展示推送消息的通用Dialog
 * Created by wcystart on 2018/8/6.
 */


public class ShowPushMessageUtils {
    /**
     *
     * @param context  上下文
     * @param message Dialog要展示的消息
     * @param isForeground 当前的Activity是否处于前台  如果处于前台Activity才会展示Dialog
     * @param isFinish //当前Activty是否被Finish掉
     */
    public static void showPushDialog(Activity context,String message,boolean isForeground,boolean isFinish){
        DisplayMetrics metric = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels;     // 屏幕宽度（像素）
        if (null != context) {
            System.out.println("开始展示dialog" + "///////////////////////");
            Dialog dialog = new Dialog(context, R.style.pushDialog);
            View view = LayoutInflater.from(context).inflate(R.layout.push_dialog, null, false);
            ImageView imageView = view.findViewById(R.id.push_image);
            System.out.println("开始下载图片了！！！！" + "/////////////////////////");
            GlideUtils.loadImageView(context.getApplicationContext(), message, imageView);
            System.out.println("推送广告图片下载成功！！！！" + "/////////////////////////");
            ImageView cancel = view.findViewById(R.id.iv_cancle);
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.setContentView(view);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setCancelable(false);
            Window window = dialog.getWindow();
            WindowManager.LayoutParams attributes = window.getAttributes();

            ///attributes.type = WindowManager.LayoutParams.TYPE_PHONE;   //这不是一个程序的窗口,它用来提供与用户交互的界面

            window.setGravity(Gravity.BOTTOM);
            attributes.width = width - 10;
            // attributes.height = height - 300;
            window.setAttributes(attributes);
            //Dialog调用已经销毁的activity
            if (!isFinish && isForeground == true) { //如果Activity没有被finish掉，并且此时的Activity处于前台Activity才会展示
                dialog.show();
            }
            System.out.println("dialog展示成功！！！" + "///////////////////////");
        }
    }
}
