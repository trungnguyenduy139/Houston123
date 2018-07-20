package com.trungnguyen.android.houston123.ui.userdetail.lecturer;

import com.trungnguyen.android.houston123.base.BaseUserModel;

/**
 * Created by trungnd4 on 13/07/2018.
 */
public class LecturerModel extends BaseUserModel {
    private int lectureCount = 10;

    public int getLectureCount() {
        return lectureCount;
    }

    public void setLectureCount(int lectureCount) {
        this.lectureCount = lectureCount;
    }
}
