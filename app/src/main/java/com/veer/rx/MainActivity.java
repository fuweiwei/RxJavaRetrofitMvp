package com.veer.rx;

import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.launcher.ARouter;
import com.veer.rx.base.BaseActivity;
import com.veer.rx.base.BaseContract;
import com.veer.rx.base.BasePresenter;
import com.veer.rx.common.ActivityContracts;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    @BindView(R.id.btn)
    Button mBtn;

    @Override
    protected void initView() {

    }

    @Override
    protected BaseContract.BasePresenter initPresenter() {
        return new BasePresenter();
    }

    @Override
    protected int getActivityLayoutID() {
        return R.layout.activity_main;
    }

    @OnClick(R.id.btn)
    public void onToBanner(View view){
        ARouter.getInstance()
                .build(ActivityContracts.ACTIVITY_BANNER)
                .navigation();
    }
}
