package com.trungnguyen.android.houston123.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import java.util.Collection;


public final class PrefsUtil implements PersistentPrefs {

    private static SharedPreferences getSharedPreferenceUtil(Context context) {
        return context.getSharedPreferences(Constants.APP_NAME, Activity.MODE_PRIVATE);
    }

    public static void saveListPreferences(String key, Context context, Collection<Object> collection) {
        StringBuilder builder = new StringBuilder();
        for (Object object : collection) {
            builder.append(object);
            builder.append(DIVIDER_CHAR);
        }
        SharedPreferences.Editor editor = getSharedPreferenceUtil(context).edit();
        editor.putString(key, builder.toString());
        editor.apply();
    }

    public static String[] getListPreferences(Context context, String key) {
        SharedPreferences preferences = getSharedPreferenceUtil(context);
        String setData = preferences.getString(key, EMPTY_STR);
        if (!TextUtils.isEmpty(setData))
            return setData.split(DIVIDER_CHAR);
        else {
            return new String[]{};
        }
    }

    public static void saveStringPreferences(Context context, String key, String value) {
        SharedPreferences.Editor editor = getSharedPreferenceUtil(context).edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getStringPreferences(Context context, String key, String defaultValue) {
        SharedPreferences preferences = getSharedPreferenceUtil(context);
        return preferences.getString(key, defaultValue);
    }

    public static String getStringPreferences(Context context, String key) {
        return getStringPreferences(context, key, EMPTY_STR);
    }

    public static void saveIntPreferences(Context context, String key, int value) {
        SharedPreferences.Editor editor = getSharedPreferenceUtil(context).edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static int getIntPreferences(Context context, String key, int defaultValue) {
        SharedPreferences preferences = getSharedPreferenceUtil(context);
        return preferences.getInt(key, defaultValue);
    }

    public static void saveBoolPreferences(Context context, String key, boolean value) {
        SharedPreferences.Editor editor = getSharedPreferenceUtil(context).edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static boolean getBoolPreferences(Context context, String key, boolean defaultValue) {
        SharedPreferences preferences = getSharedPreferenceUtil(context);
        return preferences.getBoolean(key, defaultValue);
    }

    public static boolean getBoolPreferences(Context context, String key) {
        return getBoolPreferences(context, key, DEFAULT_BOOL_VALUE);
    }

}
