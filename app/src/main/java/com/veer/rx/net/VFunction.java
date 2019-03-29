package com.veer.rx.net;


import com.veer.rx.model.base.BaseResponse;

import io.reactivex.functions.Function;

/**
 * Desc:Function的封装 以便后续扩展
 * Created by fww on 2019/3/6
 */
public abstract class VFunction<T,R> implements Function<BaseResponse<T>,R> {

    @Override
    public R apply(BaseResponse<T> statusResponse) throws Exception {
        applyA(statusResponse.getData());
        return null;
    }
    protected abstract R applyA(T t) ;
}
