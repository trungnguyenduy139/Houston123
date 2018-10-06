package com.trungnguyen.android.houston123.rx;

/**
 * Created by trungnd4 on 06/10/2018.
 */

import com.trungnguyen.android.houston123.exception.HttpEmptyResponseException;
import com.trungnguyen.android.houston123.exception.NetworkConnectionException;

import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import retrofit2.HttpException;
import timber.log.Timber;

/**
 * Created by chucvv on 9/13/18.
 */
public class ObservableRetryPattern {
    public static final List<Class<?>> NETWORK_EXCEPTION_CLAZZ =
            Arrays.asList(HttpEmptyResponseException.class,
                    HttpException.class,
                    NetworkConnectionException.class,
                    TimeoutException.class,
                    SocketTimeoutException.class,
                    SocketException.class);

    public static <R> ObservableTransformer<R, R> transformObservable(R defaultValue) {
        return upstream -> upstream.onErrorResumeNext(throwable -> {
            Timber.w("[RX] Stream error with type [%s %s]", defaultValue.getClass().getSimpleName(), throwable.getClass().getSimpleName());
            if (NETWORK_EXCEPTION_CLAZZ.contains(throwable.getClass())) {
                return Observable.just(defaultValue);
            }
            return Observable.error(throwable);
        })
                .first(defaultValue)
                .toObservable();
    }
}

