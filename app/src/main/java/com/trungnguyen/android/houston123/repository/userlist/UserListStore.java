package com.trungnguyen.android.houston123.repository.userlist;

import com.trungnguyen.android.houston123.data.LecturerResponse;
import com.trungnguyen.android.houston123.data.ListBaseResponse;
import com.trungnguyen.android.houston123.ui.userdetail.detailmodel.LecturerModel;
import com.trungnguyen.android.houston123.util.Constants;

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
    }

    public interface Repository {
        Observable<List<LecturerModel>> handleLecturerService();
    }
}
