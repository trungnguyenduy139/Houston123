package com.trungnguyen.android.houston123.base;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;

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

    protected void showLoading() {
        if (mView instanceof BaseFragment) {
            ((BaseFragment) mView).showLoadingDialog();
        } else if (mView instanceof BaseActivity) {
            BaseActivity baseActivity = (BaseActivity) mView;
            if (baseActivity.isFinishing()) {
                return;
            }
            baseActivity.showLoadingDialog();
        }
    }

    protected void hideLoading() {
        if (mView instanceof BaseFragment) {
            ((BaseFragment) mView).hideLoadingDialog();
        } else if (mView instanceof BaseActivity) {
            BaseActivity baseActivity = (BaseActivity) mView;
            if (baseActivity.isFinishing()) {
                return;
            }
            baseActivity.hideLoadingDialog();
        }
    }

    protected void showFailedActionDialog() {
        if (mView instanceof BaseFragment) {
            ((BaseFragment) mView).showFailedActionDialog();
        } else if (mView instanceof BaseActivity) {
            BaseActivity baseActivity = (BaseActivity) mView;
            if (baseActivity.isFinishing()) {
                return;
            }
            baseActivity.showFailedActionDialog();
        }
    }

    @Override
    public void registerRxBus() {
    }

    @Override
    public void removeRxBus() {
    }
}
