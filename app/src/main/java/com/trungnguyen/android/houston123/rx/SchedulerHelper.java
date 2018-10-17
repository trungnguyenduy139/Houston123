package com.trungnguyen.android.houston123.rx;

import io.reactivex.ObservableTransformer;
import io.reactivex.SingleTransformer;
import io.reactivex.functions.Cancellable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by trungnd4 on 12/08/2018.
 */
public final class SchedulerHelper {
    public static <T> ObservableTransformer<T, T> applySchedulers() {
        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public static <T> ObservableTransformer<T, T> applySchedulersWithLoadingPattern(Cancellable subscribeAction, Cancellable terminateAction) {
        return observable -> observable.subscribeOn(Schedulers.io())
                .doOnSubscribe(dispose -> subscribeAction.cancel())
                .doOnTerminate(terminateAction::cancel)
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> SingleTransformer<T, T> applySingleSchedulers() {
        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> SingleTransformer<T, T> appyBackgroundSchedulers() {
        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread());
    }
}
