package com.trungnguyen.android.houston123.ui.userdetail.detailmodel;

import com.trungnguyen.android.houston123.base.BaseModel;
import com.trungnguyen.android.houston123.util.ModelResourceLoader;

import java.util.Arrays;
import java.util.List;

/**
 * Created by trungnd4 on 23/10/2018.
 */
public class ClassModel extends BaseModel {

    public String clazzId;

    public String clazz;

//    public String subjectId; -> Sub content
//
//    public String clazzName; -> Main content

    public String lecturerId;

    public String lecturerName;

    public String startDate;

    public String endDate;

    public String branch;

    public String departmen;

    public ClassModel(String mainContent, String subCotent, String clazzId, String clazz, String lecturerId, String lecturerName, String startDate, String endDate, String branch, String departmen) {
        super(mainContent, subCotent, clazzId);
        this.clazzId = clazzId;
        this.clazz = clazz;
        this.lecturerId = lecturerId;
        this.lecturerName = lecturerName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.branch = branch;
        this.departmen = departmen;
    }

    public ClassModel(String mainContent, String subCotent, String clazzId, String lecturerId, String departmen) {
        super(mainContent, subCotent, clazzId);
        this.lecturerId = lecturerId;
        this.startDate = "";
        this.endDate = "";
        this.departmen = departmen;
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

        return new ClassModel(name, phone, address, lecturerId, email, cmnd, img, outDate, outReason, department);
    }

    @Override
    public List<String> getSource() {
        return ModelResourceLoader.loadClassResource();
    }

    @Override
    public List<String> getValue() {
        return Arrays.asList(clazzId, clazz, getSubCotent(), getMainContent(), lecturerId, lecturerName, startDate, endDate, branch, departmen);
    }
}
