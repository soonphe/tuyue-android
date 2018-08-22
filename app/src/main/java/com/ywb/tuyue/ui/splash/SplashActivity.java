package com.ywb.tuyue.ui.splash;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.BarUtils;
import com.ywb.tuyue.R;
import com.ywb.tuyue.ui.main.MainActivity;
import com.ywb.tuyue.ui.mvp.BaseActivity;

import java.util.Timer;
import java.util.TimerTask;
/**
 * @Author wcystart
 * @Date 2018-6-21  2.06
 * @Description 倒计时欢迎页
 */
public class SplashActivity extends BaseActivity {
    private TextView mCountDown;
    Timer timer = new Timer();
    int currentSecond = 4;
    int minTime = 0;


    @Override
    public int bindLayout() {
        return R.layout.activity_splash;
    }
    @Override
    public void initParms(Bundle parms) {
    }

    @Override
    public void initView(View view) {

        BarUtils.setStatusBarAlpha(this, 0);
        mCountDown=findViewById(R.id.splash_countdown);
        mCountDown.setOnClickListener(new View.OnClickListener() {
            //点击倒计时按钮也跳转到主界面，同时计时器消失
            @Override
            public void onClick(View v) {
                if(timer!=null){
                    timer.cancel();
                }
                mOperation.forwardAndFinish(MainActivity.class,LEFT_RIGHT);
            }
        });
    }

    @Override
    public void doBusiness(Context mContext) {

        //处理倒计时逻辑，倒计时4s之后自动跳到主界面
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                currentSecond--;
                runOnUiThread(() -> mCountDown.setText(currentSecond + "秒跳过"));
                if (currentSecond == minTime) {
                    if(timer!=null){
                        timer.cancel();
                    }

                    mOperation.forwardAndFinish(MainActivity.class);
                }

            }
        }, 1, 1000);

    }
    @Override
    public void initInjector() {
        getComponent().inject(this);
    }
}
