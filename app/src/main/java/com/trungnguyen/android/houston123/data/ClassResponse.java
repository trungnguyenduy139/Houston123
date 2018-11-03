package com.trungnguyen.android.houston123.data;

import com.google.gson.annotations.SerializedName;
import com.trungnguyen.android.houston123.ui.userdetail.detailmodel.ClassModel;

/**
 * Created by trungnd4 on 24/10/2018.
 */
public class ClassResponse extends EmptyResponse {
    @SerializedName("Mã Lớp")
    public String clazzId;

    @SerializedName("Lớp")
    public String clazz;

    @SerializedName("Mã Môn Học")
    public String subjectId;

    @SerializedName("name")
    public String clazzName;

    @SerializedName("Mã Giáo Viên")
    public String lecturerId;

    @SerializedName("Họ Và Tên")
    public String lecturerName;

    @SerializedName("Ngày Bắt Đầu")
    public String startDate;

    @SerializedName("Ngày Kết Thúc")
    public String endDate;

    @SerializedName("branch")
    public String branch;

    @SerializedName("Tên Cơ Sở")
    public String departmen;

    @Override
    public ClassModel convertToModel() {
        return new ClassModel(clazzName, subjectId, clazzId, clazz, lecturerId, lecturerName, startDate, endDate, branch, departmen);
    }
}
