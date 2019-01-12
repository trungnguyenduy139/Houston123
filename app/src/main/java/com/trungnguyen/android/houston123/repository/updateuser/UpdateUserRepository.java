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
import com.trungnguyen.android.houston123.ui.userdetail.detailmodel.DetailClassModel;
import com.trungnguyen.android.houston123.ui.userdetail.detailmodel.LecturerModel;
import com.trungnguyen.android.houston123.ui.userdetail.detailmodel.ManagerModel;
import com.trungnguyen.android.houston123.ui.userdetail.detailmodel.StudentModel;
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
    private String mId = "";

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

    @Override
    public Observable<BaseResponse> callApiAddLecturerToClazz() {
        return null;
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
    public Observable<BaseResponse> callApiUpdateUser(int code, BaseModel model, String modelId) {
        mId = modelId;
        switch (code) {
            case UserType.MANAGER:
                return handleUpdateManager(model);
            case UserType.LECTURER:
                return handleUpdateLecturer(model);
            case UserType.SUBJECT:
                return handleUpdateSubject(model);
            case UserType.CLAZZ:
                return handleUpdateClazz(model);
            case UserType.DETAIL_CLAZZ:
                return handleUpdateDetailClazz(model);
            case UserType.STUDENT:
                return handleUpdateStudent(model);
            default:
                return Observable.empty();
        }
    }

    private Observable<BaseResponse> handleUpdateStudent(BaseModel model) {
        if (!(model instanceof StudentModel)) {
            return Observable.empty();
        }
        StudentModel studentModel = (StudentModel) model;
        String name = studentModel.getName();
        String img = "";
        String clazz = studentModel.getClazz();
        String phone = studentModel.getPhoneNumber();
        String address = studentModel.getAddress();
        String birthday = studentModel.getBirthday();
        String hocLucDauVao = studentModel.getIncome();
        String date = studentModel.getStartDate();
        String school = studentModel.getSchool();
        String depart = studentModel.getDepartment();
        return mRequestService.updateStudent(name, img, clazz, phone, address,
                birthday, hocLucDauVao, date, school, depart, "", "",
                "", "", "", "", "", "", "")
                .flatMap(ObservablePattern::responseProcessingPattern);
    }

    private Observable<BaseResponse> handleUpdateLecturer(BaseModel model) {
        if (!(model instanceof LecturerModel)) {
            return Observable.empty();
        }
        LecturerModel managerModel = (LecturerModel) model;
        String name = managerModel.getName();
        String phone = managerModel.getPhoneNumber();
        String address = managerModel.getAddress();
        String email = managerModel.getEmail();
        String cmnd = managerModel.getCmnd();
        String outDate = managerModel.getOutDate();
        String outReason = managerModel.getOutReason();
        String department = managerModel.getDepartment();
        return mRequestService.updateLecturer(mId, name, "", "giaovien", "available", phone, address, email, cmnd, department, outDate, outReason)
                .flatMap(ObservablePattern::responseProcessingPattern);
    }

    private Observable<BaseResponse> handleUpdateClazz(BaseModel model) {
        if (!(model instanceof ClassModel)) {
            return Observable.empty();
        }
        ClassModel managerModel = (ClassModel) model;
        String name = managerModel.getMainContent();
        String phone = managerModel.getSubCotent();
        String address = managerModel.lecturerId;
        String lecturerId = managerModel.startDate;
        String email = managerModel.endDate;
        String cmnd = managerModel.departmen;
        return mRequestService.updateClazz(mId, name, phone, address, lecturerId, email, cmnd)
                .flatMap(ObservablePattern::responseProcessingPattern);
    }


    private Observable<BaseResponse> handleUpdateDetailClazz(BaseModel model) {
        DetailClassModel detailClassModel = (DetailClassModel) model;
        String studentId = detailClassModel.getStudentId();
        String clazzId = detailClassModel.getClazzId();
        String tranferId = detailClassModel.getTransferId();
        String transferTIme = detailClassModel.getTransferTime();
        return mRequestService.updateDetailClass(mId, clazzId, tranferId, transferTIme)
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
        return mRequestService.updateManager(name, phone, address, mId, email, cmnd, img, outDate, outReason, department, position, outDate, outReason)
                .flatMap(ObservablePattern::responseProcessingPattern);
    }
}
