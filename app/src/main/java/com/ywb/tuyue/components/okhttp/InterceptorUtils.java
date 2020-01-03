package com.ywb.tuyue.components.okhttp;


import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.NetworkUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.CacheControl;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @Author soonphe
 * @Date 2017-11-20 18:04
 * @Description Okhttp3拦截器工具包
 */
public class InterceptorUtils {

    /**
     * 日志拦截器
     *
     * @return HttpLoggingInterceptor
     */
    public static HttpLoggingInterceptor getHttpLoggingInterceptor(boolean debug) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLogger());
        if (debug) {
            // 测试
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            // 打包
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        return loggingInterceptor;
    }


    /**
     * 请求头拦截器
     * 使用addHeader()不会覆盖之前设置的header,若使用header()则会覆盖之前的header
     *
     * @return
     */
    public static Interceptor getRequestHeaderInterceptor() {
        Interceptor headerInterceptor = chain -> {
            Request originalRequest = chain.request();
            //这里可以添加判断逻辑或直接返回chain.proceed
            Request request = originalRequest.newBuilder()
                    .addHeader("version", "1")
                    .addHeader("time", System.currentTimeMillis() + "")
                    .build();
            return chain.proceed(request);
        };
        return headerInterceptor;
    }

    /**
     * 统一请求参数拦截器
     *
     * @return
     */
    public static Interceptor commonParamsInterceptor() {
        Interceptor commonParams = chain -> {
            Request originRequest = chain.request();
            Request request;
            HttpUrl httpUrl = originRequest.url().newBuilder()
                    .addQueryParameter("paltform", "android")
                    .addQueryParameter("version", "1.0.0")
                    .build();
            request = originRequest.newBuilder()
                    .url(httpUrl)
                    .build();
            return chain.proceed(request);
        };
        return commonParams;
    }

    /**
     * 在无网络的情况下读取缓存，有网络的情况下根据缓存的过期时间重新请求
     *
     * @return
     */
    public static Interceptor getCacheInterceptor() {
        Interceptor interceptor = chain -> {
            Request request = chain.request();
            if (!NetworkUtils.isConnected()) {
                //无网络下强制使用缓存，无论缓存是否过期,此时该请求实际上不会被发送出去。
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
                LogUtils.d("getCacheInterceptor" + "没有网络链接");
            }
            Response response = chain.proceed(request);
            if (NetworkUtils.isConnected()) {
                //有网络情况下，配置缓存时长，在下次请求时，根据缓存决定是否真正发出请求。
                String cacheControl = request.cacheControl().toString();
                //这里设置为0则为全部走网络,60 * 60则为缓存一小时
                int maxAge = 60 * 60;
                // read from cache for 1 minute
                return response.newBuilder()
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .removeHeader("Pragma").build();
            } else {
                //无网络缓存时间4-weeks stale
                int maxStale = 60 * 60 * 24 * 28;
                return response.newBuilder()
                        .header("Cache-Control", "public,only-if-cached,max-stale=" + maxStale)
                        .removeHeader("Pragma")
                        .build();
            }
        };
        return interceptor;
    }


    /**
     * 自定义CookieJar
     *
     * @param builder
     */
    public static void addCookie(OkHttpClient.Builder builder) {
        builder.cookieJar(new CookieJar() {
            private final HashMap<HttpUrl, List<Cookie>> cookieStore = new HashMap<>();

            @Override
            public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                cookieStore.put(url, cookies);
                //保存cookie //也可以使用SP保存
            }

            @Override
            public List<Cookie> loadForRequest(HttpUrl url) {
                List<Cookie> cookies = cookieStore.get(url);
                //取出cookie
                return cookies != null ? cookies : new ArrayList<Cookie>();
            }
        });
    }
}
