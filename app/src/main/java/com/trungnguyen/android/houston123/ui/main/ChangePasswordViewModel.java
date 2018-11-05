package com.trungnguyen.android.houston123.ui.main;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import com.trungnguyen.android.houston123.R;
import com.trungnguyen.android.houston123.anotation.OnClick;
import com.trungnguyen.android.houston123.base.BaseViewModel;
import com.trungnguyen.android.houston123.exception.BodyException;
import com.trungnguyen.android.houston123.repository.login.AuthenticateRepository;
import com.trungnguyen.android.houston123.repository.login.AuthenticateStore;
import com.trungnguyen.android.houston123.rx.SchedulerHelper;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * Created by trungnd4 on 05/11/2018.
 */
public class ChangePasswordViewModel extends BaseViewModel<IChangePassword> {

    private AuthenticateStore.Repository mAuthenticateRepository;

    @Inject
    public ChangePasswordViewModel(AuthenticateRepository repository, Context context) {
        super(context);
        this.mAuthenticateRepository = repository;
    }


    @OnClick
    public void onChangePasswordAction(View view, String oldPass, String newPass, String confirmPass) {

        if (!shouldAllowChangePassword(oldPass, newPass, confirmPass) && mView != null) {
            mView.onVerifyPasswordFailed();
            return;
        }

        Disposable subscription = mAuthenticateRepository.callChangePasswordApi(oldPass, newPass, confirmPass)
                .compose(SchedulerHelper.applySchedulers())
                .doOnSubscribe(disposable -> showLoading())
                .doOnTerminate(this::hideLoading)
                .subscribe(response -> {
                    if (mView != null) {
                        mView.onChangePasswordSuccess(response.message);
                    }
                }, this::handleChangePasswordFailed);

        mSubscription.add(subscription);
    }

    private void handleChangePasswordFailed(Throwable throwable) {
        if (context == null) {
            return;
        }
        String message = context.getString(R.string.change_password_failed);
        if (throwable instanceof BodyException) {
            message = throwable.getMessage();
        }
        if (mView != null) {
            mView.onChangePasswordFailed(message);
        }
    }

    private boolean shouldAllowChangePassword(String oldPass, String newPass, String confirm) {
        return TextUtils.equals(newPass, confirm) && !TextUtils.equals(oldPass, newPass);
    }

}
