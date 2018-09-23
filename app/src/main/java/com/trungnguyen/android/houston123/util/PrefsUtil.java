package com.trungnguyen.android.houston123.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public final class PrefsUtil implements PersistentPrefs {

    private SharedPreferences getSharedPreferenceUtil(Context context) {
        return context.getSharedPreferences(Constants.APP_NAME, Activity.MODE_PRIVATE);
    }

    private SharedPreferences mPreferences;

    public PrefsUtil(Context context) {
        mPreferences = getSharedPreferenceUtil(context);
    }

    public List<Object> getListPreferences(String key) {
        String sCache = mPreferences.getString(key, StringUtils.EMPTY);
        if (TextUtils.isEmpty(sCache)) {
            return new ArrayList<>();
        }
        Type type = new TypeToken<List<Object>>() {
        }.getType();
        return GsonUtils.fromJsonString(sCache, type);
    }

    public void putListPreferences(String key, final List<Object> notify) {
        String jsonPromotion = GsonUtils.toJsonString(notify);
        mPreferences.edit()
                .putString(key, jsonPromotion)
                .apply();
    }

    public void saveStringPreferences(String key, String value) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    @Nullable
    public String getStringPreferences(String key, String defaultValue) {
        SharedPreferences preferences = mPreferences;
        return preferences.getString(key, defaultValue);
    }

    @Nullable
    public String getStringPreferences(String key) {
        return getStringPreferences(key, EMPTY_STR);
    }

    public void saveIntPreferences(String key, int value) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public int getIntPreferences(String key, int defaultValue) {
        SharedPreferences preferences = mPreferences;
        return preferences.getInt(key, defaultValue);
    }

    public void saveBoolPreferences(String key, boolean value) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public boolean getBoolPreferences(String key, boolean defaultValue) {
        SharedPreferences preferences = mPreferences;
        return preferences.getBoolean(key, defaultValue);
    }

    public boolean getBoolPreferences(String key) {
        return getBoolPreferences(key, DEFAULT_BOOL_VALUE);
    }

}
