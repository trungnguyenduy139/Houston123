package com.trungnguyen.android.houston123.ui.listuser;

import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.trungnguyen.android.houston123.base.BaseUserModel;
import com.trungnguyen.android.houston123.base.BaseViewModel;

/**
 * Created by trungnd4 on 14/07/2018.
 */
public class UserItemViewModel extends BaseViewModel {

    @NonNull
    public final ObservableField<String> mImgUrl;

    @NonNull
    public final ObservableField<String> mName;

    @NonNull
    public final ObservableField<String> mPosition;

    private final BaseUserModel mLecturerModel;

    private OnUserClickListener mListener;

    public UserItemViewModel(BaseUserModel lecturerModel, OnUserClickListener listener) {
        this.mLecturerModel = lecturerModel;
        mImgUrl = new ObservableField<>(mLecturerModel.getUserImg());
        mName = new ObservableField<>(mLecturerModel.getName());
        mPosition = new ObservableField<>(mLecturerModel.getPosition());
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
