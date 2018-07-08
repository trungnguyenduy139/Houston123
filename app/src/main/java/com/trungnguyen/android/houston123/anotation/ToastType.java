package com.trungnguyen.android.houston123.anotation;


import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.trungnguyen.android.houston123.anotation.ToastType.TYPE_ERROR;
import static com.trungnguyen.android.houston123.anotation.ToastType.TYPE_GENERAL;
import static com.trungnguyen.android.houston123.anotation.ToastType.TYPE_OK;

@Retention(RetentionPolicy.SOURCE)
@StringDef({
        TYPE_ERROR, TYPE_GENERAL, TYPE_OK
})
public @interface ToastType {

    String TYPE_ERROR = "TYPE_ERROR";
    String TYPE_GENERAL = "TYPE_GENERAL";
    String TYPE_OK = "TYPE_OK";
}
