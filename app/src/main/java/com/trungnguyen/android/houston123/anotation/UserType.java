package com.trungnguyen.android.houston123.anotation;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.trungnguyen.android.houston123.anotation.UserType.CLAZZ;
import static com.trungnguyen.android.houston123.anotation.UserType.DETAIL_CLAZZ;
import static com.trungnguyen.android.houston123.anotation.UserType.LECTURER;
import static com.trungnguyen.android.houston123.anotation.UserType.MANAGER;
import static com.trungnguyen.android.houston123.anotation.UserType.STUDENT;
import static com.trungnguyen.android.houston123.anotation.UserType.SUBJECT;

/**
 * Created by trungnd4 on 08/10/2018.
 */
@Retention(RetentionPolicy.SOURCE)
@IntDef({
        LECTURER, MANAGER, STUDENT, CLAZZ, SUBJECT, DETAIL_CLAZZ
})
public @interface UserType {

    int LECTURER = 0;
    int MANAGER = 1;
    int STUDENT = 2;
    int CLAZZ = 3;
    int SUBJECT = 4;
    int DETAIL_CLAZZ = 5;
}
