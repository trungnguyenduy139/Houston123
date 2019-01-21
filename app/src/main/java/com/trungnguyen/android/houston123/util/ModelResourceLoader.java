package com.trungnguyen.android.houston123.util;

import com.trungnguyen.android.houston123.anotation.UserType;
import com.trungnguyen.android.houston123.base.BaseModel;
import com.trungnguyen.android.houston123.ui.userdetail.ItemDetailModel;
import com.trungnguyen.android.houston123.ui.userdetail.detailmodel.ClassModel;
import com.trungnguyen.android.houston123.ui.userdetail.detailmodel.LecturerModel;
import com.trungnguyen.android.houston123.ui.userdetail.detailmodel.ManagerModel;
import com.trungnguyen.android.houston123.ui.userdetail.detailmodel.StudentModel;
import com.trungnguyen.android.houston123.ui.userdetail.detailmodel.SubjectModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import timber.log.Timber;

/**
 * Created by trungnd4 on 14/08/2018.
 */

public final class ModelResourceLoader {
    public static List<String> loadResourceLecturer() {
        return Arrays.asList("Mã giáo viên","Họ và tên", "Số điện thoại", "Địa chỉ", "Email", "CMND", "Ngày nghĩ", "Lý do nghĩ", "Cơ sở", "Tên cơ sở", "Available");
    }

    public static List<String> loadResourceManager() {
        return Arrays.asList("Mã quản lý", "Họ và tên", "Số điện thoại", "Địa chỉ", "Email", "CMND", "Chức vụ", "Ngày nghĩ", "Lý do nghĩ", "Cơ sở", "Permission", "Tên cơ sở");
    }


    public static List<String> loadClassResource() {
        return Arrays.asList("Mã Lớp", "Lớp", "Mã Môn Học", "Tên", "Mã Giáo Viên", "Họ Và Tên", "Ngày Bắt Đầu", "Ngày Kết Thúc", "Nhánh", "Cơ sở");
    }

    public static List<String> loadClassDetailResource() {
        return Arrays.asList("Mã Chi Tiết", "Mã Lớp", "Mã Học Viên", "Điểm Số", "Đánh Giá", "Họ Và Tên");
    }


    public static List<String> loadStudentResource() {
        return Arrays.asList("Họ tên", "Lớp", "Số điện thoại", "Địa chỉ",
                "Ngày Sinh", "Học Lực Đầu Vào",
                "Ngày Nhập Học","Trường", "Ngày nghĩ","Lý do nghĩ",
                "Họ tên người thân 1",
                "SĐT người thân 1",
                "Nghề nghiệp người thân 1",
                "Họ tên người thân 2",
                "SĐT người thân 2",
                "Nghề nghiệp người thân 2",
                "Biết Houston123 như thế nào",
                "Chính thức",
                "Cơ sở",
                "Tên cơ sở");
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
            case UserType.STUDENT:
                resources = loadStudentResource();
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
        Timber.d("convert model %s", code);
        switch (code) {
            case UserType.LECTURER:
                model = LecturerModel.initFromResource(resources);
                break;
            case UserType.MANAGER:
                model = ManagerModel.initFromResource(resources);
                break;
            case UserType.CLAZZ:
                model = ClassModel.initFromResource(resources);
                break;
            case UserType.SUBJECT:
                model = SubjectModel.initFromResource(resources);
                break;
            case UserType.STUDENT:
                model = StudentModel.initFromResource(resources);
                break;
            default:
                model = BaseModel.EMPTY;
                break;
        }

        return model;
    }
}
