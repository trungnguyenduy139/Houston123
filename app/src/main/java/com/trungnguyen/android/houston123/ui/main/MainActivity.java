package com.trungnguyen.android.houston123.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.trungnguyen.android.houston123.BR;
import com.trungnguyen.android.houston123.R;
import com.trungnguyen.android.houston123.base.BaseActivity;
import com.trungnguyen.android.houston123.databinding.ActivityMainBinding;
import com.trungnguyen.android.houston123.ui.userdetail.IDetailUserView;
import com.trungnguyen.android.houston123.widget.HomeBottomNavigationView;
import com.trungnguyen.android.houston123.widget.NonSwipeableViewPager;


public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> implements IDetailUserView {

    private HomeBottomNavigationView mHomeBottomNavigationView;

    private NonSwipeableViewPager mViewPager;

    private MainPagerAdapter mMainPagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHomeBottomNavigationView = findViewById(R.id.main_bottom_navigation);
        mViewPager = findViewById(R.id.main_view_pager);

        mMainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mMainPagerAdapter);
        mViewPager.setOffscreenPageLimit(mMainPagerAdapter.getCount() - 1);
        mHomeBottomNavigationView.setViewPager(mViewPager);
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
