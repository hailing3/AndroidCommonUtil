package com.mtx.androidcommonutil.util.statusbar;

import android.content.Context;
import android.view.Window;
import android.view.WindowManager;

/**
 * 修改状态栏相关状态的工具类
 * Created by lishaoming on 16-11-24.
 */

public class StatusBarUtil {

    private StatusBarUtil() {
    }

    /**
     * 是否显示状态栏
     *
     * @param isShow
     * @param window
     */
    public static void setStatusBarShowOrHidden(boolean isShow, Window window) {
        if (window == null) {
            return;
        }
        android.view.WindowManager.LayoutParams params = window.getAttributes();
        if (isShow) {
            params.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
//            window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
            window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        } else {
            params.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
//            window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
            window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        window.setAttributes(params);
    }

    /**
     * 判断状态栏是否显示
     *
     * @param window
     * @return
     */
    public static boolean isStatusBarShow(Window window) {
        if (window == null) {
            return false;
        }
        WindowManager.LayoutParams attrs = window.getAttributes();
        if ((attrs.flags & WindowManager.LayoutParams.FLAG_FULLSCREEN) == WindowManager.LayoutParams.FLAG_FULLSCREEN) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 获取状态栏高度
     *
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        if (context == null) {
            return 0;
        }
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen",
                "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
