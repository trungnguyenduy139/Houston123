package com.trungnguyen.android.houston123.ui.userdetail.detailmodel;

import com.trungnguyen.android.houston123.base.BaseModel;
import com.trungnguyen.android.houston123.base.BaseUserModel;
import com.trungnguyen.android.houston123.util.ModelResourceLoader;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Created by trungnd4 on 13/07/2018.
 */
public class LecturerModel extends BaseUserModel implements Serializable {

    private String img;

    private String outDate;
    private String outReason;
    private String department;


    public LecturerModel(String name, String phone, String lecturerId, String img, String address, String email, String cmnd, String outDate, String outReason, String department) {
        super(name, phone, address, lecturerId, email, cmnd);
        this.img = img;
        this.outDate = outDate;
        this.outReason = outReason;
        this.department = department;
        this.permission = "giaovien";
    }

    public static BaseModel initFromResource(List<String> resources) {
        int index = 0;
        String name = resources.get(index++);
        String phone = resources.get(index++);
        String address = resources.get(index++);
        String lecturerId = resources.get(index++);
        String email = resources.get(index++);
        String cmnd = resources.get(index++);
        String img = "";
        String outDate = resources.get(index++);
        String outReason = resources.get(index++);
        String department = resources.get(index++);

        return new LecturerModel(name, phone, address, lecturerId, email, cmnd, img, outDate, outReason, department);
    }

    public String getImg() {
        return img;
    }

    public String getOutDate() {
        if (outDate == null) {
            return "";
        }
        return outDate;
    }

    public String getOutReason() {
        if (outReason == null) {
            return "";
        }
        return outReason;
    }

    public String getDepartment() {
        if (department == null) {
            return "";
        }
        return department;
    }

    @Override
    public List<String> getSource() {
        return ModelResourceLoader.loadResourceLecturer();
    }

    @Override
    public List<String> getValue() {
        return Arrays.asList(getMainContent(), getSubCotent(), getAddress(), getEmail(), getCmnd(), getOutDate(), getOutReason(), getDepartment());
    }
}
