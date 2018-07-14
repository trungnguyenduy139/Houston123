package com.trungnguyen.android.houston123.ui.listuser;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import com.trungnguyen.android.houston123.base.BaseViewModel;
import com.trungnguyen.android.houston123.injection.Injector;
import com.trungnguyen.android.houston123.util.Navigator;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by trungnd4 on 13/07/2018.
 */
public class UserListViewModel extends BaseViewModel {

    @Inject
    Navigator mNavigator;

    private final MutableLiveData<List<UserModel>> mUserListLiveData;

    UserListViewModel(Context context) {
        super(context);
        Injector.getInstance().getDataManagerComponent().inject(this);
        mUserListLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<List<UserModel>> getUserListLiveData() {
        return mUserListLiveData;
    }


    public void onItemSelected() {
        mNavigator.startLoginActivity(context);
    }
}
