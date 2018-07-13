package com.trungnguyen.android.houston123.ui.listuser;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.trungnguyen.android.houston123.base.BaseViewModel;

import java.util.List;

/**
 * Created by trungnd4 on 13/07/2018.
 */
public class UserListViewModel extends BaseViewModel {

    public final ObservableList<UserModel> userObservableArrayList = new ObservableArrayList<>();

    private final MutableLiveData<List<UserModel>> mUserListLiveData;

    public UserListViewModel(Context context) {
        super(context);
        mUserListLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<List<UserModel>> getUserListLiveData() {
        return mUserListLiveData;
    }
}
