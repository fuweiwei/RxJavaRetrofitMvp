package com.veer.rx.net;


import android.util.Log;

import com.veer.rx.common.Contacts;
import com.veer.rx.net.http.HttpConfig;
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
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit 辅助类
 * @author Veer
 * @email 276412667@qq.com
 * @date 18/7/2
 */

public class RetrofitHelper {
    private static String TGA = "RetrofitHelper";
    private volatile static RetrofitHelper mInstance = null;
    private Retrofit mRetrofit = null;

    public static RetrofitHelper getInstance(){
        if(mInstance==null){
            synchronized (RetrofitHelper.class){
                if (mInstance == null){
                    mInstance = new RetrofitHelper();
                }
            }
        }
        return mInstance;
    }
    private RetrofitHelper(){
        init();
    }
    private void init() {
        resetApp();
    }

    private void resetApp() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(Contacts.DEV_BASE_URL)
                .client(HttpConfig.getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }



    public ApiService getServer(){
        return mRetrofit.create(ApiService.class);
    }
    public <T> T getServer(Class<T> service){
        return mRetrofit.create(service);
    }

}
