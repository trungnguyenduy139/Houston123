package com.trungnguyen.android.houston123.ui.main.home;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trungnguyen.android.houston123.BR;
import com.trungnguyen.android.houston123.R;
import com.trungnguyen.android.houston123.anotation.UserType;
import com.trungnguyen.android.houston123.base.BaseFragment;
import com.trungnguyen.android.houston123.base.BaseModel;
import com.trungnguyen.android.houston123.databinding.FragmentMainBinding;
import com.trungnguyen.android.houston123.util.BundleBuilder;
import com.trungnguyen.android.houston123.util.BundleConstants;
import com.trungnguyen.android.houston123.util.Navigator;
import com.trungnguyen.android.houston123.widget.sweetalert.SweetAlertDialog;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * Created by trungnd4 on 18/08/2018.
 */
public class HomeFragment extends BaseFragment<FragmentMainBinding, HomeViewModel> implements IHomeView {


    @Inject
    Navigator mNavigator;


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
        Activity activity = getBaseActivity();
        if (activity == null || activity.isFinishing()) {
            return;
        }
        activity.findViewById(R.id.llManager).setOnClickListener(v -> viewModel.onItemClick(UserType.MANAGER));
        activity.findViewById(R.id.llStudent).setOnClickListener(v -> viewModel.onItemClick(UserType.STUDENT));
        activity.findViewById(R.id.llLecturer).setOnClickListener(v -> viewModel.onItemClick(UserType.LECTURER));
        activity.findViewById(R.id.llClass).setOnClickListener(v -> viewModel.onItemClick(UserType.CLAZZ));
        activity.findViewById(R.id.llSubject).setOnClickListener(v -> viewModel.onItemClick(UserType.SUBJECT));
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void onLoadHomeResourcesCompleted(List<HomeItem> homeItems) {

    }

    @Override
    public void failedToLoadUsers(Throwable throwable) {
        if (getBaseActivity() == null || getBaseActivity().isFinishing()) {
            return;
        }
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(getBaseActivity(), SweetAlertDialog.ERROR_TYPE);
        sweetAlertDialog.setContentText(getString(R.string.default_api_error_msg));
        sweetAlertDialog.show();
        Timber.d("Load list model error [%s]", throwable.getMessage());
    }

    @Override
    public void successToLoadUsers(int code, Collection<? extends BaseModel> userModels) {
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
