package com.trungnguyen.android.houston123.ui.login;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.trungnguyen.android.houston123.BR;

public class LoginModel extends BaseObservable {

    private String mUserName;
    private String mPassword;


    public LoginModel() {

    }

    public LoginModel(String mUserName, String mPassword) {
        this.mUserName = mUserName;
        this.mPassword = mPassword;
    }

    @Bindable
    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String mUserName) {
        this.mUserName = mUserName;
        notifyPropertyChanged(BR.userName);
    }

    @Bindable
    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String mPassword) {
        this.mPassword = mPassword;
        notifyPropertyChanged(BR.password);
    }
}
