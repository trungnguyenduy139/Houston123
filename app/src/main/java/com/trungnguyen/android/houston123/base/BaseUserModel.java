package com.trungnguyen.android.houston123.base;

import android.databinding.BaseObservable;
import android.text.TextUtils;

import java.io.Serializable;
import java.util.ArrayList;

import io.reactivex.Observable;

/**
 * Created by trungnd4 on 20/07/2018.
 */
public abstract class BaseUserModel extends BaseObservable implements Serializable {

    private String userImg;
    protected String name;
    protected String phoneNumber;
    protected String address;
    protected String userId;
    protected String email;
    protected String cmnd;

    public BaseUserModel(String name, String phoneNumber, String address, String userId, String email, String cmnd) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.userId = userId;
        this.email = email;
        this.cmnd = cmnd;
    }

    public String getUserImg() {
        return userImg;
    }

    public String getUserId() {
        if (TextUtils.isEmpty(userId)) {
            return "";
        }
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getCmnd() {
        return cmnd;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        if (phoneNumber == null) {
            return "";
        }
        return phoneNumber;
    }

    public void getPhoneNumber(String phomeNumber) {
        this.phoneNumber = phomeNumber;
    }

    public Observable convert() {
        return Observable.just(new ArrayList<>());
    }
}
