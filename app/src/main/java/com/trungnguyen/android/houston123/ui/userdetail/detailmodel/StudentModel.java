package com.trungnguyen.android.houston123.ui.userdetail.detailmodel;

import com.trungnguyen.android.houston123.base.BaseUserModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by trungnd4 on 08/10/2018.
 */
public class StudentModel extends BaseUserModel {
    private String lecturerId;
    private String img;
    private String email;
    private String cmnd;
    private String outDate;
    private String outReason;
    private String department;
    private String position;

    public StudentModel(String name, String phoneNumber, String address, String userId, String email, String cmnd) {
        super(name, phoneNumber, address, userId, email, cmnd);
    }

    @Override
    public List<String> getSource() {
        return new ArrayList<>();
    }

    @Override
    public List<String> getValue() {
        return new ArrayList<>();
    }
}
