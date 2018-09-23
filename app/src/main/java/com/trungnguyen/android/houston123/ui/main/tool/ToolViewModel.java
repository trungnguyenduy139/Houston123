package com.trungnguyen.android.houston123.ui.main.tool;

import android.content.Context;
import android.os.Bundle;

import com.trungnguyen.android.houston123.anotation.OnClick;
import com.trungnguyen.android.houston123.base.BaseViewModel;
import com.trungnguyen.android.houston123.util.Navigator;

import javax.inject.Inject;

/**
 * Created by trungnd4 on 19/08/2018.
 */
public class ToolViewModel extends BaseViewModel {

    private Context mContext;
    private Navigator mNavigator;

    @Inject
    public ToolViewModel(Context context, Navigator navigator) {
        mContext = context;
        this.mNavigator = navigator;
    }

    @OnClick
    public void onClickTmp() {
        mNavigator.startUserListActivity(mContext, new Bundle());
    }
}
