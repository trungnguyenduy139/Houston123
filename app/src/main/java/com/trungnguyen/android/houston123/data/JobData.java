package com.trungnguyen.android.houston123.data;

import com.google.gson.annotations.SerializedName;

public class JobData {
    @SerializedName("admin")
    public double admin;
    @SerializedName("blue-collar")
    public double blueCollar;
    @SerializedName("entrepreneur")
    public double entrepreneur;
    @SerializedName("housemaid")
    public double housemaid;
    @SerializedName("management")
    public double management;
    @SerializedName("retired")
    public double retired;
    @SerializedName("self-employed")
    public double selfEmployed;
    @SerializedName("services")
    public double services;
    @SerializedName("student")
    public double student;
    @SerializedName("technician")
    public double technician;
    @SerializedName("unemployed")
    public double unemployed;
    @SerializedName("unknown")
    public double unknown;
}
