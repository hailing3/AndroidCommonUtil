package com.mtx.androidcommonutil.util.pref;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 自定义那个Preference工具类
 * 所有键，都定义在Pref文件中
 */
public class PreferenceUtil {
    private PreferenceUtil() {
    }

    public static void setInt(Context context, String key, int value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(Pref.KEY_FILE_NAME, Context.MODE_PRIVATE).edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static int getInt(Context context, String key, int defValue) {
        SharedPreferences sharedPref = context.getSharedPreferences(Pref.KEY_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPref.getInt(key, defValue);
    }

    public static void setLong(Context context, String key, long value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(Pref.KEY_FILE_NAME, Context.MODE_PRIVATE).edit();
        editor.putLong(key, value);
        editor.commit();
    }

    public static long getLong(Context context, String key, long defValue) {
        SharedPreferences sharedPref = context.getSharedPreferences(Pref.KEY_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPref.getLong(key, defValue);
    }

    public static void setBoolean(Context context, String key, boolean value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(Pref.KEY_FILE_NAME, Context.MODE_PRIVATE).edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static boolean getBoolean(Context context, String key, boolean defValue) {
        SharedPreferences sharedPref = context.getSharedPreferences(Pref.KEY_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPref.getBoolean(key, defValue);
    }


    public static void setString(Context context, String key, String value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(Pref.KEY_FILE_NAME, Context.MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getString(Context context, String key, String defValue) {
        SharedPreferences sharedPref = context.getSharedPreferences(Pref.KEY_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPref.getString(key, defValue);
    }

}
