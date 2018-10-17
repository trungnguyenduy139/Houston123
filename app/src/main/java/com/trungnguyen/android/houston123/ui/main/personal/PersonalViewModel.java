package com.trungnguyen.android.houston123.ui.main.personal;

import com.trungnguyen.android.houston123.anotation.OnClick;
import com.trungnguyen.android.houston123.base.BaseViewModel;
import com.trungnguyen.android.houston123.repository.login.AuthenticateRepository;
import com.trungnguyen.android.houston123.repository.login.AuthenticateStore;
import com.trungnguyen.android.houston123.rx.SchedulerHelper;
import com.trungnguyen.android.houston123.util.Constants;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import timber.log.Timber;

/**
 * Created by trungnd4 on 19/08/2018.
 */
public class PersonalViewModel extends BaseViewModel<IPersonalView> {

    private AuthenticateStore.Repository mAuthRepository;

    @Inject
    public PersonalViewModel(AuthenticateRepository authRepository) {
        super();
        this.mAuthRepository = authRepository;
    }

    @OnClick
    public void onUserLogout() {
        Disposable subscription = mAuthRepository.callLogoutApi()
                .compose(SchedulerHelper.applySchedulersWithLoadingPattern(this::showLoading, this::hideLoading))
                .doOnNext(authenticateResponse -> mAuthRepository.putAuthInfoLocal(false, Constants.EMPTY))
                .subscribe(authenticateResponse -> {
                    if (mView != null) {
                        mView.navigateToLoginScreen();
                    }
                }, throwable -> Timber.d("Failed to Logout [%s]", throwable.getMessage()));
        mSubscription.add(subscription);
    }
}
