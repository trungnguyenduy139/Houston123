package com.trungnguyen.android.houston123.rx;

import android.support.annotation.NonNull;

import com.trungnguyen.android.houston123.util.AppLogger;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;

/**
 * Created by trungnd4 on 12/08/2018.
 *
 */
public final class ObservableHelper {

    public static <T> Observable<Optional<T>> makeObservableOptional(@NonNull final Callable<T> func) {
        return Observable.create(emitter -> {
            try {
                Optional<T> ret = Optional.ofNullable(func.call());

                if (!emitter.isDisposed()) {
                    emitter.onNext(ret);
                    emitter.onComplete();
                }

            } catch (Exception ex) {
                AppLogger.w(ex, "Exception while calling makeObservableOptional");
                try {
                    if (!emitter.isDisposed()) {
                        emitter.onError(ex);
                    }
                } catch (Exception ex2) {
                    AppLogger.w(ex2, "Exception while calling disposing emitter in makeObservableOptional");
                }
            }
        });
    }

    public static <T> Observable<T> makeObservableStrict(@NonNull final Callable<T> func) {
        return Observable.create(emitter -> {
            try {
                T ret = func.call();

                if (!emitter.isDisposed()) {
                    emitter.onNext(ret);
                    emitter.onComplete();
                }

            } catch (Exception ex) {
                AppLogger.w(ex, "Exception while calling makeObservableStrict");
                try {
                    if (!emitter.isDisposed()) {
                        emitter.onError(ex);
                    }
                } catch (Exception ex2) {
                    AppLogger.w(ex2, "Exception while calling disposing emitter in makeObservableStrict");
                }
            }
        });
    }

    /**
     * @return a transformer for emitting the present values of emitted {@link Optional} instances
     */
    public static <T> ObservableTransformer<Optional<T>, T> presentValue() {
        return observable -> observable
                .filter(Optional::isPresent)
                .map(Optional::get);
    }
}
