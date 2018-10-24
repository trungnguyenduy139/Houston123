package com.trungnguyen.android.houston123.repository.userlist;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;

import com.trungnguyen.android.houston123.anotation.UserType;
import com.trungnguyen.android.houston123.base.BaseModel;
import com.trungnguyen.android.houston123.data.BaseResponse;
import com.trungnguyen.android.houston123.data.ClassResponse;
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
    public Observable<List<LecturerModel>> handleLecturerService(int page) {
        return mRequestService.getListLecturer(page)
                .filter(Objects::nonNull)
                .flatMap(managerResponseDataResponse -> Observable.just(managerResponseDataResponse.getListBaseResponse()))
                .doOnNext(managerResponseListBaseResponse ->
                        mLocalStorage.putCurrentListPageLocal(managerResponseListBaseResponse.getPage()))
                .flatMap(managerResponseListBaseResponse -> Observable.just(managerResponseListBaseResponse.getDataList()))
                .filter(Objects::nonNull)
                .flatMapIterable(managerResponses -> managerResponses)
                .filter(Objects::nonNull)
                .map(LecturerResponse::convertToModel)
                .toList()
                .toObservable();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public Observable<List<StudentModel>> handleStudentService(int page) {
        return mRequestService.getListStudents(page)
                .filter(Objects::nonNull)
                .flatMap(managerResponseDataResponse -> Observable.just(managerResponseDataResponse.getListBaseResponse()))
                .doOnNext(managerResponseListBaseResponse ->
                        mLocalStorage.putCurrentListPageLocal(managerResponseListBaseResponse.getPage()))
                .flatMap(managerResponseListBaseResponse -> Observable.just(managerResponseListBaseResponse.getDataList()))
                .filter(Objects::nonNull)
                .flatMapIterable(managerResponses -> managerResponses)
                .filter(Objects::nonNull)
                .map(StudentResponse::convertToModel)
                .toList()
                .toObservable();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public Observable<List<ManagerModel>> handleManagerService(int page) {
        return mRequestService.getListManager(page)
                .filter(Objects::nonNull)
                .flatMap(managerResponseDataResponse -> Observable.just(managerResponseDataResponse.getListBaseResponse()))
                .doOnNext(managerResponseListBaseResponse ->
                        mLocalStorage.putCurrentListPageLocal(managerResponseListBaseResponse.getPage()))
                .flatMap(managerResponseListBaseResponse -> Observable.just(managerResponseListBaseResponse.getDataList()))
                .filter(Objects::nonNull)
                .flatMapIterable(managerResponses -> managerResponses)
                .filter(Objects::nonNull)
                .map(ManagerResponse::convertToModel)
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
        switch (code) {
            case UserType.STUDENT:
                return this.handleStudentService(page);
            case UserType.MANAGER:
                return this.handleManagerService(page);
            case UserType.LECTURER:
                return this.handleLecturerService(page);
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
                .doOnNext(managerResponseListBaseResponse ->
                        mLocalStorage.putCurrentListPageLocal(managerResponseListBaseResponse.getPage()))
                .flatMap(managerResponseListBaseResponse -> Observable.just(managerResponseListBaseResponse.getDataList()))
                .filter(Objects::nonNull)
                .flatMapIterable(managerResponses -> managerResponses)
                .filter(Objects::nonNull)
                .map(ClassResponse::convertToModel)
                .toList()
                .toObservable();
    }

    @Override
    public Observable<Integer> getPageFromLocal() {
        return Observable.just(mLocalStorage.getCurrentPage());
    }
}
