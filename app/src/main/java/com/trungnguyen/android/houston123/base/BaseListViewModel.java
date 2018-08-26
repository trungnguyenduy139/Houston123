package com.trungnguyen.android.houston123.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Created by trungnd4 on 26/08/2018.
 */
public abstract class BaseListViewModel<View, Listener extends IAdapterListener> extends BaseViewModel<View> {

    public BaseListViewModel() {
        super();
    }

    public BaseListViewModel(Context context) {
        super(context);
    }

    public BaseListViewModel(@NonNull Fragment fragment) {
        super(fragment);
    }

    public void attachAdapter(@Nullable BaseInfinityAdapter<Listener> adapter) {
        if (adapter == null) {
            return;
        }
        adapter.setListener(getListener());
    }

    @NonNull
    public abstract Listener getListener();
}
