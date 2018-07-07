package com.trungnguyen.android.houston123.ui.login;

import android.content.Context;
import android.widget.Toast;

import com.trungnguyen.android.houston123.base.BaseViewModel;

public class LoginViewModel extends BaseViewModel {
    private Context mContext;

    public LoginViewModel(Context context) {

        super(context);
        mContext = context;
    }

    public void onTextClick() {
        Toast.makeText(mContext, "Click click boom", Toast.LENGTH_SHORT).show();
    }
}
