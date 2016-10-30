package com.mtx.androidcommonutil.model;

import android.webkit.JavascriptInterface;

import com.mtx.androidcommonutil.MyApplication;
import com.mtx.androidcommonutil.util.ToastUtil;

/**
 * H5界面调用NA方法的实体对象
 * Created by lishaoming on 16/10/30.
 */
public class NaObject {

    //JavaScript调用此方法
    @JavascriptInterface
    public void callAndroidMethod(int a, String s, boolean b) {
        ToastUtil.show(MyApplication.getContext(), "H5传入的参数：" + a + "," + s + "," + b);
    }
}
