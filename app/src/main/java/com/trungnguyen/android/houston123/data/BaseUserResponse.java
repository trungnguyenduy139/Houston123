package com.trungnguyen.android.houston123.data;

import com.google.gson.annotations.SerializedName;
import com.trungnguyen.android.houston123.base.BaseUserModel;

/**
 * Created by trungnd4 on 07/10/2018.
 */
public abstract class BaseUserResponse extends EmptyResponse {
    @SerializedName("Họ Và Tên")
    String userName;
    @SerializedName("Số Điện Thoại")
    String phoneNumber;
    @SerializedName("Địa Chỉ")
    String address;

}
