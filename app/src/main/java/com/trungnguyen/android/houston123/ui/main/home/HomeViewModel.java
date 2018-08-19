package com.trungnguyen.android.houston123.ui.main.home;

import android.content.Context;
import com.trungnguyen.android.houston123.base.BaseViewModel;
import com.trungnguyen.android.houston123.rx.DefaultSubscriber;
import com.trungnguyen.android.houston123.rx.SchedulerHelper;
import com.trungnguyen.android.houston123.util.CommonResourceLoader;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;


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

    public List<HomeItem> loadHomeResource() {
        List<HomeItem> itemList = new ArrayList<>();
        Disposable disposable = mResourceLoader.getHomeResource(mContext)
                .compose(SchedulerHelper.applySchedulers())
                .doOnNext(itemList::addAll)
                .subscribeWith(new DefaultSubscriber<>());

        mSubscription.add(disposable);

        return itemList;
    }
}
