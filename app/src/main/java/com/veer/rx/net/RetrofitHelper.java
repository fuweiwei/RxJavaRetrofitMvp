package com.veer.rx.net;


import android.util.Log;

import com.veer.rx.common.Contacts;
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
    private  long CONNECT_TIMEOUT = 60L;
    private  long READ_TIMEOUT = 30L;
    private  long WRITE_TIMEOUT = 30L;
    private static RetrofitHelper mInstance = null;
    private Retrofit mRetrofit = null;

    public static RetrofitHelper getInstance(){
        synchronized (RetrofitHelper.class){
            if (mInstance == null){
                mInstance = new RetrofitHelper();
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
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
    /**
     * 获取OkHttpClient实例
     *
     * @return
     */

    private  OkHttpClient getOkHttpClient() {
        OkHttpClient   okHttpClient = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(new RqInterceptor())
                .addInterceptor(new LogInterceptor())
                .build();
        return okHttpClient;
    }

    /**
     * 请求拦截器
     */
    private  class RqInterceptor implements Interceptor {
        @Override
        public Response intercept(Interceptor.Chain chain) throws IOException {
            Request request  =  chain.request()
                    .newBuilder()
                    .addHeader("X-APP-TYPE","android")
                    .addHeader("X-LZ-XJD","2")
                    .addHeader("X-APP-VER","27")
                    .addHeader("X-APP-NAME","2.0.7")
                    .addHeader("X-APP-VER_NAME","B")
                    .build();
            Response response = chain.proceed(request);
            return response;
        }
    }
    /**
     * 日志拦截器
     */
    private class LogInterceptor implements Interceptor {
        @Override
        public Response intercept(Interceptor.Chain chain) throws IOException {
            Request request  =  chain.request();
            String url = request.url().toString();
            String params = requestBodyToString(request.body());
            Response response = chain.proceed(request);
            String responseString = JsonHandleUtils.jsonHandle(response.body().string());
            String time = DateUtils.getNowDateFormat(DateUtils.DATE_FORMAT_2);
            String log = "\n\n*****请求时间*****:\n" + time+"\n*******路径*******:\n" + url + "\n*******参数*******:\n" + params +  "\n*******报文*******:\n"  + responseString+"\n \n";
            Log.d(TGA,log);
            return chain.proceed(request);
        }
    }

    private  String requestBodyToString(final RequestBody request) {
        try {
            final RequestBody copy = request;
            final Buffer buffer = new Buffer();
            if (copy != null){

                copy.writeTo(buffer);
            }
            else{
                return "";
            }
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }

    public ApiService getServer(){
        return mRetrofit.create(ApiService.class);
    }
}
