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

    public ClassModel(String mainContent, String subCotent) {
        super(mainContent, subCotent);
    }

    public ClassModel(String mainContent, String subCotent, String clazzId, String clazz, String lecturerId, String lecturerName, String startDate, String endDate, String branch, String departmen) {
        super(mainContent, subCotent);
        this.clazzId = clazzId;
        this.clazz = clazz;
        this.lecturerId = lecturerId;
        this.lecturerName = lecturerName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.branch = branch;
        this.departmen = departmen;
    }

    @Override
    public List<String> getSource() {
        return ModelResourceLoader.loadClassResource();
    }

    @Override
    public List<String> getValue() {
        return Arrays.asList(clazzId, getMainContent(), getSubCotent(), lecturerId, lecturerName, startDate, endDate, branch, departmen);
    }
}
