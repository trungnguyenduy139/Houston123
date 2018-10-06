package com.trungnguyen.android.houston123.util;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.trungnguyen.android.houston123.ui.listuser.UserListActivity;
import com.trungnguyen.android.houston123.ui.login.LoginActivity;
import com.trungnguyen.android.houston123.ui.main.MainActivity;
import com.trungnguyen.android.houston123.ui.userdetail.DetailUserActivity;
import com.trungnguyen.android.houston123.ui.userdetail.UpdateDetailUserActivity;

public final class Navigator {

    public void startLoginActivity(@NonNull Context context) {
        Intent intent = getIntent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    public void startDetailActivity(@NonNull Context context, @NonNull Bundle bundle) {
        try {
            Intent intent = getIntent(context, DetailUserActivity.class);
            intent.putExtras(bundle);
            context.startActivity(intent);
        } catch (IllegalArgumentException e) {
            AppLogger.w("Navigator startDetailActivity() [%s]", e.getMessage());
        }
    }

    public void startEditDetailActivity(@NonNull Context context, @NonNull Bundle bundle) {
        try {
            Intent intent = getIntent(context, UpdateDetailUserActivity.class);
            intent.putExtras(bundle);
            context.startActivity(intent);
        } catch (IllegalArgumentException e) {
            AppLogger.w("Navigator startDetailActivity() [%s]", e.getMessage());
        }
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
