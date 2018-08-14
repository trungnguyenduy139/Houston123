package com.trungnguyen.android.houston123.ui.userdetail;

import android.databinding.Bindable;

import com.trungnguyen.android.houston123.base.BaseUserModel;

/**
 * Created by trungnd4 on 13/07/2018.
 */
public class LecturerModel extends BaseUserModel {
    private String lectureCount = "10";

    @Bindable
    public String getLectureCount() {
        return lectureCount;
    }

    public void setLectureCount(String lectureCount) {
        this.lectureCount = lectureCount;
    }

}
