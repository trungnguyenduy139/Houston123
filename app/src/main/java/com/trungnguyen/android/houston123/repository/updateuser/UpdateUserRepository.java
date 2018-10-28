package com.trungnguyen.android.houston123.repository.updateuser;


import com.trungnguyen.android.houston123.data.ClassResponse;
import com.trungnguyen.android.houston123.exception.BodyException;
import com.trungnguyen.android.houston123.exception.HttpEmptyResponseException;
import com.trungnguyen.android.houston123.ui.userdetail.detailmodel.ClassModel;
import com.trungnguyen.android.houston123.util.Constants;
import com.trungnguyen.android.houston123.util.Lists;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import timber.log.Timber;

/**
 * Created by trungnd4 on 16/09/2018.
 */
public class UpdateUserRepository implements UpdateUserStore.Repository {

    private UpdateUserStore.RequestService mRequestService;

    @Inject
    public UpdateUserRepository(UpdateUserStore.RequestService requestService) {
        this.mRequestService = requestService;
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
                .doOnError(throwable -> Timber.d("Falied to load %s", throwable.getMessage()))
                .flatMap(listResponse -> Observable.just(listResponse.getDataList()))
                .flatMapIterable(data -> data)
                .map(ClassResponse::convertToModel)
                .toList()
                .toObservable();
    }
}
