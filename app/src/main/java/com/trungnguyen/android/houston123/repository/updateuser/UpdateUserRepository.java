package com.trungnguyen.android.houston123.repository.updateuser;


import android.text.TextUtils;

import com.trungnguyen.android.houston123.data.ClassResponse;
import com.trungnguyen.android.houston123.exception.BodyException;
import com.trungnguyen.android.houston123.exception.HttpEmptyResponseException;
import com.trungnguyen.android.houston123.repository.userlist.UserListStore;
import com.trungnguyen.android.houston123.ui.userdetail.detailmodel.ClassModel;
import com.trungnguyen.android.houston123.util.Constants;

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
                .flatMap(classResponseDataResponse -> {
                    if (classResponseDataResponse == null) {
                        return Observable.error(new HttpEmptyResponseException());
                    }
                    String returnCode = classResponseDataResponse.getReturncode();
                    if (returnCode.equals(Constants.ServerCode.SUCCESS)) {
                        return Observable.just(classResponseDataResponse.getListBaseResponse());
                    }
                    return Observable.error(new BodyException(returnCode, ""));
                })
                .doOnNext(classResponseListBaseResponse -> mLocalStorage.putHasLoader(!TextUtils.isEmpty(classResponseListBaseResponse.getNextPageUrl())))
                .doOnError(throwable -> Timber.d("Falied to load %s", throwable.getMessage()))
                .flatMap(listResponse -> Observable.just(listResponse.getDataList()))
                .flatMapIterable(data -> data)
                .map(ClassResponse::convertToModel)
                .toList()
                .toObservable();
    }
}
