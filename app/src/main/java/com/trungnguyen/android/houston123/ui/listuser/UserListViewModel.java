package com.trungnguyen.android.houston123.ui.listuser;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import com.trungnguyen.android.houston123.anotation.ToastType;
import com.trungnguyen.android.houston123.base.BaseViewModel;
import com.trungnguyen.android.houston123.injection.Injector;
import com.trungnguyen.android.houston123.util.Navigator;
import com.trungnguyen.android.houston123.widget.ToastCustom;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by trungnd4 on 13/07/2018.
 */
public class UserListViewModel extends BaseViewModel implements UserListListener {

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

    public void attachAdapter(UserListAdapter adapter) {
        adapter.setListener(this);
    }

    @Override
    public void onItemClick(int position) {
        mNavigator.startLoginActivity(context);
    }

    @Override
    public void onItemLongClick(int position) {
        ToastCustom.makeText(context, "Removed " +
                position, ToastCustom.LENGTH_SHORT, ToastType.TYPE_GENERAL);
    }
}
