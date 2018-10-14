package com.trungnguyen.android.houston123.ui.userdetail;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.trungnguyen.android.houston123.anotation.DetailServiceType;
import com.trungnguyen.android.houston123.anotation.OnClick;
import com.trungnguyen.android.houston123.base.BaseListViewModel;
import com.trungnguyen.android.houston123.base.BaseUserModel;
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
    public BaseUserModel mUserModel;

    private Navigator mNavigator;

    public int mServiceActionCode = DetailServiceType.START_UPDATE;

    public void setLecturerModel(@Nullable BaseUserModel userModel) {
        if (userModel == null) {
            return;
        }
        mUserModel = userModel;
    }

    public void initDetailList(@NonNull BaseUserModel baseUserModel) {
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
    public DetailUserViewModel(Context context, Navigator navigator) {
        super(context);
        this.mNavigator = navigator;
    }

    @OnClick
    public void onUpdateClick(int code) {
        if (mUserModel == null) {
            return;
        }
        switch (code) {
            case DetailServiceType.START_UPDATE:
                Bundle bundle = new BundleBuilder().putValue(BundleConstants.KEY_UPDATE_USER_DETAIL, mUserModel).build();
                mNavigator.startEditDetailActivity(context, bundle);
                break;
            // make repository call
            // chose repository based on action code
        }
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
}
