package com.trungnguyen.android.houston123.ui.main.personal;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.trungnguyen.android.houston123.BR;
import com.trungnguyen.android.houston123.R;
import com.trungnguyen.android.houston123.base.BaseFragment;
import com.trungnguyen.android.houston123.data.LoginInfoModel;
import com.trungnguyen.android.houston123.databinding.FragmentPersonalBinding;
import com.trungnguyen.android.houston123.injection.Injector;
import com.trungnguyen.android.houston123.injection.UserComponent;
import com.trungnguyen.android.houston123.ui.userdetail.ItemDetailModel;
import com.trungnguyen.android.houston123.util.Lists;
import com.trungnguyen.android.houston123.util.Navigator;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by trungnd4 on 18/08/2018.
 */
public class PersonalFragment extends BaseFragment<FragmentPersonalBinding, PersonalViewModel> implements IPersonalView {

    @Inject
    Navigator mNavigator;

    LoginInfoModel mLoginModel;

    private PersonalInfoAdapter mAdapter;

    @NonNull
    public static PersonalFragment newInstance() {

        Bundle args = new Bundle();

        PersonalFragment fragment = new PersonalFragment();

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_personal;
    }

    @Override
    public void initData() {
        super.initData();

        UserComponent userComponent = Injector.getInstance().getUserComponent();

        if (userComponent != null) {
            mLoginModel = userComponent.getLoginModel();
        }

        mAdapter = new PersonalInfoAdapter(new ArrayList<>());
        binding.rvLoginInfo.setAdapter(mAdapter);
        if (getActivity() != null) {
            binding.rvLoginInfo.setLayoutManager(new LinearLayoutManager(getActivity()));
        }

        if (mLoginModel != null) {
            viewModel.loadUserInfoResource(mLoginModel);
        }
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initParam() {
        getDataManagerComponent().inject(this);
    }

    @Override
    public void successToLogout() {
        Activity activity = getBaseActivity();
        if (activity == null || activity.isFinishing()) {
            return;
        }
        Injector.getInstance().releaseUserScope();
        mNavigator.startLoginActivity(activity);
        activity.finish();
    }

    @Override
    public void onLoadResourceCompleted(List<ItemDetailModel> items) {
        if (Lists.isEmptyOrNull(items)) {
            return;
        }
        mAdapter.addItems(items);
    }
}
