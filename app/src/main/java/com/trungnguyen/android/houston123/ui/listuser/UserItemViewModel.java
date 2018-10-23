package com.trungnguyen.android.houston123.ui.listuser;

import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.trungnguyen.android.houston123.base.BaseItemViewModelListener;
import com.trungnguyen.android.houston123.base.BaseModel;
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
    public final ObservableField<String> mPhoneNumber;

    private final BaseModel mLecturerModel;

    private OnUserClickListener mListener;

    public UserItemViewModel(BaseModel lecturerModel, OnUserClickListener listener) {
        this.mLecturerModel = lecturerModel;
        mImgUrl = new ObservableField<>(mLecturerModel.getMainContent());
        mName = new ObservableField<>(mLecturerModel.getMainContent());
        mPhoneNumber = new ObservableField<>(mLecturerModel.getSubCotent());
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
        mListener.onItemClick();
    }

    interface OnUserClickListener extends BaseItemViewModelListener {

        boolean onUserLongClick();
    }
}
