package com.trungnguyen.android.houston123.util;

import android.content.Context;
import android.content.Intent;

import com.trungnguyen.android.houston123.ui.login.LoginActivity;

import javax.inject.Inject;

public final class Navigator {

    @Inject
    public Navigator() {

    }

    public void startLoginActivity(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

}
