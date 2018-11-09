package com.trungnguyen.android.houston123.repository.userlist;

import android.text.TextUtils;

import com.trungnguyen.android.houston123.anotation.UserType;
import com.trungnguyen.android.houston123.base.BaseModel;
import com.trungnguyen.android.houston123.data.BaseResponse;
import com.trungnguyen.android.houston123.data.ClassResponse;
import com.trungnguyen.android.houston123.data.EmptyResponse;
import com.trungnguyen.android.houston123.data.LecturerResponse;
import com.trungnguyen.android.houston123.data.ListBaseResponse;
import com.trungnguyen.android.houston123.data.ManagerResponse;
import com.trungnguyen.android.houston123.data.StudentResponse;
import com.trungnguyen.android.houston123.data.SubjectResponse;
import com.trungnguyen.android.houston123.exception.HttpEmptyResponseException;
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

    @Inject
    public UserListRepository(UserListStore.RequestService requestService,
                              UserListStore.LocalStorage localStorage) {
        this.mRequestService = requestService;
        this.mLocalStorage = localStorage;
    }

    private <R extends EmptyResponse> Observable<List<BaseModel>> processUserFlow(Observable<ListBaseResponse<R>> observable) {
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
        return handleUserService(page, code);
    }

    @Override
    public Observable<List<BaseModel>> handleUserService(int page, int api) {
        switch (api) {
            case UserType.MANAGER:
                Observable<ListBaseResponse<ManagerResponse>> observableManager = mRequestService.getListManager(page);
                return processUserFlow(observableManager);
            case UserType.LECTURER:
                Observable<ListBaseResponse<LecturerResponse>> observableLecturer = mRequestService.getListLecturer(page);
                return processUserFlow(observableLecturer);
            case UserType.STUDENT:
                Observable<ListBaseResponse<StudentResponse>> observableStudent = mRequestService.getListStudents(page);
                return processUserFlow(observableStudent);
            case UserType.CLAZZ:
                Observable<ListBaseResponse<ClassResponse>> observableClazz = mRequestService.getLisClazz(page);
                return processUserFlow(observableClazz);
            case UserType.SUBJECT:
                Observable<ListBaseResponse<SubjectResponse>> observableSubject = mRequestService.getListSubject(page);
                return processUserFlow(observableSubject);

            default:
                return Observable.just(new ArrayList<>());
        }
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
            case UserType.CLAZZ:
                userType = Constants.Api.CLAZZ;
                break;
            case UserType.SUBJECT:
                userType = Constants.Api.SUBJECT;
                break;
            default:
                break;
        }
        return userType;
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
