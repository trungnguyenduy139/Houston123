package com.trungnguyen.android.houston123.ui.login;

import android.app.AlertDialog;
import android.os.Bundle;

import com.trungnguyen.android.houston123.BR;
import com.trungnguyen.android.houston123.R;
import com.trungnguyen.android.houston123.anotation.ToastType;
import com.trungnguyen.android.houston123.base.BaseActivity;
import com.trungnguyen.android.houston123.databinding.ActivityLoginBinding;
import com.trungnguyen.android.houston123.util.BundleBuilder;
import com.trungnguyen.android.houston123.util.BundleConstants;
import com.trungnguyen.android.houston123.util.Navigator;
import com.trungnguyen.android.houston123.widget.ToastCustom;

import javax.inject.Inject;


public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> implements ILoginView {

    @Inject
    Navigator mNavigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel.getIsLoggedIn().observe(this, this::handleState);
    }


    public void handleState(boolean isLoggedIn) {
        if (isLoggedIn) {
            mNavigator.startMainActivity(this, new Bundle());
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


    @Override
    public void initParam() {
        getDataManagerComponent().inject(this);
    }

    @Override
    public void onAuthSuccess(String accessToken) {
        String message = getString(R.string.login_success);
        viewModel.putLoginStateToLocal(true);
        ToastCustom.makeText(this, message, ToastCustom.LENGTH_SHORT, ToastType.TYPE_ERROR);
        Bundle bundle = new BundleBuilder()
                .putValue(BundleConstants.USER_NAME, accessToken)
                .build();
        mNavigator.startMainActivity(this, bundle);
    }

    @Override
    public void onAuthFailed() {
        viewModel.putLoginStateToLocal(false);
        String message = getString(R.string.login_failed);
        ToastCustom.makeText(this, message, ToastCustom.LENGTH_SHORT, ToastType.TYPE_ERROR);
    }

    @Override
    public void showNetworkDialog() {
        String msg = getString(R.string.network_message);
        AlertDialog alertDialog = new AlertDialog.Builder(this).setMessage(msg).create();
        alertDialog.show();
    }

    @Override
    public void showLoading() {
        showLoadingDialog();
    }

    @Override
    public void hideLoading() {
       hideLoadingDialog();
    }
}
