package com.veer.rx.ui.main;

import com.veer.rx.base.BaseContract;
import com.veer.rx.widget.FrameLayout4Loading;

/**
 * 主页配置约定
 * @author Veer
 * @email 276412667@qq.com
 * @date 18/8/16
 */

public interface MainContract {
    interface View extends BaseContract.BaseView{
        void loginSuccess(String msg);
        void loginError(String msg);

    }
    interface Presenter extends BaseContract.BasePresenter<View>{
        void login(FrameLayout4Loading frameLayout4Loading, String name, String password);
    }
}
