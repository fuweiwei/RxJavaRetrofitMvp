package com.veer.rx.ui.main;


import android.os.Handler;
import android.text.TextUtils;

import com.veer.rx.base.BasePresenter;
import com.veer.rx.db.dao.DbUserDao;
import com.veer.rx.db.entity.User;
import com.veer.rx.widget.FrameLayout4Loading;

/**
 * 描述
 *
 * @author Veer
 * @email 276412667@qq.com
 * @date 18/8/16
 */

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter{

    @Override
    public void login(FrameLayout4Loading frameLayout4Loading, final String name, final String password) {
        mView.showLoading();
        new Handler().postDelayed(new Runnable(){
            public void run() {
                if(TextUtils.equals(name,"veer")&&TextUtils.equals(password,"v123456")){
                    User user = new User();
                    user.setName("veer");
                    user.setPassword("v123456");
                    DbUserDao.getInstance().addUser(user);
                    mView.loginSuccess("登录成功");
                }else{
                    mView.loginError("密码错误！");
                }
                mView.hideLoading();
            }
        }, 2000);

    }
}
