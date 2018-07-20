package com.trungnguyen.android.houston123.ui.listuser;

import java.io.Serializable;

/**
 * Created by trungnd4 on 13/07/2018.
 */
public class UserModel implements Serializable {
    private String mUserImg;
    private String mName;
    private String mPosition;

    public String getmUserImg() {
        return mUserImg;
    }

    public void setmUserImg(String mUserImg) {
        this.mUserImg = mUserImg;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmPosition() {
        return mPosition;
    }

    public void setmPosition(String mPosition) {
        this.mPosition = mPosition;
    }
}
