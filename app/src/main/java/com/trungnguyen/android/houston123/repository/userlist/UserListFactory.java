package com.trungnguyen.android.houston123.repository.userlist;

import com.trungnguyen.android.houston123.anotation.UserType;
import com.trungnguyen.android.houston123.data.EmptyResponse;
import com.trungnguyen.android.houston123.data.ListBaseResponse;
import com.trungnguyen.android.houston123.repository.IDataFactory;
import com.trungnguyen.android.houston123.util.Constants;

import io.reactivex.Observable;

/**
 * Created by trungnd4 on 11/11/2018.
 */
public class UserListFactory implements IDataFactory {

    private UserListStore.RequestService mUserListRequestService;

    public UserListFactory(UserListStore.RequestService requestService) {
        this.mUserListRequestService = requestService;
    }

    @Override
    public String getDataType(int code) {
        switch (code) {
            case UserType.STUDENT:
                return Constants.Api.STUDENT;
            case UserType.MANAGER:
                return Constants.Api.MANAGER;
            case UserType.LECTURER:
                return Constants.Api.LECTURER;
            case UserType.CLAZZ:
                return Constants.Api.CLAZZ;
            case UserType.SUBJECT:
                return Constants.Api.SUBJECT;
            default:
                return Constants.EMPTY;
        }
    }

    @Override
    public Observable<? extends ListBaseResponse<? extends EmptyResponse>> getUserFlow(int api, int page) {
        switch (api) {
            case UserType.STUDENT:
                return mUserListRequestService.getListStudents(page);
            case UserType.MANAGER:
                return mUserListRequestService.getListManager(page);
            case UserType.LECTURER:
                return mUserListRequestService.getListLecturer(page);
            case UserType.CLAZZ:
                return mUserListRequestService.getLisClazz(page);
            case UserType.SUBJECT:
                return mUserListRequestService.getListSubject(page);
            default:
                throw new IllegalStateException("Invalid type to get user list api");
        }
    }
}
