package com.trungnguyen.android.houston123.ui.listuser;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.trungnguyen.android.houston123.R;
import com.trungnguyen.android.houston123.BR;
import com.trungnguyen.android.houston123.base.BaseToolbarActivity;
import com.trungnguyen.android.houston123.base.BaseUserModel;
import com.trungnguyen.android.houston123.databinding.ActivityUserListBinding;
import com.trungnguyen.android.houston123.util.BundleConstants;
import com.trungnguyen.android.houston123.widget.sweetalert.SweetAlertDialog;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * Created by trungnd4 on 13/07/2018.
 */
public class UserListActivity extends BaseToolbarActivity<ActivityUserListBinding, UserListViewModel> implements IUserListView {

    private UserListAdapter<BaseUserModel> mListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        mListAdapter = new UserListAdapter<>(getData(intent));
        viewModel.attachAdapter(mListAdapter);
        binding.userListRecycler.setLayoutManager(new LinearLayoutManager(this));
        binding.userListRecycler.setAdapter(mListAdapter);

        setTitle(getResources().getString(R.string.user_list));

        viewModel.getUserListLiveData().observe(this, o -> {

        });
    }

    @SuppressWarnings("unchecked")
    private List<BaseUserModel> getData(Intent intent) {
        List<BaseUserModel> data;
        try {
            data = (List<BaseUserModel>) intent.getSerializableExtra(BundleConstants.LIST_LECTURER_BUNDLE);
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
                    if (sweetAlertDialog == null || mListAdapter == null) {
                        return;
                    }
                    mListAdapter.removeUser(position);
                    sweetAlertDialog.dismissWithAnimation();
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
    public void initParam() {
        getDataManagerComponent().inject(this);
    }
}
