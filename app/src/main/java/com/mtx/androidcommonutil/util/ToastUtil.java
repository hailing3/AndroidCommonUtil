package com.mtx.androidcommonutil.util;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * 优化后的Toast工具类，短时间内连续调用，Toast会把之前的先消失掉，立即展现
 * Created by lishaoming on 16/10/30.
 */
public class ToastUtil {
    private ToastUtil() {
    }

    private static Toast sToast;

    public static void show(Context context, String string) {
        if (context == null || TextUtils.isEmpty(string)) return;
        if (sToast != null) sToast.cancel();
        sToast = Toast.makeText(context, string, Toast.LENGTH_SHORT);
        sToast.show();
    }

    public static void show(Context context, int stringId) {
        if (context == null) return;
        if (sToast != null) sToast.cancel();
        sToast = Toast.makeText(context, stringId, Toast.LENGTH_SHORT);
        sToast.show();
    }
}
