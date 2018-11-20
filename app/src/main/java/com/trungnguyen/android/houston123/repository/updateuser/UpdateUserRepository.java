package com.trungnguyen.android.houston123.repository.updateuser;


import android.text.TextUtils;

import com.trungnguyen.android.houston123.anotation.UserType;
import com.trungnguyen.android.houston123.base.BaseModel;
import com.trungnguyen.android.houston123.data.BaseResponse;
import com.trungnguyen.android.houston123.data.EmptyResponse;
import com.trungnguyen.android.houston123.repository.IDataFactory;
import com.trungnguyen.android.houston123.repository.userlist.UserListStore;
import com.trungnguyen.android.houston123.rx.ObservablePattern;
import com.trungnguyen.android.houston123.ui.userdetail.detailmodel.ClassModel;
import com.trungnguyen.android.houston123.ui.userdetail.detailmodel.LecturerModel;
import com.trungnguyen.android.houston123.ui.userdetail.detailmodel.ManagerModel;
import com.trungnguyen.android.houston123.ui.userdetail.detailmodel.SubjectModel;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import timber.log.Timber;

/**
 * Created by trungnd4 on 16/09/2018.
 */
public class UpdateUserRepository implements UpdateUserStore.Repository {

    private UpdateUserStore.RequestService mRequestService;
    private UserListStore.LocalStorage mLocalStorage;
    private UpdateUserFactory mDataFactory;

    @Inject
    public UpdateUserRepository(UpdateUserStore.RequestService requestService,
                                UserListStore.LocalStorage localStorage,
                                @Named("updateUserFactory")
                                        IDataFactory dataFactory) {
        this.mRequestService = requestService;
        this.mLocalStorage = localStorage;
        this.mDataFactory = (UpdateUserFactory) dataFactory;
    }

//    @Override
//    public Observable<List<ClassModel>> callApiClassOfLecturer(String id) {
//        return mRequestService.getClassOfLecturer(id)
//                .flatMap(ObservablePattern::responseProcessingPattern)
//                .doOnNext(classResponseListBaseResponse -> mLocalStorage.putHasLoader(!TextUtils.isEmpty(classResponseListBaseResponse.getNextPageUrl())))
//                .doOnError(throwable -> Timber.d("Falied to load %s", throwable.getMessage()))
//                .flatMap(listResponse -> Observable.just(listResponse.getDataList()))
//                .flatMapIterable(data -> data)
//                .map(ClassResponse::convertToModel)
//                .toList()
//                .toObservable();
//
//
//    }

    @Override
    public Observable<List<BaseModel>> handleUpdateRepositoryMainFlow(int code, String id) {
        return mDataFactory.handleUpdateRepositoryFlow(code, id)
                .flatMap(ObservablePattern::responseProcessingPattern)
                .doOnNext(classResponseListBaseResponse -> mLocalStorage.putHasLoader(!TextUtils.isEmpty(classResponseListBaseResponse.getNextPageUrl())))
                .doOnError(throwable -> Timber.d("Falied to load %s", throwable.getMessage()))
                .flatMap(listResponse -> Observable.just(listResponse.getDataList()))
                .flatMapIterable(data -> data)
                .map(EmptyResponse::convertToModel)
                .toList()
                .toObservable();
    }

//    @Override
//    public Observable<List<StudentShortModel>> callApiStudentInClass(String id) {
//        return mRequestService.getStudentInClass(id)
//                .flatMap(ObservablePattern::responseProcessingPattern)
//                .doOnNext(classResponseListBaseResponse -> mLocalStorage.putHasLoader(!TextUtils.isEmpty(classResponseListBaseResponse.getNextPageUrl())))
//                .doOnError(throwable -> Timber.d("Falied to load %s", throwable.getMessage()))
//                .flatMap(listResponse -> Observable.just(listResponse.getDataList()))
//                .flatMapIterable(data -> data)
//                .map(StudentShortResponse::convertToModel)
//                .toList()
//                .toObservable();
//    }

//    @Override
//    public Observable<List<ClassModel>> clazzIsLearningSubject(String id) {
//        return mRequestService.getListClazzLearningSubject(id)
//                .flatMap(ObservablePattern::responseProcessingPattern)
//                .doOnNext(classResponseListBaseResponse -> mLocalStorage.putHasLoader(!TextUtils.isEmpty(classResponseListBaseResponse.getNextPageUrl())))
//                .doOnError(throwable -> Timber.d("Falied to load %s", throwable.getMessage()))
//                .flatMap(listResponse -> Observable.just(listResponse.getDataList()))
//                .flatMapIterable(data -> data)
//                .map(ClassResponse::convertToModel)
//                .toList()
//                .toObservable();
//    }


