package com.trungnguyen.android.houston123.ui.userdetail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;

import com.trungnguyen.android.houston123.R;
import com.trungnguyen.android.houston123.anotation.DetailServiceType;
import com.trungnguyen.android.houston123.base.BaseModel;
import com.trungnguyen.android.houston123.base.BaseUserModel;
import com.trungnguyen.android.houston123.util.BundleConstants;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        BaseModel baseUserModel = null;
        if (bundle != null) {
            baseUserModel = (BaseModel) bundle.getSerializable(BundleConstants.KEY_UPDATE_USER_DETAIL);
        }
        if (baseUserModel != null) {
            viewModel.setLecturerModel(baseUserModel);
            viewModel.initDetailList(baseUserModel);
        } else {
            mItemDetailList.addAll(ModelResourceLoader.loadEmptyManagerResources());
        }
        mUserDetailAdapter = new UpdateUserAdapter(mItemDetailList);

        viewModel.attachAdapter(mUserDetailAdapter);

        viewModel.setApply(baseUserModel == null ? DetailServiceType.START_NEW : DetailServiceType.DO_UPDATE);

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
}
