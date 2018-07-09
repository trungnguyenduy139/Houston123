package com.trungnguyen.android.houston123.ui.login;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.trungnguyen.android.houston123.anotation.OnClick;
import com.trungnguyen.android.houston123.anotation.ToastType;
import com.trungnguyen.android.houston123.base.BaseViewModel;
import com.trungnguyen.android.houston123.injection.Injector;
import com.trungnguyen.android.houston123.util.AppUtils;
import com.trungnguyen.android.houston123.util.BundleConstants;
import com.trungnguyen.android.houston123.util.BundleBuilder;
import com.trungnguyen.android.houston123.util.Constants;
import com.trungnguyen.android.houston123.util.Navigator;
import com.trungnguyen.android.houston123.widget.ToastCustom;

import javax.inject.Inject;

public class LoginViewModel extends BaseViewModel {

    private Context mContext;
    public LoginModel mLoginModel;
    @Inject
    Navigator mNavigator;

    public LoginViewModel(Context context) {

        super(context);
        mContext = context;
        mLoginModel = new LoginModel();
        Injector.getInstance().getDataManagerComponent().inject(this);

    }

    @OnClick
    public void onLoginButtonClick() {
        String userName = mLoginModel.getUserName();
        String password = mLoginModel.getPassword();
        if (!AppUtils.isAuthValid(userName, password)) {
            ToastCustom.makeText(mContext, Constants.SOMETHING_WRONG, ToastCustom.LENGTH_SHORT, ToastType.TYPE_ERROR);
            return;
        }

//        Toast.makeText(mContext, mLoginModel.getUserName() + "\n" + mLoginModel.getPassword(), Toast.LENGTH_SHORT).show();

        Bundle bundle = new BundleBuilder()
                .putString(BundleConstants.USER_NAME, mLoginModel.getPassword())
                .putString(BundleConstants.PASSWORD, mLoginModel.getPassword())
                .build();

        mNavigator.startMainActivity(mContext, bundle);
    }


    @OnClick
    public void onRegisterClick() {
        Toast.makeText(mContext, "onRegisterClick", Toast.LENGTH_SHORT).show();
    }
}
