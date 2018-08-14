package com.trungnguyen.android.houston123.ui.userdetail.detailmodel;

import android.databinding.Bindable;

import com.trungnguyen.android.houston123.base.BaseUserModel;
import com.trungnguyen.android.houston123.ui.userdetail.ItemDetailModel;
import com.trungnguyen.android.houston123.util.ModelResourceLoader;

import java.util.List;

/**
 * Created by trungnd4 on 13/07/2018.
 */
public class LecturerModel extends BaseUserModel {
    private String lectureCount = "10";

    @Bindable
    public String getLectureCount() {
        return lectureCount;
    }

    public void setLectureCount(String lectureCount) {
        this.lectureCount = lectureCount;
    }

    @Override
    public List<ItemDetailModel> convert() {
        List<String> lecturerResources = ModelResourceLoader.loadResourceLecturer();
        List<ItemDetailModel> itemDetailModelList = super.convert();
        int index = 0;
        itemDetailModelList.add(new ItemDetailModel(lecturerResources.get(index++), lectureCount));
        return itemDetailModelList;
    }
}
