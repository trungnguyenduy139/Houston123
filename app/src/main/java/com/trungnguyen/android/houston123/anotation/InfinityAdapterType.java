package com.trungnguyen.android.houston123.anotation;

import android.support.annotation.IntDef;
import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.trungnguyen.android.houston123.anotation.InfinityAdapterType.TYPE_LOADING;
import static com.trungnguyen.android.houston123.anotation.InfinityAdapterType.TYPE_NORMAL;


@Retention(RetentionPolicy.SOURCE)
@IntDef({
        TYPE_NORMAL, TYPE_LOADING
})
public @interface InfinityAdapterType {

    int TYPE_NORMAL = 0;
    int TYPE_LOADING = 1;
}
