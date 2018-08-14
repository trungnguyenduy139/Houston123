package com.trungnguyen.android.houston123.util;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;

public final class PermissionUtil {
    private PermissionUtil() {
        // private constructor for utils class
    }

    public static boolean verifyPermission(int[] grantResult) {

        if (grantResult.length < 1) {
            return false;
        }

        for (int result : grantResult) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }

        return true;
    }

    public static boolean checkAndroidMVersion() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    @NonNull
    public static int[] getSelfPermission(@NonNull Context context, String[] permission) {
        int[] selfPermission = new int[permission.length];
        for (int i = 0; i < permission.length; i++) {
            selfPermission[i] = ContextCompat.checkSelfPermission(context, permission[i]);
        }

        return selfPermission;
    }


    public static boolean verifyPermission(@NonNull Context context, String[] permission) {
        int[] selfPermission = new int[permission.length];
        for (int i = 0; i < permission.length; i++) {
            selfPermission[i] = ContextCompat.checkSelfPermission(context, permission[i]);
        }

        return verifyPermission(selfPermission);
    }
}