package com.trungnguyen.android.houston123.ui.analyze;

import android.os.Bundle;

import com.trungnguyen.android.houston123.BR;
import com.trungnguyen.android.houston123.R;
import com.trungnguyen.android.houston123.base.BaseActivity;
import com.trungnguyen.android.houston123.databinding.ActivityAnalyzeBinding;

public class AnalyzeDataActivity extends BaseActivity<ActivityAnalyzeBinding, AnalyzeViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_analyze;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    protected void initViewDataBinding(Bundle savedInstanceState) {
        super.initViewDataBinding(savedInstanceState);
    }

    @Override
    public void initParam() {
        getDataManagerComponent().inject(this);
    }
}
