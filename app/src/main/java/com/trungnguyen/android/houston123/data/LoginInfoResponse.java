package com.trungnguyen.android.houston123.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by trungnd4 on 05/11/2018.
 */
public class LoginInfoResponse {
    @SerializedName("permission")
    public String permission;

    @SerializedName("Họ Và Tên")
    public String name;

    @SerializedName("account_id")
    public String accountId;

    @SerializedName("Số Điện Thoại")
    public String phone;

    @SerializedName("Địa Chỉ")
    public String address;

    @SerializedName("CMND")
    public String cmnd;


    @SerializedName("Email")
    public String email;

    @SerializedName("Chức Vụ")
    public String position;
}
