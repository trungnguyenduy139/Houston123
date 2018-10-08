package com.trungnguyen.android.houston123.network;

import android.content.Context;
import android.support.annotation.NonNull;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import timber.log.Timber;

public class BaseNetworkInterceptor implements Interceptor {

    @Inject
    public BaseNetworkInterceptor(Context context) {
        Timber.d("[Tracking] Generate device info for BaseNetworkInterceptor");
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl url = request.url().newBuilder().build();
        request = request.newBuilder().url(url).build();
        return chain.proceed(request);
    }
}
