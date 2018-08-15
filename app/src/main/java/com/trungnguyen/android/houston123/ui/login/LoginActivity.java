package com.trungnguyen.android.houston123.ui.login;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.trungnguyen.android.houston123.BR;
import com.trungnguyen.android.houston123.R;
import com.trungnguyen.android.houston123.base.BaseActivity;
import com.trungnguyen.android.houston123.databinding.ActivityLoginBinding;
import com.trungnguyen.android.houston123.ui.userdetail.IDetailUserView;


public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> implements IDetailUserView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        viewModel.getIsLoggedIn().observe(this, aBoolean -> {
//            if (aBoolean == null) {
//                return;
//            }
//            if (aBoolean) {
//                viewModel.startMainActivity();
//                finish();
//            }
//        });

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
}
