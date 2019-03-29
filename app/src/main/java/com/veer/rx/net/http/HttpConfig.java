package com.veer.rx.net.http;

import android.util.Log;

import com.veer.rx.net.RetrofitHelper;
import com.veer.rx.net.interceptor.HeadRequestInterceptor;
import com.veer.rx.net.interceptor.LogInterceptor;
import com.veer.rx.net.interceptor.VLogInterceptor;
import com.veer.rx.util.DateUtils;
import com.veer.rx.util.JsonHandleUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * Desc:http配置
 * Created by fww on 2019/3/29
 */
public class HttpConfig {
    public static String TGA = "HttpConfig";
    public static long CONNECT_TIMEOUT = 60L;
    public static long READ_TIMEOUT = 30L;
    public static long WRITE_TIMEOUT = 30L;
    /**
     * 获取OkHttpClient实例
     * @return
     */
    public static OkHttpClient getOkHttpClient() {
        OkHttpClient   okHttpClient = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(new HeadRequestInterceptor())
                .addInterceptor(new VLogInterceptor())
                .build();
        return okHttpClient;
    }
}
