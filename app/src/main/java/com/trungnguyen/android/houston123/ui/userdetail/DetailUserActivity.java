package com.trungnguyen.android.houston123.ui.userdetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.trungnguyen.android.houston123.R;
import com.trungnguyen.android.houston123.BR;

import com.trungnguyen.android.houston123.anotation.DetailServiceType;
import com.trungnguyen.android.houston123.base.BaseToolbarActivity;
import com.trungnguyen.android.houston123.base.BaseUserModel;
import com.trungnguyen.android.houston123.databinding.ActivityDetailUserBinding;
import com.trungnguyen.android.houston123.util.BundleConstants;
import com.trungnguyen.android.houston123.util.Constants;
import com.trungnguyen.android.houston123.util.Lists;
import com.trungnguyen.android.houston123.widget.sweetalert.SweetAlertDialog;

import java.util.ArrayList;
import java.util.List;

public class DetailUserActivity extends BaseToolbarActivity<ActivityDetailUserBinding, DetailUserViewModel>
        implements IDetailUserView {

    @NonNull
    private List<ItemDetailModel> mItemDetailList = new ArrayList<>();
    private UserDetailAdapter mUserDetailAdapter;
    private BaseUserModel mUserModel;
    private int mCode;
    private int mPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        BaseUserModel baseUserModel = null;
        if (bundle != null) {
            mPosition = bundle.getInt(BundleConstants.KEY_POSITION_USER);
            baseUserModel = (BaseUserModel) bundle.getSerializable(BundleConstants.KEY_USER_DETAIL);
            mCode = bundle.getInt(BundleConstants.KEY_CODE_DETAIL, Constants.DEFAULT_CODE_VALUE);
        }
        if (baseUserModel != null) {
            viewModel.setLecturerModel(baseUserModel);
            viewModel.initDetailList(baseUserModel);
            mUserModel = baseUserModel;
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

    @Override
    public BaseUserModel getUserModel() {
        return null;
    }

    @Override
    public void updateResourceList(List<ItemDetailModel> list) {
        if (Lists.isEmptyOrNull(list) || mUserDetailAdapter == null) {
            return;
        }
        mUserDetailAdapter.addItems(list);
    }

    @Override
    public void deleteUserSuccess() {
        setResult(Constants.DELETED_USER);
        setIntent(new Intent().putExtra(Constants.RESULT_DELETED, mPosition));
        finish();
    }

    @Override
    public void deleteUserFailed() {
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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.detail_user_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.detail_update:
                viewModel.onUpdateClick(DetailServiceType.START_UPDATE);
                break;
            case R.id.detail_delete:
                viewModel.deleteCurrentUser(mCode, mUserModel.getUserId());
                break;
        }
        return true;
    }

    @Override
    public void initParam() {
        getDataManagerComponent().inject(this);
    }
}
