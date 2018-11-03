package com.trungnguyen.android.houston123.ui.main.home;

import android.content.Context;
import android.support.annotation.NonNull;

import com.trungnguyen.android.houston123.base.BaseListViewModel;
import com.trungnguyen.android.houston123.repository.userlist.UserListRepository;
import com.trungnguyen.android.houston123.repository.userlist.UserListStore;
import com.trungnguyen.android.houston123.rx.SchedulerHelper;
import com.trungnguyen.android.houston123.util.CommonResourceLoader;
import com.trungnguyen.android.houston123.util.Constants;
import com.trungnguyen.android.houston123.util.Lists;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import timber.log.Timber;


/**
 * Created by trungnd4 on 19/08/2018.
 */

public class HomeViewModel extends BaseListViewModel<IHomeView, HomeAdapterListener> implements HomeAdapterListener {

    private Context mContext;

    private CommonResourceLoader mResourceLoader;

    private UserListStore.Repository mUserListRepository;

    @Inject
    public HomeViewModel(Context context,
                         CommonResourceLoader resourceLoader,
                         UserListRepository userListRepository) {
        super(context);
        mContext = context;
        mResourceLoader = resourceLoader;
        this.mUserListRepository = userListRepository;
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
        processUserFlow(position);
    }

    private void processUserFlow(int code) {
        Disposable subscription = mUserListRepository.handleUserServiceFlow(code, Constants.FIRST_PAGE_REQUEST)
                .compose(SchedulerHelper.applySchedulers())
                .filter(models -> !Lists.isEmptyOrNull(models))
                .doOnSubscribe(disposable -> showLoading())
                .doOnTerminate(this::hideLoading)
                .subscribe(userModels -> {
                    if (mView != null) {
                        mView.successToLoadUsers(code, userModels);
                    }
                }, throwable -> {
                    if (mView != null) {
                        mView.failedToLoadUsers(throwable);
                    }
                });

        mSubscription.add(subscription);
    }
}
