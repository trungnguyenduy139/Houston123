package com.trungnguyen.android.houston123.ui.userdetail;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.trungnguyen.android.houston123.R;
import com.trungnguyen.android.houston123.anotation.DetailServiceType;
import com.trungnguyen.android.houston123.anotation.OnClick;
import com.trungnguyen.android.houston123.anotation.UserType;
import com.trungnguyen.android.houston123.base.BaseListViewModel;
import com.trungnguyen.android.houston123.base.BaseModel;
import com.trungnguyen.android.houston123.repository.updateuser.UpdateUserRepository;
import com.trungnguyen.android.houston123.repository.updateuser.UpdateUserStore;
import com.trungnguyen.android.houston123.repository.userlist.UserListRepository;
import com.trungnguyen.android.houston123.repository.userlist.UserListStore;
import com.trungnguyen.android.houston123.rx.DefaultSubscriber;
import com.trungnguyen.android.houston123.rx.SchedulerHelper;
import com.trungnguyen.android.houston123.util.BundleBuilder;
import com.trungnguyen.android.houston123.util.BundleConstants;
import com.trungnguyen.android.houston123.util.Navigator;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import timber.log.Timber;

/**
 * Created by trungnd4 on 20/07/2018.
 */
public class DetailUserViewModel extends BaseListViewModel<IDetailUserView, UserDetailListener> implements UserDetailListener {

    @Nullable
    public BaseModel mUserModel;

    private Navigator mNavigator;

    private UserListStore.Repository mUserListRepository;

    private UpdateUserStore.Repository mUpdateUserRepository;

    private Context mContext;

    public int mServiceActionCode = DetailServiceType.START_UPDATE;

    public void setLecturerModel(@Nullable BaseModel userModel) {
        if (userModel == null) {
            return;
        }
        mUserModel = userModel;
    }

    public void initDetailList(@NonNull BaseModel baseUserModel) {
        Disposable subscription = baseUserModel
                .convert()
                .compose(SchedulerHelper.applySchedulers())
                .subscribe(items -> {
                    if (mView != null) {
                        mView.updateResourceList((List<ItemDetailModel>) items);
                    }
                }, throwable -> Timber.d("[DetailUser] Failed to load model resource %s", throwable.toString()));

        mSubscription.add(subscription);
    }

    @Inject
    public DetailUserViewModel(Context context, Navigator navigator,
                               UserListRepository repository,
                               UpdateUserRepository updateUserRepository) {
        super(context);
        this.mContext = context;
        this.mNavigator = navigator;
        this.mUserListRepository = repository;
        this.mUpdateUserRepository = updateUserRepository;
    }

    @OnClick
    public void onUpdateClick(int code, BaseModel model) {
        if (mUserModel == null) {
            return;
        }
        switch (code) {
            case DetailServiceType.START_UPDATE:
                Bundle bundle = new BundleBuilder().putValue(BundleConstants.KEY_UPDATE_USER_DETAIL, mUserModel).build();
                mNavigator.startEditDetailActivity(context, bundle);
                break;
            case DetailServiceType.DO_UPDATE:
                if (model == null) {
                    return;
                }
                handleUpdateUser(model);
                break;
            default:
                break;
        }
    }

    public void handleUpdateUser(BaseModel model) {
        Disposable subscription = mUpdateUserRepository.callApiUpdateUser(model)
                .compose(SchedulerHelper.applySchedulersLoadingAction(this::showLoading, this::hideLoading))
                .subscribeWith(new DefaultSubscriber<>());
        mSubscription.add(subscription);
    }

    public void deleteCurrentUser(int code, String userId) {
        Disposable subscription = mUserListRepository.handleRemoveUserFlow(code, userId)
                .compose(SchedulerHelper.applySchedulersLoadingAction(this::showLoading, this::hideLoading))
                .subscribe(baseResponse -> {
                    if (mView != null) {
                        mView.deleteUserSuccess();
                    }
                }, throwable -> {
                    if (mView != null) {
                        mView.deleteUserFailed();
                    }
                });

        mSubscription.add(subscription);
    }

    public void setApply(int apply) {
        mServiceActionCode = apply;
    }

    @NonNull
    @Override
    public UserDetailListener getListener() {
        return this;
    }

    @Override
    public void onDestroy() {
        mUserModel = null;
        super.onDestroy();
    }

    public void classOfLecturer(String userId) {
        Disposable subscription = mUpdateUserRepository.callApiClassOfLecturer(userId)
                .compose(SchedulerHelper.applySchedulersLoadingAction(this::showLoading, this::hideLoading))
                .doOnError(throwable -> Timber.d("Failed to load 2 %s", throwable.getMessage()))
                .subscribe(dataList -> {
                    if (mNavigator != null) {
                        Bundle bundle = new BundleBuilder()
                                .putValue(BundleConstants.LIST_USER_BUNDLE, dataList)
                                .putValue(BundleConstants.USER_CODE_BUNDLE, UserType.CLAZZ)
                                .build();
                        mNavigator.startUserListActivity(mContext, bundle);
                    }
                }, throwable -> showFailedActionDialog(mContext.getString(R.string.general_error_message)));

        mSubscription.add(subscription);
    }


    public void studentInClass(String userId) {
        Disposable subscription = mUpdateUserRepository.callApiStudentInClass(userId)
                .compose(SchedulerHelper.applySchedulersLoadingAction(this::showLoading, this::hideLoading))
                .doOnError(throwable -> Timber.d("Failed to load 2 %s", throwable.getMessage()))
                .subscribe(dataList -> {
                    if (mNavigator != null) {
                        Bundle bundle = new BundleBuilder()
                                .putValue(BundleConstants.LIST_USER_BUNDLE, dataList)
                                .putValue(BundleConstants.USER_CODE_BUNDLE, UserType.STUDENT)
                                .build();
                        mNavigator.startUserListActivity(mContext, bundle);
                    }
                }, throwable -> showFailedActionDialog(mContext.getString(R.string.general_error_message)));

        mSubscription.add(subscription);
    }


    public void onAddNewClicked(int userCode) {

    }
}
