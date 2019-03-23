package com.trungnguyen.android.houston123.data;

import com.google.gson.annotations.SerializedName;

public class MaritalData {
    @SerializedName("divorced")
    public double divorced;
    @SerializedName("married")
    public double married;
    @SerializedName("single")
    public double single;
    @SerializedName("unknown")
    public double unknown;

}
