package com.trungnguyen.android.houston123.repository.userlist;

import android.text.TextUtils;

import com.trungnguyen.android.houston123.base.BaseModel;
import com.trungnguyen.android.houston123.data.BaseResponse;
import com.trungnguyen.android.houston123.data.EmptyResponse;
import com.trungnguyen.android.houston123.data.ListBaseResponse;
import com.trungnguyen.android.houston123.exception.HttpEmptyResponseException;
import com.trungnguyen.android.houston123.repository.IDataFactory;
import com.trungnguyen.android.houston123.rx.ObservablePattern;
import com.trungnguyen.android.houston123.util.Constants;
import com.trungnguyen.android.houston123.util.Lists;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import timber.log.Timber;

/**
 * Created by trungnd4 on 07/10/2018.
 */
public class UserListRepository implements UserListStore.Repository {

    private UserListStore.RequestService mRequestService;
    private UserListStore.LocalStorage mLocalStorage;
    private IDataFactory mDataFactory;

    @Inject
    public UserListRepository(UserListStore.RequestService requestService,
                              UserListStore.LocalStorage localStorage,
                              IDataFactory dataFactory) {
        this.mRequestService = requestService;
        this.mLocalStorage = localStorage;
        this.mDataFactory = dataFactory;
    }

    private Observable<List<BaseModel>> processUserFlow(Observable<? extends ListBaseResponse<? extends EmptyResponse>> observable) {
        return observable
                .flatMap(ObservablePattern::responseProcessingPattern)
                .doOnNext(responseListBaseResponse -> {
                    if (responseListBaseResponse == null) {
                        Timber.d("[UserList] List of response User api is NULL");
                        return;
                    }
                    String url = responseListBaseResponse.getNextPageUrl();
                    String nextPageUrl = url == null ? Constants.EMPTY : url;
                    mLocalStorage.putCurrentListPageLocal(responseListBaseResponse.getPage());
                    mLocalStorage.putHasLoader(!TextUtils.isEmpty(nextPageUrl));
                })
                .flatMap(responseListBaseResponse -> Observable.just(responseListBaseResponse.getDataList()))
                .filter(dataList -> !Lists.isEmptyOrNull(dataList))
                .flatMapIterable(responses -> responses)
                .map(EmptyResponse::convertToModel)
                .toList()
                .toObservable();
    }

    @Override
    public Observable<BaseResponse> callApiDeleteUser(String userType, String userId) {
        return mRequestService.deleteUser(userType, userId)
                .doOnNext(baseResponse -> Timber.d("[DeleteManager] Success to delete record manager"))
                .flatMap(ObservablePattern::responseProcessingPattern);
    }

    @Override
    public <R extends EmptyResponse> Observable<? extends Collection<? extends BaseModel>> handleUserServiceFlow(int code, int page) {
        if (page == Constants.LOADING_MORE_ERROR) {
            return Observable.just(new ArrayList<>());
        }

        Observable<? extends ListBaseResponse<? extends EmptyResponse>> dataListFlow = mDataFactory.getUserFlow(code, page);

        return processUserFlow(dataListFlow);
    }

    @Override
    public Observable<BaseResponse> handleRemoveUserFlow(int code, final String userId) {
        String userType = mDataFactory.getDataType(code);
        if (code == Constants.DEFAULT_CODE_VALUE || TextUtils.isEmpty(userType) || TextUtils.isEmpty(userId)) {
            return Observable.error(new HttpEmptyResponseException());
        }
        return this.callApiDeleteUser(userType, userId);
    }

    @Override
    public boolean getHasLoader() {
        return mLocalStorage.getHasLoader();
    }

    @Override
    public Observable<Integer> getPageFromLocal() {
        return Observable.just(mLocalStorage.getCurrentPage());
    }

    @Override
    public Observable<Integer> handleLocalPageData() {
        return this.getPageFromLocal()
                .map(integer -> ++integer)
                .map(integer -> {
                    if (!this.getHasLoader()) {
                        return Constants.LOADING_MORE_ERROR;
                    }
                    return integer;
                })
                .firstOrError()
                .toObservable();
    }
}
