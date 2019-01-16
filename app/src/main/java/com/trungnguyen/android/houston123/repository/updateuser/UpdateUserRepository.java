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
import com.trungnguyen.android.houston123.util.Lists;

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

    @Override
    public Observable<List<BaseModel>> handleNonLecturerClass(String modelId) {
        return mRequestService.getListNoneLecturer(modelId)
                .flatMap(ObservablePattern::responseProcessingPattern)
                .flatMap(responseListBaseResponse -> Observable.just(responseListBaseResponse.getDataList()))
                .filter(dataList -> !Lists.isEmptyOrNull(dataList))
                .flatMapIterable(responses -> responses)
                .map(EmptyResponse::convertToModel)
                .toList()
                .toObservable();
    }


    @Override
    public Observable<BaseResponse> callApiUpdateUser(int code, BaseModel model, String modelId, boolean isUpdate) {
        mId = model.getModelId();
        switch (code) {
            case UserType.MANAGER:
                return handleUpdateManager(model);
            case UserType.LECTURER:
                return handleUpdateLecturer(model);
            case UserType.SUBJECT:
                return handleUpdateSubject(model, isUpdate);
            case UserType.CLAZZ:
                return handleUpdateClazz(model, isUpdate);
            case UserType.DETAIL_CLAZZ:
                return handleUpdateDetailClazz(model);
            case UserType.STUDENT:
                return handleUpdateStudent(model, isUpdate);
            default:
                return Observable.empty();
        }
    }

    private Observable<BaseResponse> handleUpdateStudent(BaseModel model, boolean isUpdate) {
        if (!(model instanceof StudentModel)) {
            return Observable.empty();
        }
        StudentModel studentModel = (StudentModel) model;
        String name = studentModel.getSafeObject(studentModel.getMainContent());
        String img = "";
        String clazz = studentModel.getSafeObject(studentModel.getClazz());
        String phone = studentModel.getSafeObject(studentModel.getSubCotent());
        String address = studentModel.getSafeObject(studentModel.getAddress());
        String birthday = studentModel.getSafeObject(studentModel.getBirthday());
        String hocLucDauVao = studentModel.getSafeObject(studentModel.getIncome());
        String date = studentModel.getSafeObject(studentModel.getStartDate());
        String school = studentModel.getSafeObject(studentModel.getSchool());
        String depart = studentModel.getSafeObject(studentModel.getDepartmentName());
        String nameNT1 = studentModel.getSafeObject(studentModel.getNameNT1());
        String phoneNT1 = studentModel.getSafeObject(studentModel.getPhoneNT1());
        String carrerNT1 = studentModel.getSafeObject(studentModel.getCarrerNT1());
        String nameNT2 = studentModel.getSafeObject(studentModel.getNameNT2());
        String phoneNT2 = studentModel.getSafeObject(studentModel.getPhoneNT2());
        String carrerNT2 = studentModel.getSafeObject(studentModel.getCarrerNT2());
        String toknow = studentModel.getSafeObject(studentModel.getHowToKnow());
        String official = studentModel.getSafeObject(studentModel.getOfficial());
        String offDate = studentModel.getSafeObject(studentModel.getOutDate());
        String offReason = studentModel.getSafeObject(studentModel.getOutReason());


        return isUpdate ? mRequestService.updateStudent(mId, name, img, clazz, phone, address,
                birthday, hocLucDauVao, date, school, nameNT1, carrerNT1,
                phoneNT1, nameNT2, carrerNT2, phoneNT2, toknow, official, depart, offDate, offReason)
                .flatMap(ObservablePattern::responseProcessingPattern) : mRequestService.createStudent(name, img, clazz, phone, address,
                birthday, hocLucDauVao, date, school, nameNT1, carrerNT1,
                phoneNT1, nameNT2, carrerNT2, phoneNT2, toknow, official, depart)
                .flatMap(ObservablePattern::responseProcessingPattern);
    }

    private Observable<BaseResponse> handleUpdateLecturer(BaseModel model) {
        if (!(model instanceof LecturerModel) || mId.isEmpty()) {
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

    private Observable<BaseResponse> handleUpdateClazz(BaseModel model, boolean isUppdate) {
        if (!(model instanceof ClassModel)) {
            return Observable.empty();
        }
        ClassModel clazzModel = (ClassModel) model;
        String clazz = clazzModel.clazz;
        String subjectId = clazzModel.getSubCotent();
        String lecturerId = clazzModel.lecturerId;
        String start = clazzModel.startDate;
        String end = clazzModel.endDate;
        String departmen = clazzModel.departmen;
        return isUppdate ? mRequestService.updateClazz(mId,  lecturerId, start, end, "", "")
                .flatMap(ObservablePattern::responseProcessingPattern) : mRequestService.createClazzz(clazz, subjectId, lecturerId, start, end, "Dĩ An")
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


    private Observable<BaseResponse> handleUpdateSubject(BaseModel model, boolean isUpdate) {
        if (!(model instanceof SubjectModel)) {
            return Observable.empty();
        }
        SubjectModel subjectModel = (SubjectModel) model;
        String name = subjectModel.getMainContent();
        String id = subjectModel.getSubCotent();
        String manager = subjectModel.getManagerAllow();
        return isUpdate ? mRequestService.updateSubject(mId, name, manager)
                .flatMap(ObservablePattern::responseProcessingPattern) : mRequestService.createSubject(id, name, manager)
                .flatMap(ObservablePattern::responseProcessingPattern);
    }

    private Observable<BaseResponse> handleUpdateManager(BaseModel model) {
        ManagerModel managerModel = (ManagerModel) model;
        String name = managerModel.getName();
        String phone = managerModel.getPhoneNumber();
        String address = managerModel.getAddress();
        String email = managerModel.getEmail();
        String cmnd = managerModel.getCmnd();
        String img = managerModel.getImg();
        String outDate = managerModel.getOutDate();
        String outReason = managerModel.getOutReason();
        String department = managerModel.getDepartment();
        String position = managerModel.getPosition();
        return mRequestService.updateManager(mId, name, phone, address, email, cmnd, img, outDate, outReason, department, position, outDate, outReason)
                .flatMap(ObservablePattern::responseProcessingPattern);
    }
}
