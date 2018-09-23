package com.trungnguyen.android.houston123.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by trungnd4 on 23/09/2018.
 */
public class AuthenticateResponse {
    @SerializedName("status")
    public Status status;

    public class Status {
        @SerializedName("code")
        public int authResponseCode;

        @SerializedName("desc")
        public String desc;
    }
}
