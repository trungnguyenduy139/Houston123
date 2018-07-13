package com.trungnguyen.android.houston123.ui.listuser;

import android.os.Bundle;

import com.trungnguyen.android.houston123.R;
import com.trungnguyen.android.houston123.BR;
import com.trungnguyen.android.houston123.base.BaseActivity;
import com.trungnguyen.android.houston123.databinding.ActivityUserListBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by trungnd4 on 13/07/2018.
 */
public class UserListActivity extends BaseActivity<ActivityUserListBinding, UserListViewModel> {

    private UserListAdapter mListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mListAdapter = new UserListAdapter(initTemporaryData());
    }

    private List<UserModel> initTemporaryData() {
        List<UserModel> userList = new ArrayList<>();
        userList.add(new UserModel());
        userList.add(new UserModel());
        userList.add(new UserModel());
        userList.add(new UserModel());
        userList.add(new UserModel());
        userList.add(new UserModel());
        userList.add(new UserModel());
        userList.add(new UserModel());
        userList.add(new UserModel());
        userList.add(new UserModel());

        return userList;
    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_user_list;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public UserListViewModel initViewModel() {
        return new UserListViewModel(this);
    }
}
