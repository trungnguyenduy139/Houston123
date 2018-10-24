package com.trungnguyen.android.houston123.ui.userdetail.detailmodel;


import com.trungnguyen.android.houston123.base.BaseUserModel;
import com.trungnguyen.android.houston123.util.ModelResourceLoader;

import java.util.Arrays;
import java.util.List;

/**
 * Created by trungnd4 on 14/08/2018.
 */
public class ManagerModel extends BaseUserModel {

    private String img;
    private String outDate;
    private String outReason;
    private String department;
    private String position;


    public ManagerModel(String name, String phone, String lecturerId, String img, String address, String email, String cmnd, String outDate, String outReason, String department, String position) {
        super(name, phone, address, lecturerId, email, cmnd);
        this.img = img;
        this.outDate = outDate;
        this.outReason = outReason;
        this.department = department;
        this.position = position;
    }

    public String getImg() {
        return img;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getCmnd() {
        return cmnd;
    }

    public String getDepartment() {
        return department;
    }

    public String getPosition() {
        return position;
    }

    public String getOutDate() {
        return outDate != null ? outDate : "";
    }

    public String getOutReason() {
        return outReason != null ? outReason : "";
    }


    @Override
    public List<String> getSource() {
        return ModelResourceLoader.loadResourceManager();
    }

    @Override
    public List<String> getValue() {
        return Arrays.asList(userId, getMainContent(), getSubCotent(), address, email, cmnd, position, getOutDate(), getOutReason(), department);
    }
}
