package com.pmcc.my_base.utils;

import android.content.pm.PackageManager;

import com.pmcc.my_base.BuildConfig;
import com.pmcc.my_base.base.BaseApp;

/**
 * Created by ${zhangshuai} on 2018/11/29.
 * 2751157603@qq.com
 */
public class DeviceUtil {
    /**
     * 判断debug模式
     * @return
     */
    public static boolean isDebug() {
        return BuildConfig.DEBUG;
    }
    /**
     * 获得当前版本号
     *
     * @return
     */
    public static int getVersionCode() {
        int versionCode = 0;
        try {
            versionCode = BaseApp.getAppContext().getPackageManager().
                    getPackageInfo(BaseApp.getAppContext().getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * 获得当前版本名称
     *
     * @return
     */
    public static String getVersionName() {
        String versionName = "";
        try {
            versionName = BaseApp.getAppContext().getPackageManager().
                    getPackageInfo(BaseApp.getAppContext().getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }
}
