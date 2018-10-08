package com.trungnguyen.android.houston123.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by trungnd4 on 07/10/2018.
 */
public class BaseUserResponse {
    @SerializedName("Họ Và Tên")
    String userName;
    @SerializedName("Số Điện Thoại")
    String phoneNumber;
}
