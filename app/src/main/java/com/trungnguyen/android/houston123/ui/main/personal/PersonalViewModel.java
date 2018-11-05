package com.trungnguyen.android.houston123.ui.main.personal;

import android.content.Context;

import com.trungnguyen.android.houston123.anotation.OnClick;
import com.trungnguyen.android.houston123.base.BaseViewModel;
import com.trungnguyen.android.houston123.repository.login.AuthenticateRepository;
import com.trungnguyen.android.houston123.repository.login.AuthenticateStore;
import com.trungnguyen.android.houston123.rx.SchedulerHelper;
import com.trungnguyen.android.houston123.ui.main.ChangePasswordActivity;
import com.trungnguyen.android.houston123.util.Constants;
import com.trungnguyen.android.houston123.util.Navigator;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import timber.log.Timber;

/**
 * Created by trungnd4 on 19/08/2018.
 */
public class PersonalViewModel extends BaseViewModel<IPersonalView> {

    private AuthenticateStore.Repository mAuthRepository;
    private Navigator mNavigator;

    @Inject
    public PersonalViewModel(AuthenticateRepository authRepository,
                             Navigator navigator,
                             Context context) {
        super(context);
        this.mNavigator = navigator;
        this.mAuthRepository = authRepository;
    }

    @OnClick
    public void onUserLogout() {
        Disposable subscription = mAuthRepository.callLogoutApi()
                .compose(SchedulerHelper.applySchedulersLoadingAction(this::showLoading, this::hideLoading))
                .doOnNext(authenticateResponse -> mAuthRepository.putAuthInfoLocal(false, Constants.EMPTY))
                .subscribe(authenticateResponse -> {
                    if (mView != null) {
                        mView.successToLogout();
                    }
                }, throwable -> Timber.d("Failed to Logout [%s]", throwable.getMessage()));
        mSubscription.add(subscription);
    }

    @OnClick
    public void onUserChangePassword() {
        mNavigator.startActivity(context, ChangePasswordActivity.class);
    }
}
