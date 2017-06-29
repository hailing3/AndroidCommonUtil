package com.mtx.androidcommonutil.util;

import android.app.ActivityManager;
import android.content.Context;
import android.text.TextUtils;

import java.util.List;

/**
 * 管理查询Activity状态的工具
 * Created by lishaoming on 17-6-21.
 */
public class ActivityUtil {
    private ActivityUtil() {
    }

    /**
     * 判断应用是否已经启动
     *
     * @param context     一个context
     * @param packageName 要判断应用的包名
     * @return boolean
     */
    public static boolean isAppAlive(Context context, String packageName) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> processInfos = activityManager.getRunningAppProcesses();
        for (int i = 0; i < processInfos.size(); i++) {
            if (processInfos.get(i).processName.equals(packageName)) {
                return true;
            }
        }
        return false;
    }

    public static String getRunningActivityName(Context context, String packageName) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> processInfos = activityManager.getRunningTasks(10);
        for (ActivityManager.RunningTaskInfo taskInfo : processInfos) {
            if (taskInfo.topActivity != null) {
                if (TextUtils.equals(packageName, taskInfo.topActivity.getPackageName())) {
                    return taskInfo.topActivity.getClassName();
                }
            }
        }
        return null;
    }

}
