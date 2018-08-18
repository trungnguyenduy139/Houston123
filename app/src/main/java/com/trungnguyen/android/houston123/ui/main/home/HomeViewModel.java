package com.trungnguyen.android.houston123.ui.main.home;

import android.content.Context;

import com.trungnguyen.android.houston123.base.BaseViewModel;


/**
 * Created by trungnd4 on 19/08/2018.
 */
public class HomeViewModel extends BaseViewModel {

    private Context mContext;

    public HomeViewModel(Context context) {

        super(context);
        mContext = context;
        getDataManagerComponent().inject(this);
    }
}
