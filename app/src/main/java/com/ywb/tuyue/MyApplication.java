package com.ywb.tuyue;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;
import android.support.multidex.MultiDex;
import android.widget.Toast;

import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.Utils;
import com.facebook.stetho.Stetho;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.smtt.sdk.QbSdk;
import com.ywb.tuyue.api.AppApi;
import com.ywb.tuyue.constants.Constants;
import com.ywb.tuyue.di.component.ApplicationComponent;
import com.ywb.tuyue.di.component.DaggerApplicationComponent;
import com.ywb.tuyue.di.module.ApplicationModule;
import com.ywb.tuyue.ui.mvp.BaseApplication;

import org.litepal.LitePal;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import cn.jpush.android.api.JPushInterface;
import okhttp3.OkHttpClient;

import static com.ywb.tuyue.constants.Constants.BUGLY_APPID;


/**
 * @Author soonphe
 * @Date 2017-12-01 15:13
 * @Description Application
 */
public class MyApplication extends BaseApplication {

    private ApplicationComponent mApplicationComponent;

    @Inject
    OkHttpClient okHttpClient;

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

        // 安装tinker
        // TinkerManager.installTinker(this); 替换成下面Bugly提供的方法
//        Beta.installTinker();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    public void onCreate() {
        super.onCreate();
        //debug版本使用严苛模式
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
        //blankJ 工具类init
        Utils.init(this);
        //极光初始化
        JPushInterface.setDebugMode(false);    // 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);

        //facebook调试工具
        Stetho.initializeWithDefaults(this);

//        OkHttpClient.Builder builder = new OkHttpClient.Builder()
//                //全局的读取超时时间 默认DEFAULT_MILLISECONDS 60000 60秒
//                .readTimeout(60000, TimeUnit.MILLISECONDS)
//                //全局的写入超时时间
//                .writeTimeout(60000, TimeUnit.MILLISECONDS)
//                //全局的连接超时时间
//                .connectTimeout(60000, TimeUnit.MILLISECONDS)
//                .retryOnConnectionFailure(true);
        //初始化okgo
        OkGo.getInstance().init(this)
                //okgo全局配置的okhttpclient
//                .setOkHttpClient(builder.build())
                .setCacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
                //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
                .setRetryCount(3);
        //创建下载文件夹
        FileUtils.createOrExistsDir(Constants.DOWNLOAD_PATH);
        //初始化litepal数据库
        LitePal.initialize(this);
        //腾讯bugfly
        CrashReport.initCrashReport(this, BUGLY_APPID, false);
//        Bugly.init(this, BUGLY_APPID, true);//bugly

        // 我们可以从这里获得Tinker加载过程的信息
//        tinkerApplicationLike = TinkerPatchApplicationLike.getTinkerPatchApplicationLike();
//
//        // 初始化TinkerPatch SDK, 更多配置可参照API章节中的,初始化SDK
//        TinkerPatch.init(tinkerApplicationLike)
//                .reflectPatchLibrary()
//                .setPatchRollbackOnScreenOff(true)
//                .setPatchRestartOnSrceenOff(true)
//                .setFetchPatchIntervalByHours(3);
//
//        // 每隔3个小时(通过setFetchPatchIntervalByHours设置)去访问后台时候有更新,通过handler实现轮训的效果
//        TinkerPatch.with().fetchPatchUpdateAndPollWithInterval();


        QbSdk.initX5Environment(this, new QbSdk.PreInitCallback() {
            @Override
            public void onCoreInitFinished() {
                //x5内核初始化完成回调接口，此接口回调并表示已经加载起来了x5，有可能特殊情况下x5内核加载失败，切换到系统内核。

            }

            @Override
            public void onViewInitFinished(boolean b) {
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。

            }
        });


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
