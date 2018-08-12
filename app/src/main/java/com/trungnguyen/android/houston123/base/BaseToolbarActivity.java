package com.trungnguyen.android.houston123.base;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.view.MenuItem;

public abstract class BaseToolbarActivity<V extends ViewDataBinding, VM extends BaseViewModel>
        extends BaseActivity<V, VM> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            setDisplayHomeEnabled(true);
        }
    }

    public void setDisplayHomeEnabled(boolean enabled) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(enabled);
            getSupportActionBar().setDisplayShowHomeEnabled(enabled);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void setTitle(CharSequence title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    @Override
    public void setTitle(int titleId) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(titleId);
        }
    }
}
