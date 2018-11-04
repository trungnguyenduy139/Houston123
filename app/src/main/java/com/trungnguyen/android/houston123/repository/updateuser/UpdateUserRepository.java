package com.trungnguyen.android.houston123.repository.updateuser;


import android.text.TextUtils;

import com.trungnguyen.android.houston123.base.BaseModel;
import com.trungnguyen.android.houston123.data.BaseResponse;
import com.trungnguyen.android.houston123.data.ClassResponse;
import com.trungnguyen.android.houston123.repository.userlist.UserListStore;
import com.trungnguyen.android.houston123.rx.ObservablePattern;
import com.trungnguyen.android.houston123.ui.userdetail.detailmodel.ClassModel;
import com.trungnguyen.android.houston123.ui.userdetail.detailmodel.ManagerModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import timber.log.Timber;

/**
 * Created by trungnd4 on 16/09/2018.
 */
public class UpdateUserRepository implements UpdateUserStore.Repository {

    private UpdateUserStore.RequestService mRequestService;
    private UserListStore.LocalStorage mLocalStorage;

    @Inject
    public UpdateUserRepository(UpdateUserStore.RequestService requestService,
                                UserListStore.LocalStorage localStorage) {
        this.mRequestService = requestService;
        this.mLocalStorage = localStorage;
    }

    @Override
    public Observable<List<ClassModel>> callApiClassOfLecturer(String id) {
        return mRequestService.getClassOfLecturer(id)
                .flatMap(ObservablePattern::responseProcessingPattern)
                .doOnNext(classResponseListBaseResponse -> mLocalStorage.putHasLoader(!TextUtils.isEmpty(classResponseListBaseResponse.getNextPageUrl())))
                .doOnError(throwable -> Timber.d("Falied to load %s", throwable.getMessage()))
                .flatMap(listResponse -> Observable.just(listResponse.getDataList()))
                .flatMapIterable(data -> data)
                .map(ClassResponse::convertToModel)
                .toList()
                .toObservable();
    }


    @Override
    public Observable<BaseResponse> callApiUpdateUser(BaseModel model) {
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
