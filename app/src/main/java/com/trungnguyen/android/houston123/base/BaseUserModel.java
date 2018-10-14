package com.trungnguyen.android.houston123.base;

import android.databinding.BaseObservable;

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

    public BaseUserModel(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
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

    public Observable convert() {
        return Observable.just(new ArrayList<>());
    }
}
