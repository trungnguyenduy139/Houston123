package com.trungnguyen.android.houston123.base;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.app.ActionBar;

public abstract class BaseToolbarActivity<V extends ViewDataBinding, VM extends BaseViewModel>
        extends BaseActivity<V, VM> {

    private ActionBar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            setDisplayHomeEnabled(true);
        }
        toolBar = getSupportActionBar();
    }

    public void setDisplayHomeEnabled(boolean b) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(b);
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        toolBar.setTitle(title);
    }

    @Override
    public void setTitle(int titleId) {
        toolBar.setTitle(titleId);
    }
}
