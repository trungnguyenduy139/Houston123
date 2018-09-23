package com.trungnguyen.android.houston123.ui.login;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.trungnguyen.android.houston123.BR;
import com.trungnguyen.android.houston123.R;
import com.trungnguyen.android.houston123.anotation.ToastType;
import com.trungnguyen.android.houston123.base.BaseActivity;
import com.trungnguyen.android.houston123.databinding.ActivityLoginBinding;
import com.trungnguyen.android.houston123.widget.ToastCustom;


public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> implements ILoginView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel.getIsLoggedIn().observe(this, this::handleState);
    }

    public void handleState(boolean isLoggedIn) {
        if (isLoggedIn) {
            viewModel.startMainActivity();
            finish();
        }
    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_login;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @NonNull
    @Override
    public LoginViewModel initViewModel() {
        return new LoginViewModel(this);
    }

    @Override
    public void onAuthFinish(boolean status) {
        String message = status ? getString(R.string.login_success) : getString(R.string.login_failed);
        ToastCustom.makeText(this, message, ToastCustom.LENGTH_SHORT, ToastType.TYPE_ERROR);
    }

    @Override
    public void showNetworkDialog() {
        String msg = getString(R.string.network_message);
        AlertDialog alertDialog = new AlertDialog.Builder(this).setMessage(msg).create();
        alertDialog.show();
    }
}
