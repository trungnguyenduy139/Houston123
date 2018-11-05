package com.trungnguyen.android.houston123.util;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public final class AppUtils {

    //hide soft keyboard
    public static void hideKeyboard(Activity th) {
        View view = th.getCurrentFocus();
        if (view != null) {

            InputMethodManager inputManager = (InputMethodManager) th.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputManager == null) {
                return;
            }
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

        }
    }

    //is network available?
    public static boolean isNetworkAvailable(Context a) {

        ConnectivityManager connectivityManager = (ConnectivityManager)
                a.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null) {
            return false;
        }

        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static String transformToken(String oldToken) {
        return Constants.TOKEN_PREFIX + Constants.SPACE + oldToken;
    }
}
