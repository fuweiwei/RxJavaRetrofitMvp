package com.veer.rx.util;

import android.text.TextUtils;
import android.util.Log;

import com.veer.rx.BuildConfig;


/**
 * Desc:log封装类
 * Created by fww on 2019/3/29
 */
public class DebugLog {

    /**
     * 类名
     */
    private static String className;

    /**
     * 方法名
     */
    private static String methodName;

    /**
     * 行数
     */
    private static int lineNumber;

    private static boolean isDebuggable = BuildConfig.DEBUG;

    private DebugLog() {
    }

    /**
     * 使用前先打开调试开关
     *
     * @param debuggable
     */
    public static void setDebuggable(boolean debuggable) {
        isDebuggable = debuggable;
    }

    public static boolean isDebuggable() {
        return isDebuggable;
    }

    private static String createLog(String log) {

        StringBuffer buffer = new StringBuffer();
        buffer.append("[");
        buffer.append(methodName);
        buffer.append(":");
        buffer.append(lineNumber);
        buffer.append("]");
        buffer.append(log);

        return buffer.toString();
    }

    private static void getMethodNames(StackTraceElement[] sElements) {
        className = sElements[3].getFileName();
        methodName = sElements[3].getMethodName();
        lineNumber = sElements[3].getLineNumber();
    }

    public static void e(String message) {

        if (!isDebuggable())
            return;
        getMethodNames(Thread.currentThread().getStackTrace());
        Log.e(className, createLog(message));
    }

    public static void e(Throwable e) {
        if (!isDebuggable())
            return;

        getMethodNames(Thread.currentThread().getStackTrace());
        Log.e(className, createLog(String.format("Exception: %s. Caused by %s. Detail message: %s", e.toString(), e.getCause(), e.getMessage())));
    }

    public static void i(String message) {
        if (!isDebuggable())
            return;

        getMethodNames(Thread.currentThread().getStackTrace());
        Log.i(className, createLog(message));
    }

    public static void d(String message) {
        if (!isDebuggable())
            return;

        getMethodNames(Thread.currentThread().getStackTrace());
        Log.d(className, createLog(message));
    }

    public static void v(String message) {
        if (!isDebuggable())
            return;
        getMethodNames(Thread.currentThread().getStackTrace());
        Log.v(className, createLog(message));
    }

    public static void w(String message) {
        if (!isDebuggable())
            return;

        getMethodNames(Thread.currentThread().getStackTrace());
        Log.w(className, createLog(message));
    }

    public static void wtf(String message) {
        if (!isDebuggable())
            return;

        getMethodNames(Thread.currentThread().getStackTrace());
        Log.wtf(className, createLog(message));
    }

    public static void e(String message, Throwable tr) {
        if (!isDebuggable())
            return;
        getMethodNames(Thread.currentThread().getStackTrace());
        Log.e(className, createLog(message), tr);
    }

    public static void wtf(String message, Throwable tr) {
        if (!isDebuggable())
            return;
        getMethodNames(Thread.currentThread().getStackTrace());
        Log.wtf(className, createLog(message), tr);
    }

    /**
     * 打印调用栈信息
     *
     * @param depth 最小为0，则只打印调用此函数的栈，每加1，则栈深度加1
     */
    public static void stackDepth(int depth) {
        if (!isDebuggable)
            return;

        String className = "";
        StringBuilder logBuilder = new StringBuilder();
        for (int i = 3; i < Thread.currentThread().getStackTrace().length; i++) {
            if (i - 3 > depth)
                break;

            StackTraceElement element = Thread.currentThread().getStackTrace()[i];
            logBuilder.append(element.getFileName() + ":" + element.getMethodName() + ":" + element.getLineNumber());
            logBuilder.append("\n");

            if (TextUtils.isEmpty(className))
                className = element.getFileName();
        }

        Log.d(className, logBuilder.toString());
    }

    /********************
     * 一般调试
     *********************/
    public static void v(String tag, String msg) {
        if (!isDebuggable() || msg == null)
            return;
        Log.v(tag, msg);

    }

    public static void d(String tag, String msg) {
        if (!isDebuggable() || msg == null)
            return;
        Log.d(tag, msg);

    }

    public static void i(String tag, String msg) {
        if (!isDebuggable() || msg == null)
            return;
        Log.i(tag, msg);

    }

    public static void w(String tag, String msg) {
        if (!isDebuggable() || msg == null)
            return;
        Log.w(tag, msg);

    }

    public static void e(String tag, String msg) {
        if (!isDebuggable() || msg == null)
            return;
        Log.e(tag, msg);

    }

    public static void e(String tag, String msg, Throwable tr) {
        if (!isDebuggable() || msg == null)
            return;
        Log.e(tag, msg, tr);
    }

    public static void wtf(String tag, String msg, Throwable tr) {
        if (!isDebuggable() || msg == null)
            Log.wtf(tag, msg, tr);
    }
    /******************** end *********************/
}

