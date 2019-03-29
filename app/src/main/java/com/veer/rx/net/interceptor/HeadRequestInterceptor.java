package com.veer.rx.net.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Desc: 请求头拦截器
 * Created by fww on 2019/3/29
 */
public class HeadRequestInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request  =  chain.request()
                .newBuilder()
                .addHeader("X-APP-TYPE","android")
                .build();
        Response response = chain.proceed(request);
        return response;
    }
}
