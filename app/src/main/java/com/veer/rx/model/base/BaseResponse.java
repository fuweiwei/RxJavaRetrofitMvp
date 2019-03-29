package com.veer.rx.model.base;

import com.veer.rx.net.StatusCode;

import java.io.Serializable;

/**
 * Desc: 返回体基础类
 * Created by fww on 2019/3/29
 */
public class BaseResponse<T> implements IResponse, Serializable {
    private int status;

    private String msg;

    private T data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public boolean isSuccess() {
        return status==StatusCode.SUCCESS;
    }
}
