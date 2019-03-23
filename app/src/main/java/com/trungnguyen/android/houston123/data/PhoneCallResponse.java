package com.trungnguyen.android.houston123.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PhoneCallResponse implements Serializable {
    @SerializedName("columns")
    public ColumnsData columns;
    @SerializedName("summary")
    public SummaryData summary;
}
