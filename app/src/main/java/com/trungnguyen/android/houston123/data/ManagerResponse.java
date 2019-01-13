package com.trungnguyen.android.houston123.data;

import com.google.gson.annotations.SerializedName;
import com.trungnguyen.android.houston123.ui.userdetail.detailmodel.ManagerModel;

/**
 * Created by trungnd4 on 08/10/2018.
 */
public class ManagerResponse extends BaseUserResponse {
    @SerializedName("Mã Quản Lý")
    String lecturerId;
    @SerializedName("Hình Ảnh")
    String img;
    @SerializedName("Email")
    String email;
    @SerializedName("CMND")
    String cmnd;
    @SerializedName("Ngày Nghĩ")
    String outDate;
    @SerializedName("Lý Do Nghĩ")
    String outReason;
    @SerializedName("Chức Vụ")
    String position;
    @SerializedName("Cơ Sở")
    String department;
    @SerializedName("permission")
    String permission;
    @SerializedName("Tên Cơ Sở")
    String departmentName;


    public ManagerResponse(String lecturerId, String img, String email, String cmnd, String outDate, String outReason, String position, String department) {
        this.lecturerId = lecturerId;
        this.img = img;
        this.email = email;
        this.cmnd = cmnd;
        this.outDate = outDate;
        this.outReason = outReason;
        this.position = position;
        this.department = department;
    }

    @Override
    public ManagerModel convertToModel() {
        return new ManagerModel(userName, phoneNumber, lecturerId, img, address, email, cmnd, outDate, outReason, department, position, permission, departmentName);
    }
}
