package com.trungnguyen.android.houston123.data;

import com.google.gson.annotations.SerializedName;
import com.trungnguyen.android.houston123.ui.userdetail.detailmodel.StudentShortModel;

/**
 * Created by trungnd4 on 09/11/2018.
 */
public class StudentShortResponse extends EmptyResponse {

    @SerializedName("ID")
    String userId;

    @SerializedName("User ID")
    String studentId;

    @SerializedName("Mã Lớp")
    String clazzId;

    @SerializedName("Mã Lớp Chuyển")
    String transClazz;

    @SerializedName("Thời Gian Chuyển")
    String transTime;

    @Override
    public StudentShortModel convertToModel() {
        return new StudentShortModel(userId, studentId, clazzId, transClazz, transTime);
    }
}
