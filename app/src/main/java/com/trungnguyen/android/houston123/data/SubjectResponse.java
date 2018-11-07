package com.trungnguyen.android.houston123.data;

import com.google.gson.annotations.SerializedName;
import com.trungnguyen.android.houston123.base.BaseModel;
import com.trungnguyen.android.houston123.ui.userdetail.detailmodel.SubjectModel;

/**
 * Created by trungnd4 on 07/11/2018.
 */
public class SubjectResponse extends EmptyResponse {
    @SerializedName("mamon")
    String subjectId;
    @SerializedName("name")
    String name;
    @SerializedName("managerAllow")
    String managerAllow;


    @Override
    public BaseModel convertToModel() {
        return new SubjectModel(name, subjectId, managerAllow);
    }
}
