package com.trungnguyen.android.houston123.rx;

import io.reactivex.observers.DefaultObserver;

/**
 */
public abstract class RxBusSubscriber<T> extends DefaultObserver<T> {

    @Override
    public void onNext(T t) {
        try {
            onEvent(t);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected abstract void onEvent(T t);
}
