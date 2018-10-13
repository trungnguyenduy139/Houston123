package com.trungnguyen.android.houston123.base;

import android.content.pm.ActivityInfo;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.trungnguyen.android.houston123.R;
import com.trungnguyen.android.houston123.bus.Messenger;
import com.trungnguyen.android.houston123.injection.DataManagerComponent;
import com.trungnguyen.android.houston123.injection.Injector;
import com.trungnguyen.android.houston123.widget.LoadingDialog;
import com.trungnguyen.android.houston123.widget.sweetalert.SweetAlertDialog;

import java.util.Objects;

import javax.inject.Inject;


public abstract class BaseActivity<V extends ViewDataBinding, VM extends BaseViewModel>
        extends RxAppCompatActivity implements IBaseActivity, IBaseLoadingView, BaseFragment.Callback {

    protected V binding;

    @Inject
    protected VM viewModel;

    private LoadingDialog mLoadingDialog;

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

        LoadingDialog.Builder dialogBuilder = new LoadingDialog.Builder(this);

        mLoadingDialog = dialogBuilder.create();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Objects.requireNonNull(Messenger.getDefault()).unregister(this);
        if (viewModel == null) {
            return;
        }
        viewModel.removeRxBus();
        viewModel.onDestroy();
        viewModel = null;
        binding.unbind();
    }

    @NonNull
    protected DataManagerComponent getDataManagerComponent() {
        return Injector.getInstance().getDataManagerComponent();
    }

    protected void initViewDataBinding(Bundle savedInstanceState) {
        binding = DataBindingUtil.setContentView(this, initContentView(savedInstanceState));
        binding.setVariable(initVariableId(), viewModel);
    }

    public void refreshLayout() {
        if (viewModel != null) {
            binding.setVariable(initVariableId(), viewModel);
        }
    }

    @Override
    public void showLoadingDialog() {
        if (mLoadingDialog == null || mLoadingDialog.isShowing()) {
            return;
        }
        mLoadingDialog.show();
    }

    @Override
    public void hideLoadingDialog() {
        if (mLoadingDialog == null || !mLoadingDialog.isShowing()) {
            return;
        }
        mLoadingDialog.dismiss();
    }

    protected void showNetworkErrorDialog() {
        String msg = getString(R.string.network_message);
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE);
        sweetAlertDialog.setContentText(msg);
        sweetAlertDialog.show();
    }


    public abstract int initContentView(Bundle savedInstanceState);

    public abstract int initVariableId();

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
