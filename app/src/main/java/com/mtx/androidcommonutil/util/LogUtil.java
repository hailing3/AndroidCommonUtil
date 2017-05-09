package com.mtx.androidcommonutil.util;

import android.util.Log;

/**
 * 自定义的Log工具，由DEBUG控制是否打印log信息
 * Created by lishaoming on 2016/11/1.
 */
public class LogUtil {
    public static final boolean DEBUG = true;

    private LogUtil() {
    }

    public static int d(String TAG, String string) {
        return DEBUG ? Log.d(TAG, string) : -1;
    }

    public static int i(String TAG, String string) {
        return DEBUG ? Log.i(TAG, string) : -1;
    }

    public static int w(String TAG, String string) {
        return DEBUG ? Log.w(TAG, string) : -1;
    }

    public static int e(String TAG, String string) {
        return DEBUG ? Log.e(TAG, string) : -1;
    }

}
