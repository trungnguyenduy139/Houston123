package com.trungnguyen.android.houston123.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by trungnd4 on 04/11/2018.
 */
public class AccountInfoResponse extends BaseResponse {
    @SerializedName("permission")
    public String permission;

    @SerializedName("fullname")
    public String name;

    @SerializedName("khuvuc")
    public String department;

    @SerializedName("Số Điện Thoại")
    public String phone;

    @SerializedName("Địa Chỉ")
    public String address;


    @SerializedName("Email")
    public String email;

    @SerializedName("Chức Vụ")
    public String position;

}
