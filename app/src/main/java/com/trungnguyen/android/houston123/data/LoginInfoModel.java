package com.trungnguyen.android.houston123.data;

import com.trungnguyen.android.houston123.base.BaseModel;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Created by trungnd4 on 06/11/2018.
 */
public class LoginInfoModel extends BaseModel implements Serializable {

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

    public LoginInfoModel updateModelLoginInfo(List<String> resources) {
        try {
            int i = 0;
            name = resources.get(i++);
            phone = resources.get(i++);
            address = resources.get(i++);
            cmnd = resources.get(i++);
            email = resources.get(i++);
            position = resources.get(i++);
        } catch (Exception ignored) {

        }
        return this;
    }

    @Override
    public List<String> getSource() {
        return Arrays.asList("Họ và tên", "Số điện thoại", "Địa chỉ", "CMND", "Email", "Chức vụ");
    }

    @Override
    public List<String> getValue() {
        return Arrays.asList(getSafeObject(getMainContent()), getSafeObject(getSubCotent()), getSafeObject(address), getSafeObject(cmnd), getSafeObject(email), getSafeObject(position));
    }
}
