package com.ywb.tuyue;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;

import androidx.annotation.RequiresApi;
import androidx.multidex.MultiDex;

import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.Utils;
import com.facebook.stetho.Stetho;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.tencent.bugly.crashreport.CrashReport;
import com.ywb.tuyue.base.BaseApplication;
import com.ywb.tuyue.constants.Constants;
import com.ywb.tuyue.di.component.ApplicationComponent;
import com.ywb.tuyue.di.component.DaggerApplicationComponent;
import com.ywb.tuyue.di.module.ApplicationModule;

import org.litepal.LitePal;

import cn.jpush.android.api.JPushInterface;

import static com.ywb.tuyue.constants.Constants.BUGLY_APPID;


/**
 * @Author soonphe
 * @Date 2017-12-01 15:13
 * @Description Application
 */
public class MyApplication extends BaseApplication {

    private ApplicationComponent mApplicationComponent;

    /**
     * 全局下拉刷新，上拉加载更多
     */
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) -> {
            layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.transparent);//全局设置主题颜色
            return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator((context, layout) -> {
            //指定为经典Footer，默认是 BallPulseFooter
            return new ClassicsFooter(context).setDrawableSize(20);
        });
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            //StrictMode严苛模式,检测:一个是线程策略，即TreadPolicy，另一个是VM策略，即VmPolicy。
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.
                    //detectActivityLeaks().      //用户检查 Activity 的内存泄露情况
                            detectLeakedSqlLiteObjects().   //SQLite对象是否被正确关闭
                    detectLeakedClosableObjects().  //用于资源没有正确关闭时提醒
                    penaltyLog().   //当触发违规条件时，记录log
                    //penaltyDeath(). //当触发违规条件时，直接Crash掉当前应用程序
                            build());
            //文件uri暴露
            builder.detectFileUriExposure();
        }
        //dagger依赖注入
        initComponent();
        //facebook Stetho
        Stetho.initializeWithDefaults(this);
        //blankJ
        Utils.init(this);
        //litepal
        LitePal.initialize(this);
        //初始化okgo
        OkGo.getInstance().init(this)
                //okgo全局配置的okhttpclient
//                .setOkHttpClient(builder.build())
                .setCacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
                //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
                .setRetryCount(10);
        //创建下载文件夹
        FileUtils.createOrExistsDir(Constants.DOWNLOAD_PATH);
        //极光初始化
        JPushInterface.setDebugMode(false);    // 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);
        //腾讯bugfly
        CrashReport.initCrashReport(this, BUGLY_APPID, false);
//        Bugly.init(this, BUGLY_APPID, true);//bugly

    }

    /**
     * 应用程序推出，移除所有activity
     */
    @Override
    public void exit() {
        removeAllActivity();
    }

    private void initComponent() {
        mApplicationComponent =
                DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
        mApplicationComponent.inject(this);
    }


    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }


    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public void registerActivityLifecycleCallback(Application.ActivityLifecycleCallbacks callbacks) {
        this.registerActivityLifecycleCallbacks(callbacks);
    }


}
