package com.trungnguyen.android.houston123.util

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

class PreferencesUtil(context: Context) : PersistentPrefs {
    private val mPreferences: SharedPreferences

    private fun getSharedPreferenceUtil(context: Context): SharedPreferences {
        return context.getSharedPreferences(Constants.APP_NAME, Activity.MODE_PRIVATE)
    }

    init {
        mPreferences = getSharedPreferenceUtil(context)
    }

    public fun putPreferences(key: String, value: Any) {
        mPreferences[key] = value
    }

    public fun getPreferences(key: String): Any? {
        return mPreferences[key]
    }

    public fun getPreferences(key: String, default: Any): Any? {
        return mPreferences[key, default]
    }

    inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = this.edit()
        operation(editor)
        editor.apply()
    }

    operator fun SharedPreferences.set(key: String, value: Any?) {
        when (value) {
            is String -> edit { it.putString(key, value) }
            is Int -> edit { it.putInt(key, value) }
            is Float -> edit { it.putFloat(key, value) }
            is Long -> edit { it.putLong(key, value) }
            is Boolean -> edit { it.putBoolean(key, value) }
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
    }

    inline operator fun <reified T : Any> SharedPreferences.get(key: String, defaultValue: T? = null): T? {
        return when (T::class) {
            String::class -> getString(key, defaultValue as? String) as T?
            Int::class -> getInt(key, defaultValue as? Int ?: -1) as T?
            Boolean::class -> getBoolean(key, defaultValue as? Boolean ?: false) as T?
            Float::class -> getFloat(key, defaultValue as? Float ?: -1f) as T?
            Long::class -> getLong(key, defaultValue as? Long ?: -1) as T?
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
    }
}
