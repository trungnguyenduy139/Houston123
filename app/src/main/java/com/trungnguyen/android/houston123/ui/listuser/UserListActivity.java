package com.trungnguyen.android.houston123.ui.listuser;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.trungnguyen.android.houston123.BR;
import com.trungnguyen.android.houston123.R;
import com.trungnguyen.android.houston123.base.BaseModel;
import com.trungnguyen.android.houston123.base.BaseToolbarActivity;
import com.trungnguyen.android.houston123.bus.DeletedUserEvent;
import com.trungnguyen.android.houston123.databinding.ActivityUserListBinding;
import com.trungnguyen.android.houston123.util.BundleConstants;
import com.trungnguyen.android.houston123.util.Constants;
import com.trungnguyen.android.houston123.util.Lists;
import com.trungnguyen.android.houston123.widget.InfiniteScrollListener;
import com.trungnguyen.android.houston123.widget.sweetalert.SweetAlertDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import timber.log.Timber;

/**
 * Created by trungnd4 on 13/07/2018.
 */
public class UserListActivity extends BaseToolbarActivity<ActivityUserListBinding, UserListViewModel> implements
        IUserListView, SwipeRefreshLayout.OnRefreshListener {

    private UserListAdapter<BaseModel> mListAdapter;
    private int mUserCode;
    private List<BaseModel> mDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        mUserCode = intent.getIntExtra(BundleConstants.USER_CODE_BUNDLE, Constants.DEFAULT_CODE_VALUE);
        mDataList = getData(intent);
        mListAdapter = new UserListAdapter<>(mDataList);
        viewModel.attachAdapter(mListAdapter);
        mListAdapter.setLoaderState(viewModel.getHasLoader());
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

        EventBus.getDefault().register(this);
    }


    @SuppressWarnings("unchecked")
    private List<BaseModel> getData(Intent intent) {
        List<BaseModel> data;
        try {
            data = (List<BaseModel>) intent.getSerializableExtra(BundleConstants.LIST_USER_BUNDLE);
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
        EventBus.getDefault().unregister(this);
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
                    BaseModel baseUserModel = null;
                    if (!Lists.isEmptyOrNull(mDataList) && position >= 0 && position < mDataList.size()) {
                        baseUserModel = mDataList.get(position);
                    }
                    viewModel.doRemoveUser(mUserCode, position, baseUserModel);
                    sweetAlertDialog.dismiss();
                });
        dialog.show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUserDeletedOnDetailScreen(DeletedUserEvent event) {
        if (event == null) {
            return;
        }
        int position = event.getUserPosition();
        successToDeleteUser(position);
    }

    @Override
    public void doSearchAction(String searchSequence) {
        viewModel.doSearchAction(searchSequence);
    }

    @Override
    public void doLoadMore(Collection<? extends BaseModel> userModels, boolean hasLoader) {
        if (mListAdapter != null) {
            mListAdapter.setLoaderState(hasLoader);
            mListAdapter.addItems(userModels);
        }
    }

    @Override
    public void doRefreshList(Collection<? extends BaseModel> usersModels, boolean hasLoader) {
        if (mListAdapter == null) {
            return;
        }
        mListAdapter.setLoaderState(hasLoader);
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
        new SweetAlertDialog(this)
                .setContentText(getString(R.string.delete_user_success)).
                setConfirmText(getString(R.string.close_dialog)).
                setConfirmClickListener(sweetAlertDialog1 -> {
                    if (sweetAlertDialog1 != null) {
                        sweetAlertDialog1.dismissWithAnimation();
                    }
                }).show();
    }

    @Override
    public void failedToDeleteUser() {
        showErrorDialog(getString(R.string.delete_user_failed));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.list_user_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.add_new:
                viewModel.handleAddNewUser(mUserCode);
                break;
            default:
                break;
        }
        return false;
    }

    @Override
    public int getCurrentUserCode() {
        return mUserCode;
    }

    @Override
    public void handleSearchResult() {
        if (mListAdapter != null) {
            mListAdapter.notifyDataSetChanged();
        }
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
