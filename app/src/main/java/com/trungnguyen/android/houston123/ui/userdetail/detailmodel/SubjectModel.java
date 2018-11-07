package com.trungnguyen.android.houston123.ui.userdetail.detailmodel;

import com.trungnguyen.android.houston123.base.BaseModel;
import com.trungnguyen.android.houston123.util.ModelResourceLoader;

import java.util.Arrays;
import java.util.List;

/**
 * Created by trungnd4 on 07/11/2018.
 */
public class SubjectModel extends BaseModel {

    private String managerAllow;

    public SubjectModel(String name, String subjectId, String managerAllow) {
        super(name, subjectId);
        this.managerAllow = managerAllow;
    }

    public String getManagerAllow() {
        if (managerAllow == null) {
            return "";
        }
        return managerAllow;
    }

    @Override
    public List<String> getSource() {
        return ModelResourceLoader.loadSubjectResource();
    }

    @Override
    public List<String> getValue() {
        return Arrays.asList(getMainContent(), getSubCotent(), getManagerAllow());
    }
}
