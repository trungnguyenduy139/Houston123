package com.trungnguyen.android.houston123.repository.userlist;

import com.trungnguyen.android.houston123.base.BaseUserModel;
import com.trungnguyen.android.houston123.data.LecturerResponse;
import com.trungnguyen.android.houston123.data.ListBaseResponse;
import com.trungnguyen.android.houston123.data.ManagerResponse;
import com.trungnguyen.android.houston123.data.StudentResponse;
import com.trungnguyen.android.houston123.ui.userdetail.detailmodel.LecturerModel;
import com.trungnguyen.android.houston123.ui.userdetail.detailmodel.ManagerModel;
import com.trungnguyen.android.houston123.ui.userdetail.detailmodel.StudentModel;
import com.trungnguyen.android.houston123.util.Constants;

import java.util.Collection;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by trungnd4 on 07/10/2018.
 */
public class UserListStore {
    public interface RequestService {
        @GET(Constants.Api.LECTURER)
        Observable<ListBaseResponse<LecturerResponse>> getListLecturer();

        @GET(Constants.Api.STUDENT)
        Observable<ListBaseResponse<StudentResponse>> getListStudents();

        @GET(Constants.Api.MANAGER)
        Observable<ListBaseResponse<ManagerResponse>> getListManager();
    }

    public interface Repository {
        Observable<List<LecturerModel>> handleLecturerService();

        Observable<List<StudentModel>> handleStudentService();

        Observable<List<ManagerModel>> handleManagerService();

        Observable<? extends Collection<? extends BaseUserModel>> handleUserServiceFlow(int code);
    }
}
