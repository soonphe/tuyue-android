package com.ywb.tuyue.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;

import com.blankj.utilcode.constant.TimeConstants;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.ywb.tuyue.MyApplication;
import com.ywb.tuyue.constants.Constants;
import com.ywb.tuyue.services.hotspot.ServiceUtil;
import com.ywb.tuyue.ui.mvp.BaseActivity;
import com.ywb.tuyue.ui.mvp.BaseApplication;
import com.ywb.tuyue.ui.unlock.UnlockActivity;
import com.ywb.tuyue.utils.ActivityManager;

/**
 * @Author soonphe
 * @Date 2018-08-30 14:31
 * @Description 监听开屏/锁屏
 */
public class ScreenOnReceiver extends BroadcastReceiver {
    /**
     * 锁屏时间超过5分钟，进入主页面页面弹出注册页面
     */
    CountDownTimer timer = new CountDownTimer(Constants.REGIST_SCREEN, Constants.REGIST_SCREEN_COUNT) {
        @Override
        public void onTick(long millisUntilFinished) {
            Log.e("锁屏倒计时", millisUntilFinished + "");
        }

        @Override
        public void onFinish() {
            //清空Sp中存放的注册用户数据
            SPUtils.getInstance().put(Constants.REGIST_PHONE, "");
        }
    };

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (Intent.ACTION_SCREEN_ON.equals(action)) {
            Log.e("onReceive()", "---开屏---");
            long time = SPUtils.getInstance().getLong(Constants.LOCK_SCREEN_TIME, 1);
            long min = TimeUtils.getTimeSpan(System.currentTimeMillis(), time, TimeConstants.MIN);
            // 锁屏时间超过5分钟，清空Sp中存放的注册用户数据
            if (min >= 5) {
                SPUtils.getInstance().put(Constants.REGIST_PHONE, "");
            }
            //如果倒计时在走，取消
//            if (timer != null) {
//                timer.cancel();
//            }
            if (!ActivityUtils.getTopActivity().getClass().getName().contains("UnlockActivity")) {
                LogUtils.e("当前页面的activity不是UnlockActivity");
                Intent i = new Intent(context, UnlockActivity.class);
                context.startActivity(i);
            }
//            if (!ActivityManager.getActivityManager().currentActivity().getClass().getName().contains("UnlockActivity")) {
//                LogUtils.e("当前页面的activity不是UnlockActivity");
//                Intent i = new Intent(context, UnlockActivity.class);
//                context.startActivity(i);
//            }
        } else if (Intent.ACTION_SCREEN_OFF.equals(action)) {
            // 锁屏
            Log.e("onReceive()", "---锁屏---");
            //保存锁屏时间戳
            SPUtils.getInstance().put(Constants.LOCK_SCREEN_TIME, System.currentTimeMillis());

//            timer.start();

//            //锁屏后取消热点设置中的返回按钮
//            if (ServiceUtil.isIntentNotNull()) {
//                ServiceUtil.stopService();
//                ViewManager manager = ViewManager.getInstance(context);
//                manager.hideFloatBall();
//            }
        } else if (Intent.ACTION_USER_PRESENT.equals(action)) {
            // 解锁
            Log.e("onReceive()", "---解锁---");
        }
    }


}
