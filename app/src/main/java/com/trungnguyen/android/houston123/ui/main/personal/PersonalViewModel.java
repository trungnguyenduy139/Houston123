package com.trungnguyen.android.houston123.ui.main.personal;

import com.trungnguyen.android.houston123.anotation.OnClick;
import com.trungnguyen.android.houston123.base.BaseViewModel;
import com.trungnguyen.android.houston123.repository.login.AuthenticateRepository;
import com.trungnguyen.android.houston123.repository.login.AuthenticateStore;
import com.trungnguyen.android.houston123.rx.SchedulerHelper;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
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
        Disposable subscription = mAuthRepository.putAuthInfoLocal(false, "")
                .compose(SchedulerHelper.applySchedulers())
                .subscribe(aBoolean -> {
                    if (mView != null) {
                        mView.navigateToLoginScreen();
                    }
                }, throwable -> Timber.d("Failed to Logout [%s]", throwable.getMessage()));
        mSubscription.add(subscription);
    }
}
