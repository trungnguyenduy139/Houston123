package com.trungnguyen.android.houston123.data;

import com.trungnguyen.android.houston123.base.BaseModel;

import java.util.Arrays;
import java.util.List;

/**
 * Created by trungnd4 on 06/11/2018.
 */
public class LoginInfoModel extends BaseModel {

    public String permission;

    public String name;

    public String phone;

    public String address;

    public String cmnd;

    public String email;

    public String position;

    public LoginInfoModel(String name, String phone, String address, String cmnd, String email, String position, String accountId) {
        super(name, phone, accountId);
        this.address = address;
        this.cmnd = cmnd;
        this.email = email;
        this.position = position;
    }

    @Override
    public List<String> getSource() {
        return Arrays.asList("Họ và tên", "Số điện thoại", "Địa chỉ", "CMND", "Email", "Chức vụ");
    }

    @Override
    public List<String> getValue() {
        return Arrays.asList(getSafeObject(getMainContent()), getSafeObject(getSubCotent()), getSafeObject(address), getSafeObject(email), getSafeObject(cmnd), getSafeObject(position));
    }
}
