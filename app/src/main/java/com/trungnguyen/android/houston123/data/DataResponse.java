package com.trungnguyen.android.houston123.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by trungnd4 on 23/10/2018.
 */
public class DataResponse<T extends EmptyResponse> {
    @SerializedName("code")
    int returncode;

    @SerializedName("embeddata")
    ListBaseResponse<T> listBaseResponse;

    public int getReturncode() {
        return returncode;
    }

    public ListBaseResponse<T> getListBaseResponse() {
        return listBaseResponse;
    }
}
