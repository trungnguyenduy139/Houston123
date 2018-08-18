package com.trungnguyen.android.houston123.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.trungnguyen.android.houston123.BR;
import com.trungnguyen.android.houston123.R;
import com.trungnguyen.android.houston123.base.BaseActivity;
import com.trungnguyen.android.houston123.databinding.ActivityMainBinding;
import com.trungnguyen.android.houston123.ui.userdetail.IDetailUserView;


public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> implements IDetailUserView {

    private MainPagerAdapter mMainPagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
        binding.mainViewPager.setAdapter(mMainPagerAdapter);
        binding.mainViewPager.setOffscreenPageLimit(mMainPagerAdapter.getCount() - 1);
        binding.mainBottomNavigation.setViewPager(binding.mainViewPager);
    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @NonNull
    @Override
    public MainViewModel initViewModel() {
        return new MainViewModel(this);
    }
}
