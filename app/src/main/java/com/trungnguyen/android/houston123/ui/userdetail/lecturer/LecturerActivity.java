package com.trungnguyen.android.houston123.ui.userdetail.lecturer;

import android.os.Bundle;

import com.trungnguyen.android.houston123.R;
import com.trungnguyen.android.houston123.BR;

import com.trungnguyen.android.houston123.base.BaseToolbarActivity;
import com.trungnguyen.android.houston123.base.BaseUserModel;
import com.trungnguyen.android.houston123.databinding.ActivityLecturerBinding;
import com.trungnguyen.android.houston123.util.BundleConstants;

public class LecturerActivity extends BaseToolbarActivity<ActivityLecturerBinding, LecturerViewModel> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        BaseUserModel baseUserModel = null;
        if (bundle != null) {
            baseUserModel = (BaseUserModel) bundle.getSerializable(BundleConstants.KEY_USER_DETAIL);
        }
        if (baseUserModel != null && baseUserModel instanceof LecturerModel) {
            viewModel.setLecturerModel((LecturerModel) baseUserModel);
        }
    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_lecturer;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public LecturerViewModel initViewModel() {
        return new LecturerViewModel(this);
    }
}
