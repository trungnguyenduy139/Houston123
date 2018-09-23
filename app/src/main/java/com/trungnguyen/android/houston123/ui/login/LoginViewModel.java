package com.trungnguyen.android.houston123.ui.login;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.trungnguyen.android.houston123.anotation.OnClick;
import com.trungnguyen.android.houston123.base.BaseViewModel;
import com.trungnguyen.android.houston123.repository.login.AuthenticateStore;
import com.trungnguyen.android.houston123.rx.DefaultSubscriber;
import com.trungnguyen.android.houston123.rx.SchedulerHelper;
import com.trungnguyen.android.houston123.util.AppUtils;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class LoginViewModel extends BaseViewModel<ILoginView> {

    private Context mContext;
    public LoginModel mLoginModel;

    private boolean mLoginState = false;

    private AuthenticateStore.RequestService mAuthRequestService;

    private AuthenticateStore.LocalStorage mAuthLocalStorage;

    @NonNull
    private MutableLiveData isLoggedIn = new MutableLiveData<Boolean>();

    @Inject
    public LoginViewModel(Context context,
                          AuthenticateStore.LocalStorage localStorage,
                          AuthenticateStore.RequestService requestService) {

        super(context);
        isLoggedIn.setValue(mLoginState);
        this.mContext = context;
        this.mAuthLocalStorage = localStorage;
        this.mAuthRequestService = requestService;
        mLoginModel = new LoginModel();
    }

    @NonNull
    public MutableLiveData<Boolean> getIsLoggedIn() {
        Disposable disposable = mAuthLocalStorage.getLoginStatus()
                .compose(SchedulerHelper.applySchedulers())
                .subscribe(isLoggedIn::setValue, Timber::d);
        mSubscription.add(disposable);
        return isLoggedIn;
    }

    @OnClick
    public void onLoginButtonClick() {
        if (!AppUtils.isNetworkAvailable(mContext) && mView != null) {
            mView.showNetworkDialog();
        }
        String userName = mLoginModel.getUserName();
        String password = mLoginModel.getPassword();

        Disposable disposable = mAuthRequestService.loginService(userName, password)
                .compose(SchedulerHelper.applySchedulers())
                .doOnSubscribe(disposable1 -> {
                    if (mView != null) {
                        mView.showLoading();
                    }
                })
                .doOnTerminate(() -> {
                    if (mView != null) {
                        mView.hideLoading();
                    }
                })
                .subscribe(authenticateResponse -> {
                    if (mView != null) {
                        mView.onAuthSuccess(userName);
                    }
                }, throwable -> {
                    if (mView != null) {
                        mView.onAuthFailed();
                    }
                });

        mSubscription.add(disposable);
    }


    @OnClick
    public void onRegisterClick() {
        Toast.makeText(mContext, "onRegisterClick", Toast.LENGTH_SHORT).show();
    }

    public void putLoginStateToLocal(boolean loginState) {
        Disposable disposable = mAuthLocalStorage.setLoginState(loginState)
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DefaultSubscriber<>());
        mSubscription.add(disposable);
    }
}
