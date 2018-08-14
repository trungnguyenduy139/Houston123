package com.trungnguyen.android.houston123.bus;


import android.support.annotation.NonNull;

import io.reactivex.observers.DisposableObserver;

/**
 */
public abstract class RxBusSubscriber<T> extends DisposableObserver<T> {

    @Override
    public void onNext(T t) {
        try {
            onEvent(t);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onComplete() {
    }

    @Override
    public void onError(@NonNull Throwable e) {
        e.printStackTrace();
    }

    protected abstract void onEvent(T t);
}
