package com.trungnguyen.android.houston123.base;

import android.databinding.BaseObservable;

import com.trungnguyen.android.houston123.ui.userdetail.ItemDetailModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by trungnd4 on 20/07/2018.
 */
public abstract class BaseUserModel extends BaseObservable implements Serializable {

    private String userImg;
    private String name;
    private String phoneNumber;
    private String position;
    private String userId;
    private String address;


    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void getPhoneNumber(String phomeNumber) {
        this.phoneNumber = phomeNumber;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public List<ItemDetailModel> convert() {
        return new ArrayList<>();
    }
}
