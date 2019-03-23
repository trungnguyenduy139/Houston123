package com.trungnguyen.android.houston123.data;

import com.google.gson.annotations.SerializedName;

public class EmailResponse {
    @SerializedName("columns")
    public ColumnsData columns;
    @SerializedName("summary")
    public SummaryData summary;
}
