package com.mtx.androidcommonutil;

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
    }

    public static Context getContext() {
        return mContext;
    }
}
