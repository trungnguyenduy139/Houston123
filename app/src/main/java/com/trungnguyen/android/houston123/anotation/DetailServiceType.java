package com.trungnguyen.android.houston123.anotation;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.trungnguyen.android.houston123.anotation.InfinityAdapterType.TYPE_LOADING;
import static com.trungnguyen.android.houston123.anotation.InfinityAdapterType.TYPE_NORMAL;

/**
 * Created by trungnd4 on 13/10/2018.
 */

@Retention(RetentionPolicy.SOURCE)
@IntDef({
        TYPE_NORMAL, TYPE_LOADING
})

public @interface DetailServiceType {

    int START_UPDATE = 100;
    int DO_UPDATE = 101;
    int START_NEW = 102;
}
