package com.trungnguyen.android.houston123.ui.login;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.trungnguyen.android.houston123.anotation.OnClick;
import com.trungnguyen.android.houston123.base.BaseViewModel;
import com.trungnguyen.android.houston123.repository.login.AuthenticateStore;
import com.trungnguyen.android.houston123.rx.SchedulerHelper;
import com.trungnguyen.android.houston123.util.AppUtils;
import com.trungnguyen.android.houston123.util.BundleConstants;
import com.trungnguyen.android.houston123.util.BundleBuilder;
import com.trungnguyen.android.houston123.util.Navigator;
import com.trungnguyen.android.houston123.util.ServerCode;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import timber.log.Timber;

public class LoginViewModel extends BaseViewModel<ILoginView> {

    private Context mContext;
    public LoginModel mLoginModel;
    @Inject
    Navigator mNavigator;

    private boolean mLoginState = false;

    @Inject
    AuthenticateStore.RequestService mAuthRequestService;

    @Inject
    AuthenticateStore.LocalStorage mAuthRepository;

    @NonNull
    private MutableLiveData isLoggedIn = new MutableLiveData<Boolean>();

    public LoginViewModel(Context context) {

        super(context);
        isLoggedIn.setValue(mLoginState);
        mContext = context;
        mLoginModel = new LoginModel();
        getDataManagerComponent().inject(this);
    }

    @NonNull
    public MutableLiveData<Boolean> getIsLoggedIn() {
        Disposable disposable = mAuthRepository.getLoginStatus()
                .compose(SchedulerHelper.applySchedulers())
                .subscribe(isLoggedIn::setValue, Timber::d);
        mSubscription.add(disposable);
        return isLoggedIn;
    }

    @OnClick
    public void onLoginButtonClick() {

        if (!AppUtils.isNetworkAvailable(mContext)) {
            mView.showNetworkDialog();
        }

        String userName = mLoginModel.getUserName();
        String password = mLoginModel.getPassword();

        Disposable disposable = mAuthRequestService.loginService(userName, password)
                .compose(SchedulerHelper.applySchedulers())
                .flatMap(authenticateResponse -> {
                    boolean isSuccess = authenticateResponse.status.authResponseCode == ServerCode.SUCCESSFUL;
                    return mAuthRepository.setLoginState(isSuccess);
                })
                .subscribe(aBoolean -> {
                    mLoginState = aBoolean;
                    if (aBoolean) {
                        isLoggedIn.setValue(true);
                        Bundle bundle = new BundleBuilder()
                                .putValue(BundleConstants.USER_NAME, mLoginModel.getPassword())
                                .putValue(BundleConstants.PASSWORD, mLoginModel.getPassword())
                                .build();

                        mNavigator.startMainActivity(mContext, bundle);

                    }
                    if (mView != null) {
                        mView.onAuthFinish(mLoginState);
                    }
                }, Timber::d);

        mSubscription.add(disposable);
    }


    @OnClick
    public void onRegisterClick() {
        Toast.makeText(mContext, "onRegisterClick", Toast.LENGTH_SHORT).show();
    }

    public void startMainActivity() {
        mNavigator.startMainActivity(mContext, new Bundle());
    }
}
