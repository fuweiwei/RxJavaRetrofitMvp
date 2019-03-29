package com.veer.rx.net;

import android.accounts.NetworkErrorException;
import android.content.Context;
import android.support.annotation.CallSuper;
import android.text.TextUtils;

import com.veer.rx.R;
import com.veer.rx.model.base.BaseResponse;

import java.lang.ref.WeakReference;
import java.net.ConnectException;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Desc:
 * Created by fww on 2019/3/29
 */
public abstract class VObserver<T> implements Observer<BaseResponse<T>> {
    private static final String TAG = "ATObserver";
    protected WeakReference<Context> mContext;

    public VObserver(Context context) {
        this.mContext = new WeakReference(context);
    }

    @Override
    public void onSubscribe(Disposable d) {
        //请求开始

    }

    @Override
    public void onNext(BaseResponse<T> tBaseResponse) {
        //根据业务来分
        if(tBaseResponse.getStatus()==StatusCode.SUCCESS){
            onSuccess(tBaseResponse.getData());
        }else{
            onFailure(tBaseResponse.getStatus(),tBaseResponse.getMsg(),true);
        }

    }

    @Override
    public void onError(Throwable e) {
        try {
            if (e instanceof ConnectException || e instanceof NetworkErrorException) {
                onFailure(StatusCode.ERROR_NONETWORK, "网络错误",true);
            } else if(e instanceof TimeoutException) {
                onFailure(StatusCode.ERROR_TIMEOUT, "请求超时",true);
            }else{
                onFailure(StatusCode.ERROR_EXCEPTION, "系统异常",true);
            }
        } catch (Exception e1) {
            onFailure(StatusCode.ERROR_EXCEPTION, "系统异常",true);
        }
    }

    @Override
    public void onComplete() {
        //请求完成
    }

    /**
     * 返回成功
     * @param t 返回体
     */
    protected abstract void onSuccess(T t) ;

    /**
     * 返回失败  子类覆盖时必须super
     * 可对通用问题进行拦截
     * @param code  错误码
     * @param message 消息
     * @param isShowMsg 是否显示错误消息
     */
    @CallSuper
    protected  void onFailure(int code, String message,boolean isShowMsg){
        if(!TextUtils.isEmpty(message)&&isShowMsg){

        }
        //判断状态码 进行拦截

    }
}

