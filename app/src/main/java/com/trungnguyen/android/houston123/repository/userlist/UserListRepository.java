package com.trungnguyen.android.houston123.repository.userlist;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.trungnguyen.android.houston123.data.LecturerResponse;
import com.trungnguyen.android.houston123.ui.userdetail.detailmodel.LecturerModel;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import io.reactivex.Observable;

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
                .flatMap(lecturerResponseListBaseResponse -> Observable.just(lecturerResponseListBaseResponse.getDataList()))
                .filter(Objects::nonNull)
                .flatMapIterable(lecturerResponses -> lecturerResponses)
                .filter(Objects::nonNull)
                .map(LecturerResponse::convertToModel)
                .toList()
                .toObservable();
    }

    private void testDiscordNotify() {
        /**
         * Create new method on new branch for Discord notify testing
         */
    }
}
