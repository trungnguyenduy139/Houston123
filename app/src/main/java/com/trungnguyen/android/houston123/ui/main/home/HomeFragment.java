package com.trungnguyen.android.houston123.ui.main.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.trungnguyen.android.houston123.BR;
import com.trungnguyen.android.houston123.R;
import com.trungnguyen.android.houston123.base.BaseFragment;
import com.trungnguyen.android.houston123.databinding.FragmentMainBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by trungnd4 on 18/08/2018.
 */
public class HomeFragment extends BaseFragment<FragmentMainBinding, HomeViewModel> {

    private HomeAdapter mHomeAdapter;

    private List<HomeItem> mHomeItems = new ArrayList<>();

    public static HomeFragment newInstance() {

        Bundle args = new Bundle();

        HomeFragment fragment = new HomeFragment();

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_main;
    }

    @Override
    public void initData() {
        super.initData();

        mHomeItems = viewModel.loadHomeResource();

        mHomeAdapter = new HomeAdapter(mHomeItems);
        binding.homeRecyclerView.setAdapter(mHomeAdapter);
        binding.homeRecyclerView.setLayoutManager(new GridLayoutManager(getBaseActivity(), 3));
        binding.homeRecyclerView.setHasFixedSize(true);
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @NonNull
    @Override
    public HomeViewModel initViewModel() {
        return new HomeViewModel(getBaseActivity());
    }
}
