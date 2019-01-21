package com.trungnguyen.android.houston123.base;

import android.text.TextUtils;

/**
 * Created by trungnd4 on 20/07/2018.
 */
public abstract class BaseUserModel extends BaseModel {

    private String userImg;
    protected String name;
    protected String phoneNumber;
    protected String address;
    protected String userId;
    protected String email;
    protected String cmnd;

    protected String permission = "";

    public BaseUserModel() {
        super();
    }

    public BaseUserModel(String name, String phoneNumber, String address, String userId, String email, String cmnd) {
        super(name, phoneNumber, userId);
        this.address = address;
        this.userId = userId;
        this.email = email;
        this.cmnd = cmnd;
    }


    public String getPermission() {
        return permission;
    }

    public BaseUserModel(String name, String phoneNumber, String address, String userId) {
        super(name, phoneNumber, userId);
        this.address = address;
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
        if (email == null) {
            return "";
        }
        return email;
    }

    public String getCmnd() {
        if (cmnd == null) {
            return "";
        }
        return cmnd;
    }

    public String getAddress() {
        if (address == null) {
            return "";
        }
        return address;
    }

    public String getName() {
        if (name == null) {
            return "";
        }
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
}
