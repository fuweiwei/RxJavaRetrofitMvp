package com.veer.rx.base;

/**
 * 基类Presenter
 * @author Veer
 * @email 276412667@qq.com
 * @date 18/7/2
 */

public class BasePresenter<T extends BaseContract.BaseView> implements BaseContract.BasePresenter<T>{
    protected T mView;
    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        if (mView != null) {
            mView = null;
        }
    }
}
