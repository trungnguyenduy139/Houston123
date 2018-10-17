package com.trungnguyen.android.houston123.ui.listuser;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;

import com.trungnguyen.android.houston123.R;
import com.trungnguyen.android.houston123.BR;
import com.trungnguyen.android.houston123.base.BaseToolbarActivity;
import com.trungnguyen.android.houston123.base.BaseUserModel;
import com.trungnguyen.android.houston123.databinding.ActivityUserListBinding;
import com.trungnguyen.android.houston123.util.BundleConstants;
import com.trungnguyen.android.houston123.util.Constants;
import com.trungnguyen.android.houston123.widget.InfiniteScrollListener;
import com.trungnguyen.android.houston123.widget.sweetalert.SweetAlertDialog;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import timber.log.Timber;

/**
 * Created by trungnd4 on 13/07/2018.
 */
public class UserListActivity extends BaseToolbarActivity<ActivityUserListBinding, UserListViewModel> implements
        IUserListView, SwipeRefreshLayout.OnRefreshListener {

    private UserListAdapter<BaseUserModel> mListAdapter;
    private int mUserCode;
    private List<BaseUserModel> mDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        mUserCode = intent.getIntExtra(BundleConstants.USER_CODE_BUNDLE, Constants.DEFAULT_CODE_VALUE);
        mDataList = getData(intent);
        mListAdapter = new UserListAdapter<>(mDataList);
        viewModel.attachAdapter(mListAdapter);
        binding.swipeToRefreshUserList.setOnRefreshListener(this);
        binding.userListRecycler.setLayoutManager(new LinearLayoutManager(this));
        binding.userListRecycler.setAdapter(mListAdapter);
        binding.userListRecycler.addOnScrollListener(new InfiniteScrollListener() {
            @Override
            protected void onLoadMore() {
                if (mUserCode != Constants.DEFAULT_CODE_VALUE) {
                    viewModel.nextPage(mUserCode);
                }
            }
        });
        setTitle(getResources().getString(R.string.user_list));

        viewModel.getUserListLiveData().observe(this, o -> {

        });
    }

    @SuppressWarnings("unchecked")
    private List<BaseUserModel> getData(Intent intent) {
        List<BaseUserModel> data;
        try {
            data = (List<BaseUserModel>) intent.getSerializableExtra(BundleConstants.LIST_USER_BUNDLE);
        } catch (Exception e) {
            data = new ArrayList<>();
            Timber.d("Failed to parse list of Users");
        }
        return data;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mListAdapter != null) {
            mListAdapter.releaseListener();
        }
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
    public void showConfirmDeleteUserDialog(int position) {
        SweetAlertDialog dialog = new SweetAlertDialog(this);
        dialog.setContentText(getString(R.string.confirm_delete_user))
                .setConfirmText(getString(R.string.dialog_confirm_text))
                .setCancelText(getString(R.string.dialog_cancel_text))
                .setConfirmClickListener(sweetAlertDialog -> {
                    if (sweetAlertDialog == null || mUserCode == Constants.DEFAULT_CODE_VALUE) {
                        return;
                    }
                    viewModel.doRemoveUser(mUserCode, position, "QL123456");
                    //todo: Change with userId instead of hard coded value
                    sweetAlertDialog.dismiss();
                });
        dialog.show();
    }

    @Override
    public void doSearchAction(String searchSequence) {
        if (mListAdapter != null) {
            mListAdapter.searchAction(searchSequence);
        }
    }

    @Override
    public void doLoadMore(Collection<? extends BaseUserModel> userModels) {
        if (mListAdapter != null) {
            mListAdapter.addItems(userModels);
        }
    }

    @Override
    public void doRefreshList(Collection<? extends BaseUserModel> usersModels) {
        if (mListAdapter == null) {
            return;
        }
        mListAdapter.clearItems();
        mListAdapter.addItems(usersModels);
    }

    @Override
    public void setRefreshing(boolean isRefreshing) {
        binding.swipeToRefreshUserList.post(() -> binding.swipeToRefreshUserList.setRefreshing(isRefreshing));
    }

    @Override
    public void successToDeleteUser(int position) {
        if (mListAdapter != null) {
            mListAdapter.removeUser(position);
        }
    }

    @Override
    public void failedToDeleteUser() {
        new SweetAlertDialog(this)
                .setContentText(getString(R.string.delete_user_failed)).
                setConfirmText(getString(R.string.close_dialog)).
                setConfirmClickListener(sweetAlertDialog1 -> {
                    if (sweetAlertDialog1 != null) {
                        sweetAlertDialog1.dismissWithAnimation();
                    }
                }).show();
    }

    @Override
    public void initParam() {
        getDataManagerComponent().inject(this);
    }

    @Override
    public void onRefresh() {
        if (viewModel != null && mUserCode != Constants.DEFAULT_CODE_VALUE) {
            viewModel.refreshList(mUserCode);
        }
    }
}
