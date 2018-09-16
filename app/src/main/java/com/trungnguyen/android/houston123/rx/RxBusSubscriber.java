package com.trungnguyen.android.houston123.rx;

/**
 * Created by trungnd4
 */

public abstract class RxBusSubscriber<T> extends DefaultSubscriber<T> {

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