    @Override
    public Observable<BaseResponse> callApiUpdateUser(int code, BaseModel model) {
        switch (code) {
            case UserType.MANAGER:
                return handleUpdateManager(model);
            case UserType.LECTURER:
                return handleUpdateLecturer(model);
            case UserType.SUBJECT:
                return handleUpdateSubject(model);
            case UserType.CLAZZ:
                return handleUpdateClazz(model);
            default:
                throw new IllegalStateException("User code not contains in 'UserType' defined");
        }
    }

    private Observable<BaseResponse> handleUpdateLecturer(BaseModel model) {
        LecturerModel managerModel = (LecturerModel) model;
        String name = managerModel.getName();
        String phone = managerModel.getPhoneNumber();
        String address = managerModel.getAddress();
        String lecturerId = managerModel.getCmnd();
        String email = managerModel.getEmail();
        String cmnd = managerModel.getCmnd();
        String img = managerModel.getImg();
        String outDate = managerModel.getOutDate();
        String outReason = managerModel.getOutReason();
        String department = managerModel.getDepartment();
        String permission = managerModel.getPermission();
        return mRequestService.updateLecturer(name, phone, address, lecturerId, email, cmnd, img, outDate, outReason, department, permission, outDate, outReason)
                .flatMap(ObservablePattern::responseProcessingPattern);
    }

    private Observable<BaseResponse> handleUpdateClazz(BaseModel model) {
        ClassModel managerModel = (ClassModel) model;
        String name = managerModel.getMainContent();
        String phone = managerModel.getSubCotent();
        String address = managerModel.lecturerId;
        String lecturerId = managerModel.startDate;
        String email = managerModel.endDate;
        String cmnd = managerModel.departmen;
        return mRequestService.updateClazz(model.getModelId(), name, phone, address, lecturerId, email, cmnd)
                .flatMap(ObservablePattern::responseProcessingPattern);
    }


    private Observable<BaseResponse> handleUpdateSubject(BaseModel model) {
        SubjectModel managerModel = (SubjectModel) model;
        String name = managerModel.getMainContent();
        String phone = managerModel.getManagerAllow();
        return mRequestService.updateSubject(model.getModelId(), name, phone)
                .flatMap(ObservablePattern::responseProcessingPattern);
    }

    private Observable<BaseResponse> handleUpdateManager(BaseModel model) {
        ManagerModel managerModel = (ManagerModel) model;
        String name = managerModel.getName();
        String phone = managerModel.getPhoneNumber();
        String address = managerModel.getAddress();
        String lecturerId = managerModel.getCmnd();
        String email = managerModel.getEmail();
        String cmnd = managerModel.getCmnd();
        String img = managerModel.getImg();
        String outDate = managerModel.getOutDate();
        String outReason = managerModel.getOutReason();
        String department = managerModel.getDepartment();
        String position = managerModel.getPosition();
        return mRequestService.updateManager(name, phone, address, lecturerId, email, cmnd, img, outDate, outReason, department, position, outDate, outReason)
                .flatMap(ObservablePattern::responseProcessingPattern);
    }
}
