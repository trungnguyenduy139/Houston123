package com.trungnguyen.android.houston123.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;

import com.trungnguyen.android.houston123.injection.Injector;


/**
 * Created by goldze on 2017/6/15.
 */
public class BaseViewModel implements IBaseViewModel {
    protected Context context;
    protected Fragment fragment;

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
        showDialog("请稍后...");
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

    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent(context, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }


    public void startContainerActivity(String canonicalName, Bundle bundle) {
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
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
        Injector.getInstance().releaseViewModelScope();
    }

    @Override
    public void registerRxBus() {
    }

    @Override
    public void removeRxBus() {
    }
}
