package com.trungnguyen.android.houston123.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by trungnd4 on 23/09/2018.
 */
public class AuthenticateResponse extends BaseResponse {
    @SerializedName("token")
    public String userToken = "";
}
