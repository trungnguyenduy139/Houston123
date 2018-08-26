package com.trungnguyen.android.houston123.ui.main.personal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.trungnguyen.android.houston123.BR;
import com.trungnguyen.android.houston123.R;
import com.trungnguyen.android.houston123.base.BaseFragment;
import com.trungnguyen.android.houston123.databinding.FragmentPersonalBinding;

/**
 * Created by trungnd4 on 18/08/2018.
 */
public class PersonalFragment extends BaseFragment<FragmentPersonalBinding, PersonalViewModel> {

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

    @NonNull
    @Override
    public PersonalViewModel initViewModel() {
        return new PersonalViewModel();
    }
}
