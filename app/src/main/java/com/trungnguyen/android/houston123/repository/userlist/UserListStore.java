package com.trungnguyen.android.houston123.repository.userlist;

import com.trungnguyen.android.houston123.base.BaseModel;
import com.trungnguyen.android.houston123.data.BaseResponse;
import com.trungnguyen.android.houston123.data.ClassResponse;
import com.trungnguyen.android.houston123.data.EmptyResponse;
import com.trungnguyen.android.houston123.data.LecturerResponse;
import com.trungnguyen.android.houston123.data.ListBaseResponse;
import com.trungnguyen.android.houston123.data.ManagerResponse;
import com.trungnguyen.android.houston123.data.StudentResponse;
import com.trungnguyen.android.houston123.util.Constants;

import java.util.Collection;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by trungnd4 on 07/10/2018.
 */
public class UserListStore {
    public interface RequestService {
        @GET(Constants.Api.LECTURER)
        Observable<ListBaseResponse<LecturerResponse>> getListLecturer(@Query("page") int page);

        @GET(Constants.Api.STUDENT)
        Observable<ListBaseResponse<StudentResponse>> getListStudents(@Query("page") int page);

        @GET(Constants.Api.MANAGER)
        Observable<ListBaseResponse<ManagerResponse>> getListManager(@Query("page") int page);

        @Headers({
                "Content-Type: application/json",
                "X-Requested-With: XMLHttpRequest",
        })
        @DELETE("{user_type}/{user_id}")
        Observable<BaseResponse> deleteUser(@Path(value = "user_type", encoded = true) String userType,
                                            @Path(value = "user_id", encoded = true) String userId);


        @GET(Constants.Api.CLAZZ)
        Observable<ListBaseResponse<ClassResponse>> getLisClazz(@Query("page") int page);
    }

    public interface Repository {

        Observable<List<BaseModel>> handleUserService(int page, int api);

        Observable<BaseResponse> callApiDeleteUser(String userType, String userId);

        <R extends EmptyResponse> Observable<? extends Collection<? extends BaseModel>> handleUserServiceFlow(int code, int page);

        Observable<Integer> getPageFromLocal();

        Observable<Integer> handleLocalPageData();

        Observable<BaseResponse> handleRemoveUserFlow(int code, final String userId);

        boolean getHasLoader();

    }

    public interface LocalStorage {
        void putCurrentListPageLocal(int page);

        int getCurrentPage();

        void putHasLoader(boolean empty);

        boolean getHasLoader();
    }
}
