package com.mtx.androidcommonutil;

import com.mtx.androidcommonutil.util.MiStatUtil;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import android.app.Application;
import android.content.Context;

/**
 * Created by lishaoming on 16/10/30.
 */
public class MyApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        mContext = getApplicationContext();
        ZXingLibrary.initDisplayOpinion(this);
        MiStatUtil.init(this);
    }

    public static Context getContext() {
        return mContext;
    }
}
