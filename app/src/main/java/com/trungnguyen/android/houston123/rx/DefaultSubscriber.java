package com.trungnguyen.android.houston123.rx;

/**
 * Created by trungnd4 on 19/08/2018.
 */

import io.reactivex.observers.DisposableObserver;

public class DefaultSubscriber<T> extends DisposableObserver<T> {

    @Override
    public void onComplete() {
        // no-op by default.
    }

    @Override
    public void onError(Throwable e) {
        // no-op by default.
    }

    @Override
    public void onNext(T t) {
        // no-op by default.
    }

}

