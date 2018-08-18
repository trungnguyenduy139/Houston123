package com.trungnguyen.android.houston123.util;

import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;

import com.trungnguyen.android.houston123.HoustonApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by trungnd4 on 09/07/2018.
 */
public final class AndroidUtils {

    private static volatile Handler mApplicationHandler;
    private static final String STR_EMPTY = "";
    private static final int INT_EMPTY = 0;
    public static float density = 1;

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

    @NonNull
    public static List<Long> convertArrToList(int[] pArrData) {
        List<Long> excludeAppID = new ArrayList<>();
        for (int appId : pArrData) {
            excludeAppID.add((long) appId);
        }

        return excludeAppID;
    }

    @NonNull
    public static String getAndroidVersion() {
        try {
            String release = Build.VERSION.RELEASE;
            int sdkVersion = Build.VERSION.SDK_INT;
            return "Android " + sdkVersion + " (" + release + ")";
        } catch (Exception e) {
            AppLogger.d(e, "getAndroidVersion() exception [%s]", e.getMessage());
        }
        return STR_EMPTY;
    }

    public static int dp(float value) {
        try {
            if (value == 0)
                return 0;

            return (int) Math.ceil(density * value);
        } catch (Exception e) {
            AppLogger.d(e, "dp exception [%s]", e.getMessage());
        }
        return INT_EMPTY;
    }
}
