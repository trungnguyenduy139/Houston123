package com.trungnguyen.android.houston123.ui.updateaccount;

import android.content.Context;

import com.trungnguyen.android.houston123.anotation.OnClick;
import com.trungnguyen.android.houston123.base.BaseViewModel;
import com.trungnguyen.android.houston123.data.LoginInfoModel;
import com.trungnguyen.android.houston123.repository.login.AuthenticateStore;
import com.trungnguyen.android.houston123.rx.SchedulerHelper;
import com.trungnguyen.android.houston123.ui.userdetail.ItemDetailModel;

import org.jetbrains.annotations.Nullable;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import timber.log.Timber;

/**
 * Created by trungnd4 on 13/12/2018.
 */
public class UpdateAccountViewModel extends BaseViewModel<IUpdateAccountView> {

    private AuthenticateStore.Repository mAuthenticateRepository;

    @Inject
    public UpdateAccountViewModel(Context context, AuthenticateStore.Repository repository) {
        super(context);
        this.mAuthenticateRepository = repository;
    }


    @OnClick
    public void onUpdateAccount() {
        if (mView != null) {
            mView.updateAccountLogin();
        }
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

    public void startUpdateAccountFlow(@Nullable LoginInfoModel loginModel) {
        if (loginModel == null) {
            return;
        }
        Disposable subscription = mAuthenticateRepository.callApiUpdateAccountInfo(loginModel.name,
                loginModel.cmnd,
                loginModel.phone,
                loginModel.email,
                loginModel.address)
                .compose(SchedulerHelper.applySchedulersLoadingAction(this::showLoading, this::hideLoading))
                .subscribe(response -> {
                    if (mView != null) {
                        mView.updateSuccess(response.message);
                    }
                }, Timber::d);

        mSubscription.add(subscription);
    }
}
