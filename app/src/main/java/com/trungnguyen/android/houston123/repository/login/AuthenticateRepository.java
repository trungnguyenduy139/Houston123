package com.trungnguyen.android.houston123.repository.login;


import com.trungnguyen.android.houston123.data.AuthenticateResponse;
import com.trungnguyen.android.houston123.rx.ObservableRetryPattern;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by trungnd4 on 23/09/2018.
 */
public class AuthenticateRepository implements AuthenticateStore.Repository {

    private AuthenticateStore.RequestService mRequestService;

    private AuthenticateStore.LocalStorage mLocalStorage;

    private AuthenticateResponse DEFAULT_AUTHENTICATE_RESPONSE = new AuthenticateResponse();

    @Inject
    public AuthenticateRepository(AuthenticateStore.RequestService requestService,
                                  AuthenticateStore.LocalStorage localStorage) {
        this.mRequestService = requestService;
        this.mLocalStorage = localStorage;
    }

    @Override
    public Observable<AuthenticateResponse> callLoginApi(String userName, String password) {
        return mRequestService.loginService(userName, password)
                .compose(ObservableRetryPattern.transformObservable(DEFAULT_AUTHENTICATE_RESPONSE))
                .flatMap(authenticateResponse -> {
                    if (authenticateResponse == null) {
                        return Observable.just(DEFAULT_AUTHENTICATE_RESPONSE);
                    }
                    return Observable.just(authenticateResponse);
                });
    }

    @Override
    public Observable<Boolean> getLoginState() {
        return mLocalStorage.getLoginStatus();
    }

    @Override
    public Observable<Boolean> setLoginState(boolean state) {
        return mLocalStorage.setLoginState(state);
    }
}
