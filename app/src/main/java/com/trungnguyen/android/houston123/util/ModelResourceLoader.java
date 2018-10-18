package com.trungnguyen.android.houston123.util;

import com.trungnguyen.android.houston123.ui.userdetail.ItemDetailModel;

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

    public static List<ItemDetailModel> loadEmptyManagerResources() {
        return new ArrayList<>();
    }
}
