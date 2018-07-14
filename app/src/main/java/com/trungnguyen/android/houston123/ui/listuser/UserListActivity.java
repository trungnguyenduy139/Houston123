package com.trungnguyen.android.houston123.ui.listuser;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.trungnguyen.android.houston123.R;
import com.trungnguyen.android.houston123.BR;
import com.trungnguyen.android.houston123.base.BaseActivity;
import com.trungnguyen.android.houston123.databinding.ActivityUserListBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by trungnd4 on 13/07/2018.
 */
public class UserListActivity extends BaseActivity<ActivityUserListBinding, UserListViewModel>
        implements UserListAdapter.OnItemSelected {

    private UserListAdapter mListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListAdapter = new UserListAdapter(initTemporaryData());
        mListAdapter.setListener(this);
        binding.userListRecycler.setLayoutManager(new LinearLayoutManager(this));
        binding.userListRecycler.setAdapter(mListAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mListAdapter.releaseListener();
        viewModel.getUserListLiveData().observe(this, userModels -> {
            
        });
    }

    private List<UserModel> initTemporaryData() {
        List<UserModel> userList = new ArrayList<>();
        UserModel user = new UserModel();
        user.setmName("Samantha Wilson");
        user.setmPosition("Senior QC Engineer");
        userList.add(user);
        userList.add(user);
        userList.add(user);
        userList.add(user);
        userList.add(user);
        userList.add(user);
        userList.add(user);
        userList.add(user);
        userList.add(user);
        userList.add(user);
        userList.add(user);
        userList.add(user);
        userList.add(user);

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

    @Override
    public void onItemClick(int position) {
        viewModel.onItemSelected();
    }
}
