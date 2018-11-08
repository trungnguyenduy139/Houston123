package com.trungnguyen.android.houston123.util;

import com.trungnguyen.android.houston123.anotation.UserType;
import com.trungnguyen.android.houston123.base.BaseModel;
import com.trungnguyen.android.houston123.ui.userdetail.ItemDetailModel;
import com.trungnguyen.android.houston123.ui.userdetail.detailmodel.ManagerModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by trungnd4 on 14/08/2018.
 */

public final class ModelResourceLoader {
    public static List<String> loadResourceLecturer() {
        return Arrays.asList("Họ và tên", "Số điện thoại", "Địa chỉ", "Email", "CMND", "Ngày nghĩ", "Lý do nghĩ", "Cơ sở");
    }

    public static List<String> loadResourceManager() {
        return Arrays.asList("Mã quản lý", "Họ và tên", "Số điện thoại", "Địa chỉ", "Email", "CMND", "Chức vụ", "Ngày nghĩ", "Lý do nghĩ", "Cơ sở");
    }


    public static List<String> loadClassResource() {
        return Arrays.asList("Mã Lớp", "Lớp", "Mã Môn Học", "Tên", "Mã Giáo Viên", "Họ Và Tên", "Ngày Bắt Đầu", "Ngày Kết Thúc", "Nhánh", "Cơ sở");
    }


    public static List<String> loadStudentResource() {
        return Arrays.asList("Họ tên", "SĐT", "Địa chỉ", "Mã Học Viên", "Lớp", "Ngày Sinh", "Học Lực Đầu Vào", "Ngày Bắt Đầu", "Trường", "Cơ sở");
    }

    public static List<String> loadSubjectResource() {
        return Arrays.asList("Tên môn học", "Mã môn học", "Manager allow");
    }

    public static List<ItemDetailModel> loadEmptyResources(int userCode) {
        List<String> resources;
        List<ItemDetailModel> resourcesModel = new ArrayList<>();
        switch (userCode) {
            case UserType.LECTURER:
                resources = loadResourceLecturer();
                break;
            case UserType.MANAGER:
                resources = loadResourceManager();
                break;
            case UserType.CLAZZ:
                resources = loadClassResource();
                break;
            case UserType.SUBJECT:
                resources = loadSubjectResource();
                break;
            default:
                resources = new ArrayList<>();
                break;
        }
        for (int index = 0; index < resources.size(); index++) {
            resourcesModel.add(new ItemDetailModel(resources.get(index), Constants.EMPTY));
        }
        return resourcesModel;
    }

    public static BaseModel convertModel(int code, List<String> resources) {
        BaseModel model;
        switch (code) {
            case UserType.LECTURER:
            case UserType.MANAGER:
            case UserType.CLAZZ:
            default:
                model = ManagerModel.initFromResource(resources);
        }

        return model;
    }
}
