package com.trungnguyen.android.houston123.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.trungnguyen.android.houston123.base.BaseUserModel;
import com.trungnguyen.android.houston123.ui.userdetail.detailmodel.StudentModel;

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
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

    /**
     *  Should try/catch when using PUT and GET List of Data from SharedPreferences
     */
    public <T> List<T> getListPreferences(String key, Class<T> type) {
        String sCache = mPreferences.getString(key, EMPTY_STR);
        if (TextUtils.isEmpty(sCache)) {
            return new ArrayList<>();
        }
        return GsonUtils.fromJsonString(sCache, new ListOf<>(type));
    }

    public void putListPreferences(String key, final List<?> objects) {
        String jsonPromotion = GsonUtils.toJsonString(objects);
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
