package com.veer.rx.net;

/**
 * Desc: 状态码约定
 * Created by fww on 2019/3/4
 */
public class StatusCode {
    /**
     * 数据请求成功
     */
    public static final int SUCCESS = 0;
    /**
     * 网络错误
     */
    public static final int ERROR_NONETWORK = 4000;
    /**
     * 网络超时
     */
    public static final int ERROR_TIMEOUT = 4001;
    /**
     * 异常
     */
    public static final int ERROR_EXCEPTION = 4004;

}
