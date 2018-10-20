package com.trungnguyen.android.houston123.data;

import com.trungnguyen.android.houston123.ui.userdetail.detailmodel.StudentModel;

/**
 * Created by trungnd4 on 08/10/2018.
 */
public class StudentResponse extends BaseUserResponse {
    public StudentModel convertToModel() {
        return new StudentModel(userName, phoneNumber, address, "", "", "");
    }
}
