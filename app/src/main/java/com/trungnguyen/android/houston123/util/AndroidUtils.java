package com.trungnguyen.android.houston123.util;

import android.os.Build;
import android.os.Handler;

import com.trungnguyen.android.houston123.HoustonApplication;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;


/**
 * Created by trungnd4 on 09/07/2018.
 */
public class AndroidUtils {

    private static volatile Handler mApplicationHandler;
    private static final String STR_EMPTY = "";

    static {
        mApplicationHandler = new Handler(HoustonApplication.getInstance().getMainLooper());
    }

    public static void runOnUIThread(Runnable runnable) {
        runOnUIThread(runnable, 0);
    }

    public static void runOnUIThread(Runnable runnable, long delay) {
        // synchronized (_lock) {
        if (delay == 0) {
            mApplicationHandler.post(runnable);
        } else {
            mApplicationHandler.postDelayed(runnable, delay);
        }
        // }
    }

    public static void cancelRunOnUIThread(Runnable runnable) {
        mApplicationHandler.removeCallbacks(runnable);
    }

    //
    public static List<Long> convertArrToList(int[] pArrData) {
        List<Long> excludeAppID = new ArrayList<>();
        for (int appId : pArrData) {
            excludeAppID.add((long) appId);
        }

        return excludeAppID;
    }

    public static String getAndroidVersion() {
        try {
            String release = Build.VERSION.RELEASE;
            int sdkVersion = Build.VERSION.SDK_INT;
            return "Android " + sdkVersion + " (" + release + ")";
        } catch (Exception e) {
            Timber.d(e, "getAndroidVersion() exception [%s]", e.getMessage());
        }
        return STR_EMPTY;
    }
}
