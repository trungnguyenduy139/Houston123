package com.trungnguyen.android.houston123.data;

import com.google.gson.annotations.SerializedName;
import com.trungnguyen.android.houston123.base.BaseModel;
import com.trungnguyen.android.houston123.ui.userdetail.detailmodel.DetailClassModel;

public class DetailClassResponse extends EmptyResponse {
    @SerializedName("ID")
    String userId;
    @SerializedName("MaLop")
    String clazzId;
    @SerializedName("MaHocVien")
    String MaHocVien;
    @SerializedName("Diem")
    String Diem;
    @SerializedName("DanhGia")
    String DanhGia;
    @SerializedName("Họ Và Tên")
    String name;


    @Override
    public BaseModel convertToModel() {
        return new DetailClassModel(MaHocVien, clazzId, Diem, DanhGia, name, userId);
    }
}
