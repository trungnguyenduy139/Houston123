package com.trungnguyen.android.houston123.repository.updateuser;

import com.trungnguyen.android.houston123.data.ClassResponse;
import com.trungnguyen.android.houston123.data.ListBaseResponse;
import com.trungnguyen.android.houston123.ui.userdetail.detailmodel.ClassModel;
import com.trungnguyen.android.houston123.util.Constants;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by trungnd4 on 16/09/2018.
 */
public class UpdateUserStore {

    public interface RequestService {
        @GET(Constants.Api.CLAZZ + "/{lecturer_id}")
        Observable<ListBaseResponse<ClassResponse>> getClassOfLecturer(@Path(value = "lecturer_id") String lecturerId);
    }

    public interface Repository {
        Observable<List<ClassModel>> callApiClassOfLecturer(String id);
    }

    public interface LocalStorage {

    }
}
