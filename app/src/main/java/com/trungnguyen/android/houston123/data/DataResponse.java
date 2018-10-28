package com.trungnguyen.android.houston123.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by trungnd4 on 23/10/2018.
 */
public class DataResponse<T extends EmptyResponse> {
    @SerializedName("code")
    String returncode;

    @SerializedName("embeddata")
    ListBaseResponse<T> listBaseResponse;

    public String getReturncode() {
        return returncode;
    }

    public ListBaseResponse<T> getListBaseResponse() {
        return listBaseResponse;
    }
}
