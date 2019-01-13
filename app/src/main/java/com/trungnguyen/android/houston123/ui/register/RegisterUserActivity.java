package com.trungnguyen.android.houston123.ui.register;

import android.os.Bundle;

import com.trungnguyen.android.houston123.BR;
import com.trungnguyen.android.houston123.R;
import com.trungnguyen.android.houston123.base.BaseActivity;
import com.trungnguyen.android.houston123.databinding.ActivityRegisterUserBinding;

public class RegisterUserActivity extends BaseActivity<ActivityRegisterUserBinding, RegisterUserViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_register_user;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void initParam() {
        getDataManagerComponent().inject(this);
    }
}
