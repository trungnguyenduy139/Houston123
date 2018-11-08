package com.trungnguyen.android.houston123.data;

import com.google.gson.annotations.SerializedName;
import com.trungnguyen.android.houston123.ui.userdetail.detailmodel.StudentModel;

/**
 * Created by trungnd4 on 08/10/2018.
 */
public class StudentResponse extends BaseUserResponse {

    @SerializedName("User ID")
    String userId;
    @SerializedName("Hình Ảnh")
    String img;
    @SerializedName("Lớp")
    String clazz;
    @SerializedName("Ngày Sinh")
    String birthday;
    @SerializedName("Học Lực Đầu Vào")
    String income;
    @SerializedName("Ngày Nhập Học")
    String startDate;
    @SerializedName("Trường Học Chính Khóa")
    String school;
    @SerializedName("Ngày Nghỉ Học")
    String outDate;
    @SerializedName("Lý Do Nghỉ")
    String outReason;
    @SerializedName("Họ Và Tên (NT1)")
    String nameNT1;
    @SerializedName("Số Điện Thoại (NT1)")
    String phoneNT1;
    @SerializedName("Nghề Nghiệp (NT1)")
    String carrerNT1;
    @SerializedName("Họ Và Tên (NT2)")
    String nameNT2;
    @SerializedName("Số Điện Thoại (NT2)")
    String phoneNT2;
    @SerializedName("Nghề Nghiệp (NT2)")
    String carrerNT2;
    @SerializedName("Biết Houston123 Như Thế Nào")
    String howToKnow;
    @SerializedName("Chính Thức")
    String official;
    @SerializedName("Cơ Sở")
    String department;
    @SerializedName("Tên Cơ Sở")
    String departmentName;

    @Override
    public StudentModel convertToModel() {
        return new StudentModel(userName, phoneNumber, address, userId, clazz, birthday, income, startDate, school, outDate, outReason, nameNT1, phoneNT1, carrerNT1,
                nameNT2, phoneNT2, carrerNT2, howToKnow, official, department, departmentName);
    }
}
