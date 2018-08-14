package com.trungnguyen.android.houston123.ui.login;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

public class LoginViewModel extends BaseViewModel<ILoginView> {

    private Context mContext;
    public LoginModel mLoginModel;
    @Inject
    Navigator mNavigator;

    @NonNull
    private MutableLiveData isLoggedIn = new MutableLiveData<Boolean>();

    public LoginViewModel(Context context) {

        super(context);
        isLoggedIn.setValue(true);
        mContext = context;
        mLoginModel = new LoginModel();
        getDataManagerComponent().inject(this);
    }

    @NonNull
    public MutableLiveData<Boolean> getIsLoggedIn() {
        return isLoggedIn;
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
                .putValue(BundleConstants.USER_NAME, mLoginModel.getPassword())
                .putValue(BundleConstants.PASSWORD, mLoginModel.getPassword())
                .build();

        mNavigator.startMainActivity(mContext, bundle);
    }


    @OnClick
    public void onRegisterClick() {
        Toast.makeText(mContext, "onRegisterClick", Toast.LENGTH_SHORT).show();
    }

    public void startMainActivity() {
        mNavigator.startMainActivity(mContext, new Bundle());
    }
}
