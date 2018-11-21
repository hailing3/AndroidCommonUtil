package com.mtx.androidcommonutil.util;


import com.xiaomi.mistatistic.sdk.MiStatInterface;

import android.content.Context;
import android.text.TextUtils;

import java.util.HashMap;

/**
 * 小米专用统计类
 * Created by lishaoming on 18-11-12.
 */
public class MiStatUtil {
    private static final String TAG = "MiStatUtil";
    private final static String APP_ID = "";
    private final static String APP_KEY = "";

    private MiStatUtil() {
    }

    public static void init(Context context) {
        LogUtil.i(TAG, "init start context = " + context);
        if (context == null) return;
        try {
            MiStatInterface.initialize(context, APP_ID, APP_KEY, "NewHome");
            MiStatInterface.setUploadNetwork(MiStatInterface.NetworkType.TYPE_WIFI);
            MiStatInterface.setUploadPolicy(MiStatInterface.UploadPolicy.UPLOAD_POLICY_INTERVAL, 90 * 1000); // 间隔上报，单位ms，默认90s
            MiStatInterface.enableExceptionCatcher(true);
            MiStatInterface.enableLog(true);
            LogUtil.i(TAG, "init done context = " + context
                    + ", getApplicationContext = " + context.getApplicationContext());
            LogUtil.i(TAG, "设备ID： " + MiStatInterface.getDeviceID(context));
        } catch (Exception e) {
            LogUtil.e(TAG, "MiStatException", e);
        }
    }

    /**
     * 默认只统计一个key的简单情况
     * 默认分类为null，非必选
     */
    public static void recordCountEvent(String key) {
        recordCountEvent(null, key);
    }

    /**
     * 传一个空map，使用recordCountEvent(String type, String key, HashMap<String, String>
     * params)方法统一添加参数
     *
     * @param type 打点事件的分类
     * @param key  打点事件的名称
     */
    public static void recordCountEvent(String type, String key) {
        recordCountEvent(type, key, new HashMap<String, String>());
    }

    public static void recordCountEvent(String type, String key, HashMap<String, String> params) {
        if (params == null) return;
        try {
            MiStatInterface.recordCountEvent(type, key, params);
            printParams(key, params);
        } catch (Exception e) {
            LogUtil.e(TAG, "Exception", e);
        }
    }

    private static void printParams(String methodName, HashMap<String, String> params) {
        if (TextUtils.isEmpty(methodName) || params == null) return;
        StringBuilder sb = new StringBuilder();
        for (String key : params.keySet()) {
            sb.append(key + "=" + params.get(key) + ",");
        }
        LogUtil.d(TAG, methodName + ":" + sb.toString());
    }
}
