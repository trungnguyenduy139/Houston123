package com.trungnguyen.android.houston123.util;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.trungnguyen.android.houston123.ui.login.LoginActivity;
import com.trungnguyen.android.houston123.ui.main.MainActivity;

import javax.inject.Inject;

public final class Navigator {

    @Inject
    public Navigator() {
    }

    public void startLoginActivity(Context context) {
        Intent intent = getIntent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    public void startMainActivity(Context context, Bundle bundle) {
        Intent intent = getIntent(context, MainActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @NonNull
    private Intent getIntent(Context context, Class<?> clazz) {
        return new Intent(context, clazz);
    }
}
