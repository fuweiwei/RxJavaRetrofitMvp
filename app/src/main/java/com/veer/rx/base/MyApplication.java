package com.veer.rx.base;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.Utils;
import com.veer.rx.BuildConfig;

/**
 * 描述
 *
 * @author Veer
 * @email 276412667@qq.com
 * @date 18/7/2
 */

public class MyApplication extends Application {
    private static MyApplication mMyApplication;
    @Override
    public void onCreate() {
        super.onCreate();
        mMyApplication = this;
        //初始化工具包
        Utils.init(this);
        intARouter();
    }
    /**
     * 初始化路由
     */
    private void intARouter() {
        // 这两行必须写在init之前，否则这些配置在init过程中将无效
        if (BuildConfig.DEBUG) {
            // 打印日志
            ARouter.openLog();
            // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
            ARouter.openDebug();
        }
        // 尽可能早，推荐在Application中初始化
        ARouter.init(this);
    }
    public static MyApplication getApplication() {
        return mMyApplication;
    }
}
