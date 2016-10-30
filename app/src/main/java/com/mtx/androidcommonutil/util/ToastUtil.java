package com.mtx.androidcommonutil.util;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * Created by lishaoming on 16/10/30.
 */
public class ToastUtil {
    public static void show(Context context, String string) {
        if (context == null || TextUtils.isEmpty(string)) {
            return;
        }
        Toast.makeText(context, string, Toast.LENGTH_SHORT).show();
    }

    public static void show(Context context, int stringId) {
        if (context == null) {
            return;
        }
        Toast.makeText(context, stringId, Toast.LENGTH_SHORT).show();
    }
}
