package com.trungnguyen.android.houston123.ui.login;

import android.content.Context;
import android.widget.Toast;

import com.trungnguyen.android.houston123.anotation.OnClick;
import com.trungnguyen.android.houston123.base.BaseViewModel;

public class LoginViewModel extends BaseViewModel {
    private Context mContext;

    public LoginViewModel(Context context) {

        super(context);
        mContext = context;
    }

    @OnClick
    public void onLoginButtonClick() {
        Toast.makeText(mContext, "Click click boom", Toast.LENGTH_SHORT).show();
    }


    @OnClick
    public void onRegisterClick() {
        Toast.makeText(mContext, "onRegisterClick", Toast.LENGTH_SHORT).show();
    }
}
