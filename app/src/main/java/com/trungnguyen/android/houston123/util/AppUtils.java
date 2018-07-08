package com.trungnguyen.android.houston123.util;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Base64;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.io.UnsupportedEncodingException;

public class AppUtils {

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
    public static boolean isNetworkAvailable(Activity a) {

        ConnectivityManager connectivityManager = (ConnectivityManager)
                a.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null) {
            return false;
        }

        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();

    }

    public static String encrypt(String myData) throws UnsupportedEncodingException {
        byte[] data = myData.getBytes("UTF-8");
        return Base64.encodeToString(data, Base64.DEFAULT);
    }

    public static String decryt(String d) throws UnsupportedEncodingException {
        byte[] data = Base64.decode(d, Base64.DEFAULT);
        String text = new String(data, "UTF-8");
        return text;
    }

}
