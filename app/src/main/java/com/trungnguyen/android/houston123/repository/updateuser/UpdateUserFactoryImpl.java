package com.trungnguyen.android.houston123.repository.updateuser;

import com.trungnguyen.android.houston123.data.EmptyResponse;
import com.trungnguyen.android.houston123.data.ListBaseResponse;
import com.trungnguyen.android.houston123.util.Constants;

import io.reactivex.Observable;

/**
 * Created by trungnd4 on 20/11/2018.
 */
public class UpdateUserFactoryImpl implements UpdateUserFactory {


    private UpdateUserStore.RequestService mRequestService;

    public UpdateUserFactoryImpl(UpdateUserStore.RequestService requestService) {
        mRequestService = requestService;
    }

    @Override
    public Observable<? extends ListBaseResponse<? extends EmptyResponse>> handleUpdateRepositoryFlow(int code, String id) {
        switch (code) {
            case Constants.UpdateFlowAction.CLAZZ_IS_LEARNING_SUBJECT:
                return mRequestService.getListClazzLearningSubject(id);
            case Constants.UpdateFlowAction.STUDENT_IN_CLAZZ:
                return mRequestService.getStudentInClass(id);
            case Constants.UpdateFlowAction.CLAZZ_OF_LECTURER:
                return mRequestService.getClassOfLecturer(id);
            default:
                return Observable.empty();
        }
    }
}
