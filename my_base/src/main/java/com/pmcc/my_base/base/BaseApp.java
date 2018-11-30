package com.pmcc.my_base.base;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.pmcc.my_base.utils.DeviceUtil;
import com.pmcc.my_base.utils.NeverCrash;


/**
 * Created by ${zhangshuai} on 2018/11/28.
 * 2751157603@qq.com
 */
public class BaseApp extends Application {
    private static Context appContext;
    @Override
    public void onCreate() {
        super.onCreate();
        appContext=this;
        initArouter();
        initCrash();

    }
    public static Context getAppContext() {
        return appContext;
    }

    /**
     * 异常捕获
     */
    private void initCrash() {
        NeverCrash.init(new NeverCrash.CrashHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
//                Intent intent = new Intent(instance, ErrorActivity.class);
//                intent.putExtra("error", Log.getStackTraceString(e));
//                startActivity(intent);
            }
        });
    }

    /**
     * 初始化arouter
     */
    private void initArouter() {
       if (DeviceUtil.isDebug()) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this);
    }



}
