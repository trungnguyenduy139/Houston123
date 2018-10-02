package com.trungnguyen.android.houston123.repository.login;


import com.trungnguyen.android.houston123.data.AuthenticateResponse;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by trungnd4 on 23/09/2018.
 */
public class AuthenticateRepository implements AuthenticateStore.Repository {

    private AuthenticateStore.RequestService mRequestService;

    private AuthenticateStore.LocalStorage mLocalStorage;

    @Inject
    public AuthenticateRepository(AuthenticateStore.RequestService requestService,
                                  AuthenticateStore.LocalStorage localStorage) {
        this.mRequestService = requestService;
        this.mLocalStorage = localStorage;
    }

    @Override
    public Observable<AuthenticateResponse> callLoginApi(String userName, String password) {
        return mRequestService.loginService(userName, password)
                .flatMap(authenticateResponse -> {
                    if (authenticateResponse == null) {
                        return Observable.error(new RuntimeException());
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
