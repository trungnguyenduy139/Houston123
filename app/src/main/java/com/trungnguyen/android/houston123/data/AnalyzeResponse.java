package com.trungnguyen.android.houston123.data;

import com.google.gson.annotations.SerializedName;

public class AnalyzeResponse {
    @SerializedName("email")
    public EmailResponse email;
    @SerializedName("facebook")
    public FacebookResponse facebook;
    @SerializedName("phone_call")
    public PhoneCallResponse phoneCall;
    @SerializedName("poster")
    public PosterResponse poster;

}
