package com.trungnguyen.android.houston123.ui.listuser;

import android.databinding.ObservableField;

import com.trungnguyen.android.houston123.base.BaseViewModel;

/**
 * Created by trungnd4 on 14/07/2018.
 */
public class UserItemViewModel extends BaseViewModel {

    public final ObservableField<String> mImgUrl;

    public final ObservableField<String> mName;

    public final ObservableField<String> mPosition;

    private final UserModel mUserModel;

    private OnUserClickListener mListener;

    public UserItemViewModel(UserModel userModel, OnUserClickListener listener) {
        this.mUserModel = userModel;
        mImgUrl = new ObservableField<>(mUserModel.getmUserImg());
        mName = new ObservableField<>(mUserModel.getmName());
        mPosition = new ObservableField<>(mUserModel.getmPosition());
        mListener = listener;
    }

    public boolean onItemLongClick() {
        if (mListener == null) {
            return false;
        }
        return mListener.onUserLongClick();
    }

    public void onItemClick() {
        if (mListener == null) {
            return;
        }
        mListener.onUserClick();
    }

    interface OnUserClickListener {
        void onUserClick();

        boolean onUserLongClick();
    }
}
