package com.trungnguyen.android.houston123.base;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle2.components.support.RxFragment;
import com.trungnguyen.android.houston123.bus.Messenger;
import com.trungnguyen.android.houston123.injection.DataManagerComponent;
import com.trungnguyen.android.houston123.injection.Injector;

import java.util.Objects;

import javax.inject.Inject;

/**
 * Created by goldze on 2017/6/15.
 */
public abstract class BaseFragment<V extends ViewDataBinding, VM extends BaseViewModel>
        extends RxFragment implements IBaseActivity, IBaseLoadingView {

    protected V binding;

    @Inject
    protected VM viewModel;

    @Nullable
    private BaseActivity mActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initParam();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = (BaseActivity) activity;
    }

    @Override
    public void onDetach() {
        mActivity = null;
        super.onDetach();
    }

    @Nullable
    public BaseActivity getBaseActivity() {
        if (mActivity == null || mActivity.isFinishing()) {
            return null;
        }
        return mActivity;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Objects.requireNonNull(Messenger.getDefault()).unregister(this.getContext());

        if (viewModel == null) {
            return;
        }
        viewModel.removeRxBus();
        viewModel.onDestroy();
        viewModel = null;
        binding.unbind();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, initContentView(inflater, container, savedInstanceState), container, false);
        binding.setVariable(initVariableId(), viewModel);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initData();

        initViewObservable();

        if (viewModel == null) {
            return;
        }

        viewModel.onCreate(this);

        viewModel.registerRxBus();
    }

    protected DataManagerComponent getDataManagerComponent() {
        return Injector.getInstance().getDataManagerComponent();
    }

    public void refreshLayout() {
        if (viewModel != null) {
            binding.setVariable(initVariableId(), viewModel);
        }
    }


    public abstract int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);


    public abstract int initVariableId();

    @Override
    public void initData() {

    }

    @Override
    public void initViewObservable() {

    }

    @Override
    public void showLoadingDialog() {
        BaseActivity baseActivity = getBaseActivity();
        if (baseActivity == null || baseActivity.isFinishing()) {
            return;
        }
        baseActivity.showLoadingDialog();
    }

    @Override
    public void hideLoadingDialog() {
        BaseActivity baseActivity = getBaseActivity();
        if (baseActivity == null || baseActivity.isFinishing()) {
            return;
        }
        baseActivity.hideLoadingDialog();
    }

    @Override
    public void showErrorDialog(String errorMessage) {
        BaseActivity baseActivity = getBaseActivity();
        if (baseActivity == null || baseActivity.isFinishing()) {
            return;
        }
        baseActivity.showErrorDialog(errorMessage);
    }

    public boolean onBackPressed() {
        return false;
    }

    public interface Callback {

        void onFragmentAttached();

        void onFragmentDetached(String tag);
    }
}
