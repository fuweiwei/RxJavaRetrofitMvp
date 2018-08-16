package com.veer.rx.ui.main;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ToastUtils;
import com.veer.rx.R;
import com.veer.rx.base.BaseActivity;
import com.veer.rx.common.ActivityContracts;
import com.veer.rx.widget.FrameLayout4Loading;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<MainContract.Presenter> implements MainContract.View  {
    @BindView(R.id.et_name)
    EditText mEtName;
    @BindView(R.id.et_password)
    EditText mEtPassWord;
    @BindView(R.id.btn)
    Button mBtn;
    @BindView(R.id.loading)
    FrameLayout4Loading mFrameLayout4Loading;

    @Override
    protected void initView() {

    }

    @Override
    protected MainContract.Presenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    protected int getActivityLayoutID() {
        return R.layout.activity_main;
    }

    @OnClick(R.id.btn)
    public void onToBanner(View view){
        String name = mEtName.getText().toString();
        String password = mEtPassWord.getText().toString();
        if(!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(password)){
            mPresenter.login(mFrameLayout4Loading,name,password);
        }else{
            ToastUtils.showShort("请输入正确的用户和密码");
        }
    }

    @Override
    public void loginSuccess(String msg) {
        ToastUtils.showShort(msg);
        ARouter.getInstance()
                .build(ActivityContracts.ACTIVITY_BOOK)
                .navigation();
    }

    @Override
    public void loginError(String msg) {
        ToastUtils.showShort(msg);

    }
}
