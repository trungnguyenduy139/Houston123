package com.trungnguyen.android.houston123.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by trungnd4 on 04/11/2018.
 */
public class AccountInfoResponse extends BaseResponse {
    @SerializedName("data")
    public List<LoginInfoResponse> loginInfo;
}
