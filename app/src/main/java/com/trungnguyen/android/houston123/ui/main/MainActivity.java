package com.trungnguyen.android.houston123.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.trungnguyen.android.houston123.BR;
import com.trungnguyen.android.houston123.R;
import com.trungnguyen.android.houston123.base.BaseActivity;
import com.trungnguyen.android.houston123.data.LoginInfoModel;
import com.trungnguyen.android.houston123.databinding.ActivityMainBinding;
import com.trungnguyen.android.houston123.ui.main.personal.PersonalFragment;
import com.trungnguyen.android.houston123.ui.main.personal.PersonalInfoAdapter;


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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 555 && resultCode == 666) {
            Fragment fragment = mMainPagerAdapter.getPage(2);
            if (!(fragment instanceof PersonalFragment)) {
                return;
            }
            PersonalFragment personalFragment = (PersonalFragment) fragment;
            if (data == null) {
                return;
            }
            personalFragment.updateList((LoginInfoModel) data.getSerializableExtra("RESULT_INFO"));
        }
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
