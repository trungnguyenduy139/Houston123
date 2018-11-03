package com.trungnguyen.android.houston123.repository.userlist;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;

import com.trungnguyen.android.houston123.anotation.UserType;
import com.trungnguyen.android.houston123.base.BaseModel;
import com.trungnguyen.android.houston123.data.BaseResponse;
import com.trungnguyen.android.houston123.data.DataResponse;
import com.trungnguyen.android.houston123.data.EmptyResponse;
import com.trungnguyen.android.houston123.exception.BodyException;
import com.trungnguyen.android.houston123.exception.HttpEmptyResponseException;
import com.trungnguyen.android.houston123.ui.userdetail.detailmodel.ManagerModel;
import com.trungnguyen.android.houston123.util.Constants;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import io.reactivex.Observable;
import timber.log.Timber;

/**
 * Created by trungnd4 on 07/10/2018.
 */
public class UserListRepository implements UserListStore.Repository {

    private UserListStore.RequestService mRequestService;
    private UserListStore.LocalStorage mLocalStorage;

    @Inject
    public UserListRepository(UserListStore.RequestService requestService,
                              UserListStore.LocalStorage localStorage) {
        this.mRequestService = requestService;
        this.mLocalStorage = localStorage;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private <R extends EmptyResponse> Observable<List<BaseModel>> processUserFlow(Observable<DataResponse<R>> observable) {
        return observable
                .filter(Objects::nonNull)
                .flatMap(baseUserResponseDataResponse -> {
                    if (baseUserResponseDataResponse == null) {
                        return Observable.error(HttpEmptyResponseException::new);
                    }
                    if (baseUserResponseDataResponse.getReturncode().equals(Constants.ServerCode.SUCCESS)) {
                        return Observable.just(baseUserResponseDataResponse.getListBaseResponse());
                    }
                    return Observable.error(new BodyException(Constants.ServerCode.FAILED, Constants.EMPTY));
                })
                .flatMap(responseListBaseResponse -> {
                    if (responseListBaseResponse == null) {
                        return Observable.error(HttpEmptyResponseException::new);
                    }
                    return Observable.just(responseListBaseResponse);
                })
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
                .filter(Objects::nonNull)
                .flatMapIterable(responses -> responses)
                .filter(Objects::nonNull)
                .map(EmptyResponse::convertToModel)
                .toList()
                .toObservable();
    }

    @Override
    public Observable<BaseResponse> callApiDeleteUser(String userType, String userId) {
        return mRequestService.deleteUser(userType, userId)
                .doOnNext(baseResponse -> Timber.d("[DeleteManager] Success to delete record manager"))
                .flatMap(baseResponse -> {
                    if (baseResponse == null) {
                        return Observable.error(new HttpEmptyResponseException());
                    }
                    if (TextUtils.equals(baseResponse.message, Constants.ServerCode.DELETE_SUCCESS_MESSAGE)) {
                        return Observable.just(baseResponse);
                    }
                    return Observable.error(new BodyException(baseResponse.returncode, baseResponse.message));
                });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public <R extends EmptyResponse> Observable<? extends Collection<? extends BaseModel>> handleUserServiceFlow(int code, int page) {
        if (page == Constants.LOADING_MORE_ERROR) {
            return Observable.just(new ArrayList<>());
        }
        String userType = apiChooser(code);
        Observable<DataResponse<R>> userObservable = mRequestService.getListOfUser(page, userType);
        return processUserFlow(userObservable);
    }

    @Override
    public Observable<BaseResponse> handleRemoveUserFlow(int code, final String userId) {
        String userType = apiChooser(code);
        if (code == Constants.DEFAULT_CODE_VALUE || TextUtils.isEmpty(userType) || TextUtils.isEmpty(userId)) {
            return Observable.error(new HttpEmptyResponseException());
        }
        return this.callApiDeleteUser(userType, userId);
    }

    private String apiChooser(int code) {
        String userType = Constants.EMPTY;
        switch (code) {
            case UserType.STUDENT:
                userType = Constants.Api.STUDENT;
                break;
            case UserType.MANAGER:
                userType = Constants.Api.MANAGER;
                break;
            case UserType.LECTURER:
                userType = Constants.Api.LECTURER;
                break;
            default:
                break;
        }
        return userType;
    }

    @Override
    public Observable<BaseResponse> callApiUpdateManager(ManagerModel managerModel) {
        return Observable.just(new BaseResponse());
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
