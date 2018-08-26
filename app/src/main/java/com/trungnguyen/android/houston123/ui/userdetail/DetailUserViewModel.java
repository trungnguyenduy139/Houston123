package com.trungnguyen.android.houston123.ui.userdetail;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.trungnguyen.android.houston123.base.BaseListViewModel;
import com.trungnguyen.android.houston123.base.BaseUserModel;
import com.trungnguyen.android.houston123.util.AppLogger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by trungnd4 on 20/07/2018.
 */
public class DetailUserViewModel extends BaseListViewModel<IDetailUserView, UserDetailListener> implements UserDetailListener {

    @Nullable
    public BaseUserModel mUserModel;

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

    public DetailUserViewModel(Context context) {

        super(context);
        getDataManagerComponent().inject(this);
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
