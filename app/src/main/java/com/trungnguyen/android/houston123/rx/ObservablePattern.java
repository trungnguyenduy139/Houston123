package com.trungnguyen.android.houston123.rx;

import android.text.TextUtils;

import com.trungnguyen.android.houston123.data.BaseResponse;
import com.trungnguyen.android.houston123.exception.BodyException;
import com.trungnguyen.android.houston123.exception.HttpEmptyResponseException;
import com.trungnguyen.android.houston123.exception.NetworkConnectionException;
import com.trungnguyen.android.houston123.util.Constants;

import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import retrofit2.HttpException;
import timber.log.Timber;

public class ObservablePattern {
    private static final List<Class<?>> NETWORK_EXCEPTION_CLAZZ =
            Arrays.asList(HttpEmptyResponseException.class,
                    HttpException.class,
                    NetworkConnectionException.class,
                    TimeoutException.class,
                    SocketTimeoutException.class,
                    SocketException.class);

    /**
     * onErrorResumeNext: When the main stream throw an Exception
     * an Exception will be catch, continue doing some stuff is defined in onErrorResumeNext() ->
     *
     * @param defaultValue
     * @param <R>
     * @return a new Observable from onErrorResumeNext and merge to the main stream
     */

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

    /**
     * A pattern for the first step process response
     *
     * @param response
     * @param <R>
     * @return response or an error Observable with common exception
     */

    public static <R extends BaseResponse> Observable<R> responseProcessingPattern(R response) {
        if (response == null) {
            return Observable.error(HttpEmptyResponseException::new);
        }
        if (response.returncode == Constants.ServerCode.SUCCESS) {
            return Observable.just(response);
        }
        String message = response.message;
        String clientMessage = TextUtils.isEmpty(message) ? Constants.EMPTY : message;
        return Observable.error(new BodyException(response.returncode, clientMessage));
    }

    /**
     * A pattern for the first step process response with condition params
     *
     * @param response
     * @param <R>
     * @return response or an error Observable with common exception
     */

    public static <R extends BaseResponse> Observable<R> handleResponseWithCondition(R response, Callable<Boolean> successCondition) throws Exception {
        if (response == null) {
            return Observable.error(HttpEmptyResponseException::new);
        }
        if (successCondition.call()) {
            return Observable.just(response);
        }
        String message = response.message;
        String clientMessage = TextUtils.isEmpty(message) ? Constants.EMPTY : message;
        return Observable.error(new BodyException(Constants.ServerCode.FAILED, clientMessage));
    }
}

