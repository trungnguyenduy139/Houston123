package com.trungnguyen.android.houston123.ui.main.home;

import android.annotation.SuppressLint;
import android.content.Context;

import com.trungnguyen.android.houston123.base.BaseViewModel;
import com.trungnguyen.android.houston123.rx.SchedulerHelper;
import com.trungnguyen.android.houston123.util.CommonResourceLoader;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


/**
 * Created by trungnd4 on 19/08/2018.
 */

public class HomeViewModel extends BaseViewModel {

    private Context mContext;

    @Inject
    CommonResourceLoader mResourceLoader;

    public HomeViewModel(Context context) {

        super(context);
        mContext = context;
        getDataManagerComponent().inject(this);
    }

    @SuppressLint("CheckResult")
    public List<HomeItem> loadHomeResource() {
        List<HomeItem> itemList = new ArrayList<>();
        mResourceLoader.getHomeResource()
                .compose(SchedulerHelper.applySchedulers())
                .doOnNext(itemList::addAll);
        return itemList;
    }
}
