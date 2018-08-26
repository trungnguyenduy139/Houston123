package com.trungnguyen.android.houston123.ui.main.home;

import android.content.Context;
import android.os.Bundle;

import com.trungnguyen.android.houston123.base.BaseListViewModel;
import com.trungnguyen.android.houston123.rx.DefaultSubscriber;
import com.trungnguyen.android.houston123.rx.SchedulerHelper;
import com.trungnguyen.android.houston123.util.CommonResourceLoader;
import com.trungnguyen.android.houston123.util.Navigator;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;


/**
 * Created by trungnd4 on 19/08/2018.
 */

public class HomeViewModel extends BaseListViewModel<IHomeView, HomeAdapterListener> implements HomeAdapterListener {

    private Context mContext;

    @Inject
    CommonResourceLoader mResourceLoader;

    @Inject
    Navigator mNavigator;

    public HomeViewModel(Context context) {

        super(context);
        mContext = context;
        getDataManagerComponent().inject(this);
    }

    @Override
    public HomeAdapterListener getListener() {
        return this;
    }

    public void loadHomeResource() {
        Disposable disposable = mResourceLoader.getHomeResource(mContext)
                .compose(SchedulerHelper.applySchedulers())
                .doOnNext(homeItems -> mView.onLoadHomeResourcesCompleted(homeItems))
                .subscribeWith(new DefaultSubscriber<>());

        mSubscription.add(disposable);
    }

    @Override
    public void onItemClick(int position) {
        mNavigator.startUserListActivity(mContext, new Bundle());
    }


}
