package com.veer.rx.base;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;

import com.blankj.utilcode.util.ToastUtils;
import com.veer.rx.widget.ProgressDialog;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 类描述
 *
 * @author Veer
 * @email 276412667@qq.com
 * @date 18/7/2
 */

public abstract class BaseActivity<P extends BaseContract.IPresenter> extends AppCompatActivity implements BaseContract.IView {
    protected Activity mContext;
    protected P mPresenter;
    private Unbinder mUnBinder;
    private ProgressDialog mDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getActivityLayoutID());
        //强制竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mContext = this;
        //初始化butterknife
        mUnBinder = ButterKnife.bind(this);
        mPresenter = initPresenter();
        mPresenter.setLifecycleOwner(this);
        getLifecycle().addObserver(mPresenter);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnBinder != Unbinder.EMPTY) {
            mUnBinder.unbind();
        }
    }
    /**
     * 初始化View
     */
    protected abstract void initView();
    /**
     * 在子View中初始化Presenter
     *
     * @return
     */
    protected abstract P initPresenter();
    /**
     * 设置Activity的布局ID
     *
     * @return
     */
    protected abstract int getActivityLayoutID();

    @Override
    public void showLoading() {
        mDialog = new ProgressDialog(this);
        mDialog.show();
    }

    @Override
    public void hideLoading() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    @Override
    public void showSuccess(String message) {
        ToastUtils.showShort(message);
    }

    @Override
    public void showFailed(String message) {
        ToastUtils.showShort(message);
    }

    @Override
    public void showNoNet() {
        ToastUtils.showShort("无网络");
    }

    @Override
    public void onRetry() {

    }
}
