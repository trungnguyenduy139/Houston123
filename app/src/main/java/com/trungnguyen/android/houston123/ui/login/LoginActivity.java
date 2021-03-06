package com.trungnguyen.android.houston123.ui.login;

import android.os.Bundle;
import android.text.TextUtils;

import com.trungnguyen.android.houston123.BR;
import com.trungnguyen.android.houston123.R;
import com.trungnguyen.android.houston123.anotation.ToastType;
import com.trungnguyen.android.houston123.base.BaseActivity;
import com.trungnguyen.android.houston123.databinding.ActivityLoginBinding;
import com.trungnguyen.android.houston123.util.Constants;
import com.trungnguyen.android.houston123.util.Navigator;
import com.trungnguyen.android.houston123.widget.ToastCustom;

import javax.inject.Inject;


public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> implements ILoginView {

    @Inject
    Navigator mNavigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel.getLoginStatus();
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
//        viewModel.isLoggedIn().observe(this, this::handleState);
//        viewModel.getLiveUserToken().observe(this, permission -> onAuthSuccess(permission, MainActivity.class));
    }

//    public void handleState(boolean isLoggedIn) {
//        if (isLoggedIn) {
//            mNavigator.startMainActivity(this, new Bundle());
//            finish();
//        }
//    }

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
    public void onAuthSuccess(String permission, Class clz, Bundle bundle) {
        if (TextUtils.isEmpty(permission)) {
            return;
        }
        String message = getString(R.string.login_success) + Constants.SPACE + permission;
        ToastCustom.makeText(this, message, ToastCustom.LENGTH_SHORT, ToastType.TYPE_OK);
        mNavigator.startActivity(this, clz, bundle);
        finish();
    }

    @Override
    public void onAuthFailed() {
        String message = getString(R.string.login_failed);
        ToastCustom.makeText(this, message, ToastCustom.LENGTH_SHORT, ToastType.TYPE_ERROR);
    }

    @Override
    public void showNetworkDialog() {
        showNetworkErrorDialog();
    }
}
