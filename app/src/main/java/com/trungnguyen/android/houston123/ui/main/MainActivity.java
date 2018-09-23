package com.trungnguyen.android.houston123.ui.main;

import android.os.Bundle;

import com.trungnguyen.android.houston123.BR;
import com.trungnguyen.android.houston123.R;
import com.trungnguyen.android.houston123.base.BaseActivity;
import com.trungnguyen.android.houston123.databinding.ActivityMainBinding;


public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> implements IMainView {

    private MainPagerAdapter mMainPagerAdapter;

    @Override
    public void initParam() {
        getDataManagerComponent().inject(this);
    }

    @Override
    public void initData() {
        super.initData();
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
}
