package com.trungnguyen.android.houston123.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;

import com.trungnguyen.android.houston123.injection.DataManagerComponent;
import com.trungnguyen.android.houston123.injection.Injector;

import io.reactivex.disposables.CompositeDisposable;


/**
 * Created by goldze on 2017/6/15.
 */
public class BaseViewModel<View> implements IBaseViewModel<View> {
    protected Context context;
    protected Fragment fragment;
    protected final CompositeDisposable mSubscription = new CompositeDisposable();
    @Nullable
    protected View mView;

    public BaseViewModel() {
    }

    public BaseViewModel(Context context) {
        this.context = context;
    }

    public BaseViewModel(Fragment fragment) {
        this(fragment.getContext());
        this.fragment = fragment;
    }

    private AlertDialog dialog;

    public void showDialog() {
        showDialog("Dialog...");
    }

    public void showDialog(String title) {
        if (dialog != null) {
            dialog.show();
        } else {
//            AlertDialog.Builder builder = new AlertDialog.Builder();
//            dialog = builder.show();
        }
    }

    public void dismissDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }


    public void startActivity(Class<?> clz) {
        context.startActivity(new Intent(context, clz));
    }

    public void startActivity(Class<?> clz, @Nullable Bundle bundle) {
        Intent intent = new Intent(context, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }


    public void startContainerActivity(String canonicalName, @Nullable Bundle bundle) {
        Intent intent = new Intent(context, ContainerActivity.class);
        intent.putExtra(ContainerActivity.FRAGMENT, canonicalName);
        if (bundle != null) {
            intent.putExtra(ContainerActivity.BUNDLE, bundle);
        }
        context.startActivity(intent);
    }


    public void startContainerActivity(String canonicalName) {
        Intent intent = new Intent(context, ContainerActivity.class);
        intent.putExtra(ContainerActivity.FRAGMENT, canonicalName);
        context.startActivity(intent);
    }

    @Override
    public void onCreate(@Nullable View view) {
        if (view != null) {
            mView = view;
        }
    }

    @Override
    public void onDestroy() {
        Injector.getInstance().releaseViewModelScope();
        if (mSubscription != null) {
            mSubscription.clear();
        }
        mView = null;
    }

    public void showLoading() {
        if (mView instanceof BaseActivity) {
            ((BaseActivity) mView).showLoadingDialog();
        }
    }

    public void hideLoading() {
        if (mView instanceof BaseActivity) {
            ((BaseActivity) mView).hideLoadingDialog();
        }
    }

    @Override
    public void registerRxBus() {
    }

    @Override
    public void removeRxBus() {
    }
}
