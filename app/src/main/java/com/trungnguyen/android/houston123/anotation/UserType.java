package com.trungnguyen.android.houston123.anotation;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.trungnguyen.android.houston123.anotation.UserType.LECTURER;
import static com.trungnguyen.android.houston123.anotation.UserType.MANAGER;
import static com.trungnguyen.android.houston123.anotation.UserType.STUDENT;

/**
 * Created by trungnd4 on 08/10/2018.
 */
@Retention(RetentionPolicy.SOURCE)
@IntDef({
        LECTURER, MANAGER, STUDENT
})
public @interface UserType {

    int LECTURER = 0;
    int MANAGER = 1;
    int STUDENT = 2;
}
