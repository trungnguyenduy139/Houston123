package com.trungnguyen.android.houston123.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by trungnd4 on 16/10/2018.
 */
public class BaseResponse {
    @SerializedName("returncode")
    public String returncode = "";

    @SerializedName("message")
    public String message = "Yêu cầu thất bại";
}
