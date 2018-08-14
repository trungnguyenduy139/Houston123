package com.trungnguyen.android.houston123.util;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.trungnguyen.android.houston123.anotation.UserListType;
import com.trungnguyen.android.houston123.ui.listuser.UserListActivity;
import com.trungnguyen.android.houston123.ui.login.LoginActivity;
import com.trungnguyen.android.houston123.ui.main.MainActivity;
import com.trungnguyen.android.houston123.ui.userdetail.lecturer.LecturerActivity;

import javax.inject.Inject;

public final class Navigator {

    @Inject
    public Navigator() {
    }

    public void startLoginActivity(@NonNull Context context) {
        Intent intent = getIntent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    public void startDetailActivity(@NonNull Context context, @NonNull Bundle bundle, int userType) {
        try {
            Intent intent = getIntent(context, getDetailClazz(userType));
            intent.putExtras(bundle);
            context.startActivity(intent);
        } catch (IllegalArgumentException e) {
            AppLogger.w("Navigator startDetailActivity() [%s]", e.getMessage());
        }
    }

    private Class<?> getDetailClazz(int userType) {
        Class<?> clazz;

        switch (userType) {
            case UserListType.LECTURER:
                clazz = LecturerActivity.class;
                break;
            case UserListType.MANAGER:
                // update for manager type
                clazz = LecturerActivity.class;
                break;
            case UserListType.STUDENT:
                // update for student type
                clazz = LecturerActivity.class;
                break;
            default:
                throw new IllegalArgumentException();
        }
        return clazz;
    }

    public void startMainActivity(@NonNull Context context, @NonNull Bundle bundle) {
        Intent intent = getIntent(context, MainActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    public void startUserListActivity(@NonNull Context context, @NonNull Bundle bundle) {
        Intent intent = getIntent(context, UserListActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @NonNull
    private Intent getIntent(Context context, Class<?> clazz) {
        return new Intent(context, clazz);
    }
}
