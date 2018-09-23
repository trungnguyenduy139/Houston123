package com.trungnguyen.android.houston123.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.trungnguyen.android.houston123.anotation.OnClick;
import com.trungnguyen.android.houston123.base.BaseViewModel;
import com.trungnguyen.android.houston123.util.Navigator;

import javax.inject.Inject;

public class MainViewModel extends BaseViewModel<IMainView> {

    private Context mContext;

    private Navigator mNavigator;

    @Inject
    public MainViewModel(Context context, Navigator navigator) {
        super(context);
        mContext = context;
        mNavigator = navigator;
    }

    @OnClick
    public void onTextClick() {
        Toast.makeText(mContext, "Click click boom", Toast.LENGTH_SHORT).show();
        mNavigator.startUserListActivity(mContext, new Bundle());
    }
}
