package com.trungnguyen.android.houston123.ui.main.personal;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.trungnguyen.android.houston123.BR;
import com.trungnguyen.android.houston123.R;
import com.trungnguyen.android.houston123.base.BaseFragment;
import com.trungnguyen.android.houston123.databinding.FragmentPersonalBinding;
import com.trungnguyen.android.houston123.util.Navigator;

import javax.inject.Inject;

/**
 * Created by trungnd4 on 18/08/2018.
 */
public class PersonalFragment extends BaseFragment<FragmentPersonalBinding, PersonalViewModel> implements IPersonalView {

    @Inject
    Navigator mNavigator;

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
        mNavigator.startLoginActivity(activity);
        activity.finish();
    }
}
