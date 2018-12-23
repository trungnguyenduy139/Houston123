package com.trungnguyen.android.houston123.ui.userdetail.detailmodel;

import com.trungnguyen.android.houston123.base.BaseModel;

import java.util.Collections;
import java.util.List;

/**
 * Created by trungnd4 on 23/12/2018.
 */
public class DetailClassModel extends BaseModel {

    String studentId;

    String clazzId;

    String transferId;

    String transferTime;

    public DetailClassModel(String studentId, String clazzId, String transferId, String transferTime) {
        this.studentId = studentId;
        this.clazzId = clazzId;
        this.transferId = transferId;
        this.transferTime = transferTime;
    }

    public DetailClassModel(String mainContent, String subContent, String modelId, String studentId, String clazzId, String transferId, String transferTime) {
        super(mainContent, subContent, modelId);
        this.studentId = studentId;
        this.clazzId = clazzId;
        this.transferId = transferId;
        this.transferTime = transferTime;
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
        return transferId;
    }

    public void setTransferId(String transferId) {
        this.transferId = transferId;
    }

    public String getTransferTime() {
        return transferTime;
    }

    public void setTransferTime(String transferTime) {
        this.transferTime = transferTime;
    }

    @Override
    public List<String> getSource() {
        return Collections.emptyList();
    }

    @Override
    public List<String> getValue() {
        return Collections.emptyList();
    }
}
