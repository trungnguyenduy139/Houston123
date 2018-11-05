package com.trungnguyen.android.houston123.repository.login;


import android.text.TextUtils;

import com.trungnguyen.android.houston123.data.AuthenticateResponse;
import com.trungnguyen.android.houston123.data.BaseResponse;
import com.trungnguyen.android.houston123.data.LoginInfoResponse;
import com.trungnguyen.android.houston123.exception.HttpEmptyResponseException;
import com.trungnguyen.android.houston123.rx.ObservableHelper;
import com.trungnguyen.android.houston123.rx.ObservablePattern;
import com.trungnguyen.android.houston123.rx.Optional;
import com.trungnguyen.android.houston123.util.AppUtils;
import com.trungnguyen.android.houston123.util.Constants;
import com.trungnguyen.android.houston123.util.Lists;

import java.util.List;

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

    private int DEFAULT_ACCOUNT_INFO_POSITION = 0;

    @Inject
    public AuthenticateRepository(AuthenticateStore.RequestService requestService,
                                  AuthenticateStore.LocalStorage localStorage) {
        this.mRequestService = requestService;
        this.mLocalStorage = localStorage;
    }

    @Override
    public Observable<LoginInfoResponse> callLoginApi(String userName, String password) {
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
                .map(AppUtils::transformToken)
                .flatMap(this::callAccountInformationApi);
    }

    @Override
    public Observable<AuthenticateResponse> callLogoutApi() {
        return mLocalStorage.getSafeAccessToken()
                .doOnNext(token -> Timber.d("Get access token from Local [%s]", token))
                .map(AppUtils::transformToken)
                .flatMap(token -> mRequestService.logoutService(token)
                        .compose(ObservablePattern.transformObservable(DEFAULT_AUTHENTICATE_RESPONSE))
                        .flatMap(ObservablePattern::responseProcessingPattern));
    }

    @Override
    public Observable<LoginInfoResponse> callAccountInformationApi(String token) {
        return mRequestService.getAccountInfo(token)
                .doOnError(throwable -> {
                    Timber.d("[Auth] Authenticate failed with %s", throwable.getMessage());
                    putAuthInfoLocal(false, Constants.EMPTY);
                })
                .flatMap(ObservablePattern::responseProcessingPattern)
                .flatMap(accountInfoResponse -> {
                    List<LoginInfoResponse> data = accountInfoResponse.loginInfo;
                    if (Lists.isEmptyOrNull(data) || data.get(this.DEFAULT_ACCOUNT_INFO_POSITION) == null) {
                        return Observable.error(HttpEmptyResponseException::new);
                    }
                    return Observable.just(data.get(this.DEFAULT_ACCOUNT_INFO_POSITION));
                });
    }

    @Override
    public Observable<BaseResponse> callChangePasswordApi(String passOld, String passNew, String passConfirm) {
        return mLocalStorage.getSafeAccessToken()
                .map(AppUtils::transformToken)
                .flatMap(formattedToken -> mRequestService.changePassword(formattedToken, passOld, passNew, passConfirm))
                .flatMap(ObservablePattern::responseProcessingPattern);
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
