package com.trungnguyen.android.houston123.ui.userdetail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.trungnguyen.android.houston123.R;
import com.trungnguyen.android.houston123.anotation.DetailServiceType;
import com.trungnguyen.android.houston123.base.BaseModel;
import com.trungnguyen.android.houston123.util.BundleConstants;
import com.trungnguyen.android.houston123.util.Constants;
import com.trungnguyen.android.houston123.util.Lists;
import com.trungnguyen.android.houston123.util.ModelResourceLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by trungnd4 on 26/08/2018.
 */
public class UpdateDetailUserActivity extends DetailUserActivity {

    @NonNull
    private List<ItemDetailModel> mItemDetailList = new ArrayList<>();
    private UpdateUserAdapter mUserDetailAdapter;
    private boolean mIsAddNew = false;
    private int mUserCode = Constants.DEFAULT_CODE_VALUE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        BaseModel baseUserModel = null;
        if (bundle != null) {
            mIsAddNew = bundle.containsKey(BundleConstants.ADD_NEW_USER_BUNDLE);
            if (mIsAddNew) {
                mUserCode = bundle.getInt(BundleConstants.ADD_NEW_USER_BUNDLE);
            } else
                baseUserModel = (BaseModel) bundle.getSerializable(BundleConstants.KEY_UPDATE_USER_DETAIL);
        }
        if (baseUserModel != null) {
            viewModel.setLecturerModel(baseUserModel);
            viewModel.initDetailList(baseUserModel);
        } else {
            mItemDetailList.addAll(ModelResourceLoader.loadEmptyResources(mUserCode));
        }
        mUserDetailAdapter = new UpdateUserAdapter(mItemDetailList);

        viewModel.attachAdapter(mUserDetailAdapter);

        viewModel.setApply(baseUserModel == null && mIsAddNew ? DetailServiceType.START_NEW : DetailServiceType.DO_UPDATE);

        binding.detailUserRecycler.setLayoutManager(new LinearLayoutManager(this));
        binding.detailUserRecycler.setAdapter(mUserDetailAdapter);

        setTitle(getResources().getString(R.string.update_user_detail));
    }

    @Override
    public void updateResourceList(List<ItemDetailModel> list) {
        if (Lists.isEmptyOrNull(list) || mUserDetailAdapter == null) {
            return;
        }
        mUserDetailAdapter.addItems(list);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        int menuId = mIsAddNew ? R.menu.detail_add_new_menu : R.menu.detail_update_menu;
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(menuId, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.add_new_action:
                viewModel.onAddNewClicked(mUserCode);
                break;
            case R.id.update_action:
                BaseModel model = mUserDetailAdapter.getModelData(mUserCode, binding.detailUserRecycler);
                viewModel.onUpdateClick(DetailServiceType.DO_UPDATE, model);
                break;
            default:
                break;
        }
        return false;
    }
}
