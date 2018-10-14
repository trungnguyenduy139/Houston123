package com.trungnguyen.android.houston123.ui.main.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trungnguyen.android.houston123.BR;
import com.trungnguyen.android.houston123.R;
import com.trungnguyen.android.houston123.base.BaseActivity;
import com.trungnguyen.android.houston123.base.BaseFragment;
import com.trungnguyen.android.houston123.base.BaseUserModel;
import com.trungnguyen.android.houston123.databinding.FragmentMainBinding;
import com.trungnguyen.android.houston123.util.BundleBuilder;
import com.trungnguyen.android.houston123.util.BundleConstants;
import com.trungnguyen.android.houston123.util.Navigator;
import com.trungnguyen.android.houston123.widget.sweetalert.SweetAlertDialog;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by trungnd4 on 18/08/2018.
 */
public class HomeFragment extends BaseFragment<FragmentMainBinding, HomeViewModel> implements IHomeView {

    private HomeAdapter mHomeAdapter;

    @Inject
    Navigator mNavigator;

    @NonNull
    private List<HomeItem> mHomeItems = new ArrayList<>();

    @NonNull
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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (viewModel == null) {
            return;
        }
        viewModel.attachAdapter(mHomeAdapter);
    }

    @Override
    public void initParam() {
        getDataManagerComponent().inject(this);
    }

    @Override
    public void initData() {
        super.initData();
        if (viewModel == null) {
            return;
        }
        mHomeAdapter = new HomeAdapter(mHomeItems);
        binding.homeRecyclerView.setAdapter(mHomeAdapter);
        binding.homeRecyclerView.setLayoutManager(new GridLayoutManager(getBaseActivity(), 3));

        viewModel.loadHomeResource();
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void onLoadHomeResourcesCompleted(List<HomeItem> homeItems) {
        if (mHomeAdapter == null) {
            return;
        }
        mHomeAdapter.addItems(homeItems);
    }

    @Override
    public void failedToLoadUsers(Throwable throwable) {
        if (getBaseActivity() == null || getBaseActivity().isFinishing()) {
            return;
        }
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(getBaseActivity(), SweetAlertDialog.ERROR_TYPE);
        sweetAlertDialog.setContentText(getString(R.string.default_api_error_msg));
        sweetAlertDialog.show();
    }

    @Override
    public void successToLoadUsers(int code, Collection<? extends BaseUserModel> userModels) {
        if (getBaseActivity() == null || getBaseActivity().isFinishing()) {
            return;
        }
        Bundle bundle = new BundleBuilder()
                .putValue(BundleConstants.LIST_USER_BUNDLE, userModels)
                .putValue(BundleConstants.USER_CODE_BUNDLE, code)
                .build();
        if (bundle == null) {
            return;
        }
        mNavigator.startUserListActivity(getBaseActivity(), bundle);
    }
}
