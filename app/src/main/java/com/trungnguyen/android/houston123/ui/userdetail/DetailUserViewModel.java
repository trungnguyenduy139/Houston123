package com.trungnguyen.android.houston123.ui.userdetail;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.trungnguyen.android.houston123.anotation.OnClick;
import com.trungnguyen.android.houston123.base.BaseListViewModel;
import com.trungnguyen.android.houston123.base.BaseUserModel;
import com.trungnguyen.android.houston123.util.AppLogger;
import com.trungnguyen.android.houston123.util.BundleBuilder;
import com.trungnguyen.android.houston123.util.BundleConstants;
import com.trungnguyen.android.houston123.util.Navigator;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by trungnd4 on 20/07/2018.
 */
public class DetailUserViewModel extends BaseListViewModel<IDetailUserView, UserDetailListener> implements UserDetailListener {

    @Nullable
    public BaseUserModel mUserModel;

    Navigator mNavigator;

    public boolean mIsApply = false;

    public void setLecturerModel(@Nullable BaseUserModel userModel) {
        if (userModel == null) {
            return;
        }
        mUserModel = userModel;
    }

    @NonNull
    public List<ItemDetailModel> initDetailList(@NonNull BaseUserModel baseUserModel) {
        try {
            return new ArrayList<>(baseUserModel.convert());
        } catch (Exception e) {
            AppLogger.w("init Dynamic detail list error", e.getMessage());
            return new ArrayList<>();
        }
    }

    @Inject
    public DetailUserViewModel(Context context, Navigator navigator) {
        super(context);
        this.mNavigator = navigator;
    }

    @OnClick
    public void onUpdateClick(boolean isApplyApi) {
        if (mUserModel == null) {
            return;
        }
        if (!isApplyApi) {
            Bundle bundle = new BundleBuilder().putValue(BundleConstants.KEY_UPDATE_USER_DETAIL, mUserModel).build();
            mNavigator.startEditDetailActivity(context, bundle);
        } else {
            // make repository call
        }
    }

    public void setApply(boolean apply) {
        mIsApply = apply;
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
