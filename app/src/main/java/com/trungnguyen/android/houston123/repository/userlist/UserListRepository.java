package com.trungnguyen.android.houston123.repository.userlist;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;

import com.trungnguyen.android.houston123.anotation.UserType;
import com.trungnguyen.android.houston123.base.BaseModel;
import com.trungnguyen.android.houston123.base.BaseUserModel;
import com.trungnguyen.android.houston123.data.BaseResponse;
import com.trungnguyen.android.houston123.data.BaseUserResponse;
import com.trungnguyen.android.houston123.data.ClassResponse;
import com.trungnguyen.android.houston123.data.DataResponse;
import com.trungnguyen.android.houston123.data.LecturerResponse;
import com.trungnguyen.android.houston123.data.ManagerResponse;
import com.trungnguyen.android.houston123.data.StudentResponse;
import com.trungnguyen.android.houston123.exception.BodyException;
import com.trungnguyen.android.houston123.exception.HttpEmptyResponseException;
import com.trungnguyen.android.houston123.ui.userdetail.detailmodel.ClassModel;
import com.trungnguyen.android.houston123.ui.userdetail.detailmodel.LecturerModel;
import com.trungnguyen.android.houston123.ui.userdetail.detailmodel.ManagerModel;
import com.trungnguyen.android.houston123.ui.userdetail.detailmodel.StudentModel;
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
    @Override
    public Observable<List<BaseUserModel>> handleManagerService(int page, int api) {
        switch (api) {
            case UserType.MANAGER:
                Observable<DataResponse<ManagerResponse>> observableManager = mRequestService.getListManager(page);
                return processUserFlow(observableManager);
            case UserType.LECTURER:
                Observable<DataResponse<LecturerResponse>> observableLecturer = mRequestService.getListLecturer(page);
                return processUserFlow(observableLecturer);
            case UserType.STUDENT:
                Observable<DataResponse<StudentResponse>> observableStudent = mRequestService.getListStudents(page);
                return processUserFlow(observableStudent);
            default:
                return Observable.just(new ArrayList<>());
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private <R extends BaseUserResponse> Observable<List<BaseUserModel>> processUserFlow(Observable<DataResponse<R>> observable) {
        return observable
                .filter(Objects::nonNull)
                .flatMap(baseUserResponseDataResponse -> {
                    if (baseUserResponseDataResponse == null) {
                        return Observable.error(HttpEmptyResponseException::new);
                    }
                    if (baseUserResponseDataResponse.getReturncode().equals(Constants.ServerCode.SUCCESS)) {
                        return Observable.just(baseUserResponseDataResponse.getListBaseResponse());
                    }
                    return Observable.error(new BodyException(Constants.ServerCode.FAILED, ""));
                })
                .flatMap(responseListBaseResponse -> {
                    if (responseListBaseResponse == null) {
                        return Observable.error(HttpEmptyResponseException::new);
                    }
                    return Observable.just(responseListBaseResponse);
                })
                .doOnNext(responseListBaseResponse -> {
                    if (responseListBaseResponse == null) {
                        return;
                    }
                    String url = responseListBaseResponse.getNextPageUrl();
                    String nextPageUrl = url == null ? "" : url;
                    mLocalStorage.putCurrentListPageLocal(responseListBaseResponse.getPage());
                    mLocalStorage.putHasLoader(!TextUtils.isEmpty(nextPageUrl));
                })
                .flatMap(responseListBaseResponse -> Observable.just(responseListBaseResponse.getDataList()))
                .filter(Objects::nonNull)
                .flatMapIterable(responses -> responses)
                .filter(Objects::nonNull)
                .map(BaseUserResponse::convertToModel)
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
    public Observable<? extends Collection<? extends BaseModel>> handleUserServiceFlow(int code, int page) {
        if (page == Constants.LOADING_MORE_ERROR) {
            return Observable.just(new ArrayList<>());
        }
        switch (code) {
            case UserType.STUDENT:
            case UserType.MANAGER:
            case UserType.LECTURER:
                return this.handleManagerService(page, code);
            case UserType.CLAZZ:
                return this.handleClassService(page);
            default:
                return Observable.just(new ArrayList<>());
        }
    }

    @Override
    public Observable<BaseResponse> handleRemoveUserFlow(int code, final String userId) {
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
        if (code == Constants.DEFAULT_CODE_VALUE || TextUtils.isEmpty(userType) || TextUtils.isEmpty(userId)) {
            return Observable.error(new HttpEmptyResponseException());
        }
        return this.callApiDeleteUser(userType, userId);
    }

    @Override
    public Observable<BaseResponse> callApiUpdateManager(ManagerModel managerModel) {
        return Observable.just(new BaseResponse());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public Observable<List<ClassModel>> handleClassService(int page) {
        return mRequestService.getLisClazz(page)
                .filter(Objects::nonNull)
                .flatMap(managerResponseDataResponse -> Observable.just(managerResponseDataResponse.getListBaseResponse()))
                .doOnNext(managerResponseListBaseResponse -> {
                    mLocalStorage.putCurrentListPageLocal(managerResponseListBaseResponse.getPage());
                    mLocalStorage.putHasLoader(!TextUtils.isEmpty(managerResponseListBaseResponse.getNextPageUrl()));
                })
                .flatMap(managerResponseListBaseResponse -> Observable.just(managerResponseListBaseResponse.getDataList()))
                .filter(Objects::nonNull)
                .flatMapIterable(managerResponses -> managerResponses)
                .filter(Objects::nonNull)
                .map(ClassResponse::convertToModel)
                .toList()
                .toObservable();
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
