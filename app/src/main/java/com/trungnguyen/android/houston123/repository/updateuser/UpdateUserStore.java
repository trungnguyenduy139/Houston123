package com.trungnguyen.android.houston123.repository.updateuser;

import com.trungnguyen.android.houston123.base.BaseModel;
import com.trungnguyen.android.houston123.data.BaseResponse;
import com.trungnguyen.android.houston123.data.ClassResponse;
import com.trungnguyen.android.houston123.data.ListBaseResponse;
import com.trungnguyen.android.houston123.data.StudentShortResponse;
import com.trungnguyen.android.houston123.util.Constants;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by trungnd4 on 16/09/2018.
 */
public class UpdateUserStore {

    public interface RequestService {
        @GET(Constants.Api.CLAZZ + "/{lecturer_id}")
        Observable<ListBaseResponse<ClassResponse>> getClassOfLecturer(@Path(value = "lecturer_id") String lecturerId);

        @GET(Constants.Api.DETAIL_CLASS + "/{class_id}")
        Observable<ListBaseResponse<StudentShortResponse>> getStudentInClass(@Path(value = "class_id") String lecturerId);

        @PUT(Constants.Api.MANAGER + "/{user_id}")
        Observable<BaseResponse> updateManager(@Path(value = "user_id") String userId,
                                               @Query("hovaten") String name,
                                               @Query("hinhanh") String img,
                                               @Query("permission") String permission,
                                               @Query("available") String available,
                                               @Query("sdt") String sdt,
                                               @Query("diachi") String address,
                                               @Query("email") String email,
                                               @Query("cmnd") String cmnd,
                                               @Query("chucvu") String position,
                                               @Query("coso") String department,
                                               @Query("ngaynghi") String out,
                                               @Query("lydonghi") String reason);

        @PUT(Constants.Api.LECTURER + "/{user_id}")
        Observable<BaseResponse> updateLecturer(@Path(value = "user_id") String userId,
                                                @Query("hovaten") String name,
                                                @Query("hinhanh") String img,
                                                @Query("permission") String permission,
                                                @Query("available") String available,
                                                @Query("sdt") String sdt,
                                                @Query("diachi") String address,
                                                @Query("email") String email,
                                                @Query("cmnd") String cmnd,
                                                @Query("chucvu") String position,
                                                @Query("coso") String department,
                                                @Query("ngaynghi") String out,
                                                @Query("lydonghi") String reason);

        @POST(Constants.Api.DETAIL_CLASS)
        Observable<BaseResponse> updateDetailClass(
                @Query("mahocvien") String name,
                @Query("malop") String img,
                @Query("malopchuyen") String permission,
                @Query("thoigianchuyen") String available);

        @PUT(Constants.Api.LECTURER + "/{user_id}")
        Observable<BaseResponse> updateClazz(@Path(value = "user_id") String userId,
                                             @Query("lop") String name,
                                             @Query("mamonhoc") String img,
                                             @Query("magiaovien") String permission,
                                             @Query("batdau") String available,
                                             @Query("ketthuc") String sdt,
                                             @Query("coso") String address);

        @PUT(Constants.Api.LECTURER + "/{user_id}")
        Observable<BaseResponse> updateSubject(@Path(value = "user_id") String userId,
                                               @Query("ten") String name,
                                               @Query("bophanquanly") String img);

        @GET(Constants.Api.CLAZZ + "/{subject_id}")
        Observable<ListBaseResponse<ClassResponse>> getListClazzLearningSubject(@Path(value = "subject_id") String subjectIdz);


    }

    public interface Repository {
        //        Observable<List<ClassModel>> callApiClassOfLecturer(String id);
//        Observable<List<StudentShortModel>> callApiStudentInClass(String id);
//        Observable<List<ClassModel>> clazzIsLearningSubject(String id);
        Observable<BaseResponse> callApiUpdateUser(int code, BaseModel model);

        Observable<List<BaseModel>> handleUpdateRepositoryMainFlow(int code, String id);

        Observable<BaseResponse> callApiAddLecturerToClazz();
    }

    public interface LocalStorage {

    }
}
