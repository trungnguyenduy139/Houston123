package com.trungnguyen.android.houston123.data;

import com.google.gson.annotations.SerializedName;
import com.trungnguyen.android.houston123.ui.userdetail.detailmodel.LecturerModel;

/**
 * Created by trungnd4 on 07/10/2018.
 */
public class LecturerResponse extends BaseUserResponse {
    @SerializedName("STT")
    String lecutrerStt;
    @SerializedName("Mã Giáo Viên")
    String lecturerId;
    @SerializedName("Hình Ảnh")
    String img;
    @SerializedName("Địa Chỉ")
    String address;
    @SerializedName("Email")
    String email;
    @SerializedName("CMND")
    String cmnd;
    @SerializedName("Ngày Nghĩ")
    String outDate;
    @SerializedName("Lý Do Nghĩ")
    String outReason;
    @SerializedName("Cở Sở")
    String department;

    public LecturerModel convertToModel() {
        return new LecturerModel(userName, phoneNumber, lecutrerStt, lecturerId, img, address, email, cmnd, outDate, outReason, department);
    }
}
