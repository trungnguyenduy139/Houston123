package com.trungnguyen.android.houston123.repository.login;


import android.text.TextUtils;

import com.trungnguyen.android.houston123.data.AuthenticateResponse;
import com.trungnguyen.android.houston123.exception.BodyException;
import com.trungnguyen.android.houston123.exception.HttpEmptyResponseException;
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
    public Observable<AuthenticateResponse> callLoginApi(String userName, String password) {
        return mRequestService.loginService(userName, password)
                .compose(ObservablePattern.transformObservable(DEFAULT_AUTHENTICATE_RESPONSE))
                .doOnNext(authenticateResponse -> Timber.d("[Auth] Authenticate api response %s", authenticateResponse.toString()))
                .doOnError(throwable -> Timber.d("[Auth] Authenticate failed with %s", throwable.getMessage()))
                .flatMap(authenticateResponse -> {
                    if (authenticateResponse == null) {
                        return Observable.just(DEFAULT_AUTHENTICATE_RESPONSE);
                    }
                    return Observable.just(authenticateResponse);
                });
    }

    @Override
    public Observable<AuthenticateResponse> callLogoutApi() {
        return mLocalStorage.getSafeAccessToken()
                .doOnNext(token -> Timber.d("Get access token from Local [%s]", token))
                .map(token -> Constants.TOKEN_PREFIX + Constants.SPACE + token)
                .flatMap(token -> mRequestService.logoutService(token)
                        .compose(ObservablePattern.transformObservable(DEFAULT_AUTHENTICATE_RESPONSE))
                        .flatMap(authenticateResponse -> {
                            if (authenticateResponse == null) {
                                return Observable.error(new HttpEmptyResponseException());
                            }
                            if (!TextUtils.isEmpty(authenticateResponse.message)) {
                                return Observable.just(authenticateResponse);
                            }
                            return Observable.error(new BodyException(Constants.ServerCode.FAILED, Constants.EMPTY));
                        }));
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
