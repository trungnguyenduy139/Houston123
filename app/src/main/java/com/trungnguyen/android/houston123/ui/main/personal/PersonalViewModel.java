package com.trungnguyen.android.houston123.ui.main.personal;

import android.content.Context;

import com.trungnguyen.android.houston123.anotation.OnClick;
import com.trungnguyen.android.houston123.base.BaseViewModel;
import com.trungnguyen.android.houston123.data.LoginInfoModel;
import com.trungnguyen.android.houston123.repository.login.AuthenticateRepository;
import com.trungnguyen.android.houston123.repository.login.AuthenticateStore;
import com.trungnguyen.android.houston123.rx.SchedulerHelper;
import com.trungnguyen.android.houston123.ui.main.ChangePasswordActivity;
import com.trungnguyen.android.houston123.ui.updateaccount.UpdateAccountActivity;
import com.trungnguyen.android.houston123.ui.userdetail.ItemDetailModel;
import com.trungnguyen.android.houston123.util.Constants;
import com.trungnguyen.android.houston123.util.Navigator;

import java.util.List;

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
        if (mView == null) {
            return;
        }
        mView.askBeforeLogout();
    }

    public void startLogoutProcess() {
        Disposable subscription = mAuthRepository.callLogoutApi()
                .doOnNext(authenticateResponse -> mAuthRepository.putAuthInfoLocal(false, Constants.EMPTY))
                .compose(SchedulerHelper.applySchedulersLoadingAction(this::showLoading, this::hideLoading))
                .subscribe(authenticateResponse -> {
                    if (mView != null) {
                        mView.successToLogout();
                    }
                }, throwable -> Timber.d("Failed to Logout [%s]", throwable.getMessage()));
        mSubscription.add(subscription);
    }

    @OnClick
    public void onUpdateAccount() {
        if (context == null) {
            return;
        }
        mNavigator.starFoResult(mView.getHostView(), UpdateAccountActivity.class);
    }

    @OnClick
    public void onUserChangePassword() {
        mNavigator.startActivity(context, ChangePasswordActivity.class);
    }

    public void loadUserInfoResource(LoginInfoModel model) {
        Disposable subscription = model
                .convert()
                .compose(SchedulerHelper.applySchedulers())
                .subscribe(items -> {
                    if (mView != null) {
                        mView.onLoadResourceCompleted((List<ItemDetailModel>) items);
                    }
                }, throwable -> Timber.d("[DetailUser] Failed to load model resource %s", throwable.toString()));

        mSubscription.add(subscription);
    }
}
