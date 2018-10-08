package com.trungnguyen.android.houston123.repository.userlist;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.trungnguyen.android.houston123.anotation.UserType;
import com.trungnguyen.android.houston123.base.BaseUserModel;
import com.trungnguyen.android.houston123.data.LecturerResponse;
import com.trungnguyen.android.houston123.data.ManagerResponse;
import com.trungnguyen.android.houston123.data.StudentResponse;
import com.trungnguyen.android.houston123.ui.userdetail.detailmodel.LecturerModel;
import com.trungnguyen.android.houston123.ui.userdetail.detailmodel.ManagerModel;
import com.trungnguyen.android.houston123.ui.userdetail.detailmodel.StudentModel;

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

    @Inject
    public UserListRepository(UserListStore.RequestService requestService) {
        this.mRequestService = requestService;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public Observable<List<LecturerModel>> handleLecturerService() {
        return mRequestService.getListLecturer()
                .filter(Objects::nonNull)
                .doOnNext(studentResponseListBaseResponse -> Timber.d("Request service is processing - Lecturer"))
                .flatMap(lecturerResponseListBaseResponse -> Observable.just(lecturerResponseListBaseResponse.getDataList()))
                .filter(Objects::nonNull)
                .flatMapIterable(lecturerResponses -> lecturerResponses)
                .filter(Objects::nonNull)
                .map(LecturerResponse::convertToModel)
                .toList()
                .toObservable();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public Observable<List<StudentModel>> handleStudentService() {
        return mRequestService.getListStudents()
                .filter(Objects::nonNull)
                .doOnNext(studentResponseListBaseResponse -> Timber.d("Request service is processing - Student"))
                .flatMap(studentResponseListBaseResponse -> Observable.just(studentResponseListBaseResponse.getDataList()))
                .filter(Objects::nonNull)
                .flatMapIterable(studentResponses -> studentResponses)
                .filter(Objects::nonNull)
                .map(StudentResponse::convertToModel)
                .toList()
                .toObservable();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public Observable<List<ManagerModel>> handleManagerService() {
        return mRequestService.getListManager()
                .filter(Objects::nonNull)
                .flatMap(managerResponseListBaseResponse -> Observable.just(managerResponseListBaseResponse.getDataList()))
                .filter(Objects::nonNull)
                .flatMapIterable(managerResponses -> managerResponses)
                .filter(Objects::nonNull)
                .map(ManagerResponse::convertToModel)
                .toList()
                .toObservable();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public Observable<? extends Collection<? extends BaseUserModel>> handleUserServiceFlow(int code) {
        switch (code) {
            case UserType.STUDENT:
                return this.handleLecturerService();
            case UserType.MANAGER:
                return this.handleManagerService();
            case UserType.LECTURER:
                return this.handleStudentService();
            default:
                return this.handleLecturerService();
        }
    }
}
