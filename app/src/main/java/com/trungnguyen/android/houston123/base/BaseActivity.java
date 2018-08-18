package com.trungnguyen.android.houston123.base;

import android.content.pm.ActivityInfo;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.trungnguyen.android.houston123.bus.Messenger;

import java.util.Objects;


public abstract class BaseActivity<V extends ViewDataBinding, VM extends BaseViewModel>
        extends RxAppCompatActivity implements IBaseActivity, BaseFragment.Callback {

    protected V binding;

    protected VM viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        initParam();

        initViewDataBinding(savedInstanceState);

        initData();

        initViewObservable();

        viewModel.onCreate(this);

        viewModel.registerRxBus();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Objects.requireNonNull(Messenger.getDefault()).unregister(this);
        viewModel.removeRxBus();
        viewModel.onDestroy();
        viewModel = null;
        binding.unbind();
    }


    private void initViewDataBinding(Bundle savedInstanceState) {
        binding = DataBindingUtil.setContentView(this, initContentView(savedInstanceState));
        binding.setVariable(initVariableId(), viewModel = initViewModel());
    }

    public void refreshLayout() {
        if (viewModel != null) {
            binding.setVariable(initVariableId(), viewModel);
        }
    }

    @Override
    public void initParam() {

    }

    public abstract int initContentView(Bundle savedInstanceState);

    public abstract int initVariableId();

    @NonNull
    public abstract VM initViewModel();

    @Override
    public void initData() {

    }

    @Override
    public void initViewObservable() {

    }


    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }
}
