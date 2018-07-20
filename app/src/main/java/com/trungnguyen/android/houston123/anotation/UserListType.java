package com.trungnguyen.android.houston123.anotation;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.trungnguyen.android.houston123.anotation.UserListType.LECTURER;
import static com.trungnguyen.android.houston123.anotation.UserListType.MANAGER;
import static com.trungnguyen.android.houston123.anotation.UserListType.STUDENT;


/**
 * Created by trungnd4 on 20/07/2018.
 */
@Retention(RetentionPolicy.SOURCE)
@IntDef({
        STUDENT, LECTURER, MANAGER
})
public @interface UserListType {

    int STUDENT = 0;
    int LECTURER = 1;
    int MANAGER = 2;
}
