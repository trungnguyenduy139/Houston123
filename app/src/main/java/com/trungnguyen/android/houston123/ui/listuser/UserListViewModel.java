package com.trungnguyen.android.houston123.ui.listuser;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.trungnguyen.android.houston123.base.BaseUserModel;
import com.trungnguyen.android.houston123.base.BaseViewModel;
import com.trungnguyen.android.houston123.repository.userlist.UserListRepository;
import com.trungnguyen.android.houston123.repository.userlist.UserListStore;
import com.trungnguyen.android.houston123.rx.SchedulerHelper;
import com.trungnguyen.android.houston123.util.AppLogger;
import com.trungnguyen.android.houston123.util.BundleBuilder;
import com.trungnguyen.android.houston123.util.BundleConstants;
import com.trungnguyen.android.houston123.util.Navigator;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * Created by trungnd4 on 13/07/2018.
 */
public class UserListViewModel extends BaseViewModel<IUserListView> implements UserListListener {

    private Navigator mNavigator;
    private UserListStore.Repository mUserListRepository;

    @NonNull
    private final MutableLiveData<List<BaseViewModel>> mUserListLiveData;

    @Inject
    UserListViewModel(Context context, Navigator navigator, UserListRepository userListRepository) {
        super(context);
        mUserListLiveData = new MutableLiveData<>();
        this.mNavigator = navigator;
        this.mUserListRepository = userListRepository;
    }

    @NonNull
    public MutableLiveData<List<BaseViewModel>> getUserListLiveData() {
        return mUserListLiveData;
    }

    public void attachAdapter(@NonNull UserListAdapter<BaseUserModel> adapter) {
        adapter.setListener(this);
    }

    @Override
    public void onItemClick(@NonNull BaseUserModel baseUserModel) {
        try {
            Bundle bundle = new BundleBuilder()
                    .putValue(BundleConstants.KEY_USER_DETAIL, baseUserModel)
                    .build();
            mNavigator.startDetailActivity(context, bundle);
        } catch (@NonNull ClassCastException | NullPointerException e) {
            AppLogger.w("UserListViewModel onItemClick() [%s]", e.getMessage());
        }
    }

    @Override
    public void onItemLongClick(int position) {
        if (mView == null) {
            return;
        }
        mView.showConfirmDeleteUserDialog(position);
    }

    public void onSearchTextChanged(CharSequence text) {
        if (mView != null) {
            String textSearch = text.toString();
            mView.doSearchAction(textSearch);
        }
    }

    public void nextPage(int code) {
        Disposable subscription = mUserListRepository.getPageFromLocal()
                .map(integer -> integer++)
                .flatMap(newInt -> mUserListRepository.handleUserServiceFlow(code, newInt))
                .compose(SchedulerHelper.applySchedulers())
                .subscribe(usersModels -> {
                    if (mView == null) {
                        return;
                    }
                    mView.doLoadMore(usersModels);
                }, throwable -> {

                });
        mSubscription.add(subscription);
    }
}
