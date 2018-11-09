package com.trungnguyen.android.houston123.ui.userdetail.detailmodel;

import com.trungnguyen.android.houston123.base.BaseModel;

import java.util.Arrays;
import java.util.List;

/**
 * Created by trungnd4 on 09/11/2018.
 */
public class StudentShortModel extends BaseModel {


    String userId;

    String clazzId;

    String transClazz;

    String transTime;

    public StudentShortModel(String id, String mainContent, String subContent, String transClazz, String transTime) {
        super(subContent, transClazz, mainContent);
        this.transClazz = transClazz;
        this.userId = id;
        this.transTime = transTime;
    }

    public String getSafeObject(String object) {
        return object == null ? "" : object;
    }

    @Override
    public List<String> getSource() {
        return Arrays.asList("ID", "Mã học viên", "Mã lớp", "Lớp chuyển", "Thời chuyển");
    }

    @Override
    public List<String> getValue() {
        return Arrays.asList(getSafeObject(userId), getMainContent(), getSafeObject(getSubCotent()), getSafeObject(clazzId), getSafeObject(transClazz), getSafeObject(transTime));
    }
}
