package com.trungnguyen.android.houston123.network;

import android.support.annotation.Keep;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Keep
@Target(METHOD)
@Retention(RUNTIME)
public @interface API_NAME {
    @Keep
    int https();

    @Keep
    int connector();
}
