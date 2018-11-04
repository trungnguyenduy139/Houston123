package com.trungnguyen.android.houston123.repository.login;


import android.text.TextUtils;

import com.trungnguyen.android.houston123.data.AccountInfoResponse;
import com.trungnguyen.android.houston123.data.AuthenticateResponse;
import com.trungnguyen.android.houston123.rx.ObservableHelper;
import com.trungnguyen.android.houston123.rx.ObservablePattern;
import com.trungnguyen.android.houston123.rx.Optional;
import com.trungnguyen.android.houston123.util.Constants;

import javax.inject.Inject;

import io.reactivex.Observable;
import timber.log.Timber;

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
    public Observable<AccountInfoResponse> callLoginApi(String userName, String password) {
        return mRequestService.loginService(userName, password)
                .compose(ObservablePattern.transformObservable(DEFAULT_AUTHENTICATE_RESPONSE))
                .flatMap(authenticateResponse -> {
                    if (authenticateResponse == null) {
                        return Observable.just(DEFAULT_AUTHENTICATE_RESPONSE.userToken);
                    }
                    return Observable.just(authenticateResponse.userToken);
                })
                .doOnNext(token -> {
                    Timber.d("[Auth] Authenticate api response %s", token);
                    putAuthInfoLocal(true, token);
                })
                .doOnError(throwable -> {
                    Timber.d("[Auth] Authenticate failed with %s", throwable.getMessage());
                    putAuthInfoLocal(false, Constants.EMPTY);
                })
                .map(userToken -> Constants.TOKEN_PREFIX + Constants.SPACE + userToken)
                .flatMap(this::callAccountInformationApi);
    }

    @Override
    public Observable<AuthenticateResponse> callLogoutApi() {
        return mLocalStorage.getSafeAccessToken()
                .doOnNext(token -> Timber.d("Get access token from Local [%s]", token))
                .map(token -> Constants.TOKEN_PREFIX + Constants.SPACE + token)
                .flatMap(token -> mRequestService.logoutService(token)
                        .compose(ObservablePattern.transformObservable(DEFAULT_AUTHENTICATE_RESPONSE))
                        .flatMap(authenticateResponse -> ObservablePattern
                                .handleResponseWithCondition(authenticateResponse, () -> TextUtils.isEmpty(authenticateResponse.message))));
    }

    @Override
    public Observable<AccountInfoResponse> callAccountInformationApi(String token) {
        return mRequestService.getAccountInfo(token)
                .doOnError(throwable -> {
                    Timber.d("[Auth] Authenticate failed with %s", throwable.getMessage());
                    putAuthInfoLocal(false, Constants.EMPTY);
                })
                .flatMap(accountInfoResponse -> ObservablePattern
                        .handleResponseWithCondition(accountInfoResponse, () -> TextUtils.isEmpty(accountInfoResponse.permission)));
    }

    @Override
    public Observable<Boolean> getLoginState() {
        return ObservableHelper.makeObservableOptional(() -> mLocalStorage.getLoginStatus()).map(Optional::get);
    }

    @Override
    public Observable<Boolean> putAuthInfoLocal(boolean state, final String accessToken) {
        return mLocalStorage.setLoginState(state)
                .flatMap(aBoolean -> {
                    if (aBoolean && !TextUtils.isEmpty(accessToken)) {
                        mLocalStorage.putSafeAccessToken(accessToken);
                    }
                    return Observable.just(aBoolean);
                });
    }
}
