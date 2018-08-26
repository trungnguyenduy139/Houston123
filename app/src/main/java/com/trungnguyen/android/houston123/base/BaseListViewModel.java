package com.trungnguyen.android.houston123.base;

import android.content.Context;
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

    public BaseListViewModel(Fragment fragment) {
        super(fragment);
    }

    public void attachAdapter(BaseInfinityAdapter adapter) {
        adapter.setListener(getListener());
    }

    public abstract Listener getListener();
}
