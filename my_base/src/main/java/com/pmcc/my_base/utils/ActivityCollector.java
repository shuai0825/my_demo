package com.pmcc.my_base.utils;

import android.app.Activity;

import com.pmcc.my_base.net.RetrofitManager;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by ${zhangshuai} on 2018/11/29.
 * 2751157603@qq.com
 */
public class ActivityCollector {
    private static ActivityCollector activityCollector;

    public static ActivityCollector getInstance() {
        if (activityCollector == null) {
            synchronized (RetrofitManager.class) {
                if (activityCollector == null) {
                    activityCollector = new ActivityCollector();
                }
            }
        }
        return activityCollector;
    }

    public  HashMap<Class<?>, Activity> activities = new LinkedHashMap<>();

    /**
     * 判断当前activity是否存在
     * @param clz
     * @return
     */
    public  boolean isActivityExist(Class<?> clz) {
        boolean res;
        Activity activity = activities.get(clz);
        if (activity == null) {
            res = false;
        } else {
            if (activity.isFinishing() || activity.isDestroyed()) {
                res = false;
            } else {
                res = true;
            }
        }

        return res;
    }

    /**
     * 添加当前activity
     *
     * @param activity
     * @param clz
     */
    public  void addActivity(Class<?> clz, Activity activity) {
        activities.put(clz, activity);
    }

    /**
     * 退出，在集合中移除当前activity
     *
     * @param activity
     */
    public  void removeActivity(Activity activity) {
        if (activities.containsValue(activity)) {
            activities.remove(activity.getClass());
        }
    }

    /**
     * 移除activity,代替finish
     */
    public  void finishActivity(Class<?> clz) {
        if (activities.containsKey(clz)) {
            activities.get(clz).finish();
            activities.remove(clz);
        }
    }

    /**
     * 退出所有activity
     */
    public  void removeAllActivity() {
        if (activities != null && activities.size() > 0) {
            Set<Map.Entry<Class<?>, Activity>> sets = activities.entrySet();
            for (Map.Entry<Class<?>, Activity> s : sets) {
                if (!s.getValue().isFinishing()) {
                    s.getValue().finish();
                }
            }
        }
        activities.clear();
    }
}
