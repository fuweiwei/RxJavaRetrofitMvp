package com.veer.rx.net.interceptor;


import android.text.TextUtils;

import com.veer.rx.util.DebugLog;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import static okhttp3.internal.Util.UTF_8;

/**
 * Desc:日志拦截器
 * Created by fww on 2019/3/29
 */
public class LogInterceptor implements Interceptor {
    private SimpleDateFormat format;

    public LogInterceptor() {
        format = new SimpleDateFormat("HH:mm:ss");
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        if (DebugLog.isDebuggable()) {
            logRequest(request);
        }

        Response response = chain.proceed(request);

        if (DebugLog.isDebuggable()) {
            try {
                logResponse(response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return response;
    }


    private void logRequest(Request request) {
        StringBuilder requestLog = new StringBuilder("Request \n");

        //Method
        requestLog.append(String.format("\t Method: %s\n", request.method()));

        //Time
        requestLog.append(String.format("\t Time: %s \n", format.format(new Date(System.currentTimeMillis()))));

        //Content-Type
        if (request.body() != null && request.body().contentType() != null)
            requestLog.append(String.format("\t Content-Type: %s \n", request.body().contentType().toString()));

        String url = request.url().toString();
        String params = null;

        if (request.method().equalsIgnoreCase("Get")) {
            int index = url.indexOf("?");
            if (index > 0) {
                params = url.substring(index + 1);
            }
        }

        //URL
        requestLog.append(String.format("\t Request: %s \n", getUrl(request)));

        //params
        if (!TextUtils.isEmpty(params)) {
            requestLog.append("\t Params:\n");
            String[] arrParam = params.split("&");
            for (String item : arrParam) {
                String[] arrParam2 = item.split("=");
                if (arrParam2.length == 2) {
                    try {
                        requestLog.append(String.format("\t\t %s: %s", arrParam2[0], URLDecoder.decode(arrParam2[1], "UTF-8")));
                    } catch (UnsupportedEncodingException e) {
                        DebugLog.e(e);
                    }
                }
            }
        }

        //Header
        if (request.headers().size() > 0) {
            requestLog.append("\n\t Headers: \n");
            for (String header : request.headers().names()) {
                requestLog.append(String.format("\t\t %s: %s \n", header, request.header(header)));
            }
        }

        //Body
        if (request.body() != null && request.body() instanceof FormBody) {
            requestLog.append("\t Params: \n");

            FormBody body = (FormBody) request.body();
            for (int i = 0; i < body.size(); i++) {
                requestLog.append(String.format("\t\t %s: %s", body.name(i), body.value(i)));
            }
        }

        DebugLog.d(requestLog.toString());


    }


    private void logResponse(Response response) throws IOException {

        StringBuilder requestLog = new StringBuilder("Response \n");

        //URL
        requestLog.append(String.format("\t URL: %s\n", getUrl(response.request())));

        //Time
        requestLog.append(String.format("\t Time: %s \n", format.format(new Date(System.currentTimeMillis()))));

        //Header
        if (response.headers().size() > 0) {
            requestLog.append("\t Headers: \n");
            for (String header : response.headers().names()) {
                requestLog.append(String.format("\t\t %s: %s \n", header, response.header(header)));
            }
        }

        //Body
        ResponseBody body = response.body();
        if (body != null) {
            body.source().request(Long.MAX_VALUE);

            String strResponse = body.source().buffer().clone().readString(body.contentType() != null ? body.contentType().charset(UTF_8) : UTF_8);


            StringBuilder builder = new StringBuilder();

            builder.append("\t Body: \n\t");
            for (int i = 0, indentCount = 1; i < strResponse.length(); i++) {
                char item = strResponse.charAt(i);
                builder.append(item);
                if (item == '{' || item == '[') {
                    indentCount++;
                } else if (item == '}' || item == ']') {
                    indentCount--;
                } else if (item == ',') {

                } else {
                    continue;
                }

                builder.append("\n");
                for (int j = 0; j < indentCount; j++)
                    builder.append("\t");
            }

            requestLog.append(builder.toString());
        }

        DebugLog.d(requestLog.toString());

    }


    private String getUrl(Request request) {
        String url = request.url().toString();

        if (request.method().equalsIgnoreCase("Get")) {
            int index = url.indexOf("?");
            if (index > 0) {
                url = url.substring(0, index);
            }
        }

        return url;
    }
}
