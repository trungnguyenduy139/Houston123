package com.trungnguyen.android.houston123.ui.userdetail.detailmodel;

import com.trungnguyen.android.houston123.base.BaseModel;
import com.trungnguyen.android.houston123.util.ModelResourceLoader;

import java.util.Arrays;
import java.util.List;

/**
 * Created by trungnd4 on 23/12/2018.
 */
public class DetailClassModel extends BaseModel {

    String studentId;

    String clazzId;

    String point;

    String comment;

    public DetailClassModel(String studentId, String clazzId, String transferId, String transferTime, String studentName, String userId) {
        super(studentName, studentId, userId);
        this.clazzId = clazzId;
        this.point = transferId;
        this.comment = transferTime;
    }

    public DetailClassModel(String mainContent, String subContent, String modelId, String studentId, String clazzId, String transferId, String transferTime) {
        super(mainContent, subContent, modelId);
        this.studentId = studentId;
        this.clazzId = clazzId;
        this.point = transferId;
        this.comment = transferTime;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getClazzId() {
        return clazzId;
    }

    public void setClazzId(String clazzId) {
        this.clazzId = clazzId;
    }

    public String getTransferId() {
        return point;
    }

    public void setTransferId(String transferId) {
        this.point = transferId;
    }

    public String getTransferTime() {
        return comment;
    }

    public void setTransferTime(String transferTime) {
        this.comment = transferTime;
    }

    @Override
    public List<String> getSource() {
        return ModelResourceLoader.loadClassDetailResource();
    }

    @Override
    public List<String> getValue() {
        return Arrays.asList(getModelId(), getSafeObject(clazzId), getSafeObject(getSubCotent()), getSafeObject(point), getSafeObject(comment), getSafeObject(getMainContent()));
    }
}
