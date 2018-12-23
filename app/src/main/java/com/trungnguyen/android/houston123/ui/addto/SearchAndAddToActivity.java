package com.trungnguyen.android.houston123.ui.addto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.trungnguyen.android.houston123.BR;
import com.trungnguyen.android.houston123.R;
import com.trungnguyen.android.houston123.base.BaseModel;
import com.trungnguyen.android.houston123.base.BaseToolbarActivity;
import com.trungnguyen.android.houston123.databinding.ActivitySearchAndAddBinding;
import com.trungnguyen.android.houston123.ui.listuser.UserListAdapter;
import com.trungnguyen.android.houston123.ui.listuser.UserListListener;
import com.trungnguyen.android.houston123.util.BundleConstants;
import com.trungnguyen.android.houston123.util.Constants;
import com.trungnguyen.android.houston123.widget.ToastCustom;
import com.trungnguyen.android.houston123.widget.sweetalert.SweetAlertDialog;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by trungnd4 on 23/12/2018.
 */
public class SearchAndAddToActivity extends BaseToolbarActivity<ActivitySearchAndAddBinding, SearchAndAddToViewModel> implements ISearchAndAddToView, UserListListener {

    private UserListAdapter<BaseModel> mDataAdapter;

    private List<BaseModel> mDataList = new ArrayList<>();

    private int mUserCode = Constants.DEFAULT_CODE_VALUE;

    private BaseModel mModel;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_search_and_add;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initParam() {
        getDataManagerComponent().inject(this);
    }

    @Override
    public void onSearchCompleted(@NotNull Collection<? extends BaseModel> userModels) {
        if (binding.searchListRecycler.getVisibility() == View.INVISIBLE) {
            binding.searchListRecycler.setVisibility(View.VISIBLE);
            binding.tvEmptyData.setVisibility(View.INVISIBLE);
        }
        if (mDataAdapter == null) {
            return;
        }
        mDataList = (List<BaseModel>) userModels;
        mDataAdapter.addItems(userModels);
    }

    @Override
    public void initData() {
        super.initData();
        Intent intent = getIntent();
        mUserCode = intent.getIntExtra(BundleConstants.USER_CODE_BUNDLE, Constants.DEFAULT_CODE_VALUE);
        mModel = (BaseModel) intent.getSerializableExtra(BundleConstants.ADD_TO_MODEL);
        binding.searchListRecycler.setLayoutManager(new LinearLayoutManager(this));
        mDataAdapter = new UserListAdapter<>(mDataList);
        mDataAdapter.setListener(this);
        mDataAdapter.setLoaderState(false);
        binding.searchListRecycler.setAdapter(mDataAdapter);
    }

    @Override
    public void addToCompleted() {
        ToastCustom.makeTopToastText(this, getString(R.string.update_user_success));
    }

    @Override
    public void onItemClick(BaseModel baseUserModel, int position) {
        new SweetAlertDialog(this)
                .setTitleText(getString(R.string.add_to_title))
                .setContentText(getString(R.string.add_to_message))
                .setConfirmText(getString(R.string.dialog_ok))
                .setCancelText(getString(R.string.close_dialog))
                .setCancelClickListener(SweetAlertDialog::dismissWithAnimation)
                .setConfirmClickListener(sweetAlertDialog -> {
                    if (sweetAlertDialog == null) {
                        return;
                    }
                    viewModel.onAddUserAction(mDataList.get(position), mModel.getModelId(), getUserCode());
                    sweetAlertDialog.dismissWithAnimation();
                })
                .show();
    }

    @Override
    public void onItemLongClick(int position) {

    }

    @Override
    public int getUserCode() {
        return mUserCode;
    }
}
