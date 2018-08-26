package com.trungnguyen.android.houston123.ui.main.tool;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.trungnguyen.android.houston123.BR;
import com.trungnguyen.android.houston123.R;
import com.trungnguyen.android.houston123.base.BaseFragment;
import com.trungnguyen.android.houston123.databinding.FragmentToolBinding;

/**
 * Created by trungnd4 on 18/08/2018.
 */
public class ToolFragment extends BaseFragment<FragmentToolBinding, ToolViewModel> {

    @NonNull
    public static ToolFragment newInstance() {

        Bundle args = new Bundle();

        ToolFragment fragment = new ToolFragment();

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_tool;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @NonNull
    @Override
    public ToolViewModel initViewModel() {
        return new ToolViewModel(getBaseActivity());
    }
}
