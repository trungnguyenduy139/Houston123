package com.trungnguyen.android.houston123.ui.main;

import android.os.Bundle;

import com.trungnguyen.android.houston123.BR;
import com.trungnguyen.android.houston123.R;
import com.trungnguyen.android.houston123.base.BaseActivity;
import com.trungnguyen.android.houston123.databinding.ActivityChangePasswordBinding;
import com.trungnguyen.android.houston123.widget.ToastCustom;
import com.trungnguyen.android.houston123.widget.sweetalert.SweetAlertDialog;

/**
 * Created by trungnd4 on 05/11/2018.
 */
public class ChangePasswordActivity extends BaseActivity<ActivityChangePasswordBinding, ChangePasswordViewModel> implements IChangePassword {

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_change_password;
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

    @Override
    public void onVerifyPasswordFailed() {
        ToastCustom.makeTopToastText(this, getString(R.string.invalid_confirm_pass));
    }

    @Override
    public void onChangePasswordSuccess(String message) {
        new SweetAlertDialog(this)
                .setContentText(message)
                .show();
    }

    @Override
    public void onChangePasswordFailed(String message) {
        showErrorDialog(message);
    }
}
