package com.trungnguyen.android.houston123.ui.main.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.trungnguyen.android.houston123.base.BaseListViewModel;
import com.trungnguyen.android.houston123.rx.SchedulerHelper;
import com.trungnguyen.android.houston123.util.CommonResourceLoader;
import com.trungnguyen.android.houston123.util.Navigator;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import timber.log.Timber;


/**
 * Created by trungnd4 on 19/08/2018.
 */

public class HomeViewModel extends BaseListViewModel<IHomeView, HomeAdapterListener> implements HomeAdapterListener {

    private Context mContext;

    private CommonResourceLoader mResourceLoader;

    private Navigator mNavigator;

    @Inject
    public HomeViewModel(Context context,
                         CommonResourceLoader resourceLoader,
                         Navigator navigator) {
        super(context);
        mContext = context;
        mNavigator = navigator;
        mResourceLoader = resourceLoader;
    }

    @NonNull
    @Override
    public HomeAdapterListener getListener() {
        return this;
    }

    public void loadHomeResource() {
        Disposable disposable = mResourceLoader.getHomeResource(mContext)
                .compose(SchedulerHelper.applySchedulers())
                .subscribe(homeItems -> {
                    if (mView != null) {
                        mView.onLoadHomeResourcesCompleted(homeItems);
                    }
                }, Timber::d);

        mSubscription.add(disposable);
    }

    @Override
    public void onItemClick(int position) {
        mNavigator.startUserListActivity(mContext, new Bundle());
    }
}
