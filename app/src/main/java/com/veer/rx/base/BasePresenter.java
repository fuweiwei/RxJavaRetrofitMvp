package com.veer.rx.base;

import com.uber.autodispose.AutoDisposeConverter;
import com.veer.rx.util.RxLifecycleUtils;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

/**
 * 基类Presenter
 * @author Veer
 * @email 276412667@qq.com
 * @date 18/7/2
 */

public class BasePresenter<T extends BaseContract.IView> implements BaseContract.IPresenter<T> {
    protected T mView;
    private LifecycleOwner mLifecycleOwner;


    public BasePresenter(T view){
        this.mView = view;
    }

    protected  <T> AutoDisposeConverter<T> bindToLife() {
        if (null == mLifecycleOwner)
            throw new NullPointerException("mLifecycleOwner == null");
        return RxLifecycleUtils.bindLifecycle(mLifecycleOwner);
    }

    @Override
    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        this.mLifecycleOwner = lifecycleOwner;
    }

    @Override
    public void onCreate(LifecycleOwner owner) {

    }

    @Override
    public void onDestroy(LifecycleOwner owner) {
        if (mView != null) {
            mView = null;
        }
    }

    @Override
    public void onLifecycleChanged(LifecycleOwner owner, Lifecycle.Event event) {
    }
}
