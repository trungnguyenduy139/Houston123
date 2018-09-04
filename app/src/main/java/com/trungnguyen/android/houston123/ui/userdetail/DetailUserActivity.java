package com.trungnguyen.android.houston123.ui.userdetail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;

import com.trungnguyen.android.houston123.R;
import com.trungnguyen.android.houston123.BR;

import com.trungnguyen.android.houston123.base.BaseToolbarActivity;
import com.trungnguyen.android.houston123.base.BaseUserModel;
import com.trungnguyen.android.houston123.databinding.ActivityDetailUserBinding;
import com.trungnguyen.android.houston123.util.BundleConstants;

import java.util.ArrayList;
import java.util.List;

public class DetailUserActivity extends BaseToolbarActivity<ActivityDetailUserBinding, DetailUserViewModel>
        implements IDetailUserView {

    @NonNull
    private List<ItemDetailModel> mItemDetailList = new ArrayList<>();
    private UserDetailAdapter mUserDetailAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        BaseUserModel baseUserModel = null;
        if (bundle != null) {
            baseUserModel = (BaseUserModel) bundle.getSerializable(BundleConstants.KEY_USER_DETAIL);
        }
        if (baseUserModel != null) {
            viewModel.setLecturerModel(baseUserModel);
            mItemDetailList.addAll(viewModel.initDetailList(baseUserModel));
        }

        mUserDetailAdapter = new UserDetailAdapter(mItemDetailList);

        viewModel.attachAdapter(mUserDetailAdapter);

        binding.detailUserRecycler.setLayoutManager(new LinearLayoutManager(this));
        binding.detailUserRecycler.setAdapter(mUserDetailAdapter);

        setTitle(getResources().getString(R.string.user_detail));
    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_detail_user;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @NonNull
    @Override
    public DetailUserViewModel initViewModel() {
        return new DetailUserViewModel(this);
    }

    @Override
    public BaseUserModel getUserModel() {
        return null;
    }
}
