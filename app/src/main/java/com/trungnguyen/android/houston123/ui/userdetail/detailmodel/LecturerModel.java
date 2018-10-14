package com.trungnguyen.android.houston123.ui.userdetail.detailmodel;

import com.trungnguyen.android.houston123.base.BaseUserModel;
import com.trungnguyen.android.houston123.ui.userdetail.ItemDetailModel;
import com.trungnguyen.android.houston123.util.ModelResourceLoader;

import java.io.Serializable;
import java.util.List;

/**
 * Created by trungnd4 on 13/07/2018.
 */
public class LecturerModel extends BaseUserModel implements Serializable {

    private String lecutrerStt;
    private String lecturerId;
    private String img;
    private String address;
    private String email;
    private String cmnd;
    private String outDate;
    private String outReason;
    private String department;


    public LecturerModel(String name, String phone, String lecutrerStt, String lecturerId, String img, String address, String email, String cmnd, String outDate, String outReason, String department) {
        super(name, phone);
        this.lecutrerStt = lecutrerStt;
        this.lecturerId = lecturerId;
        this.img = img;
        this.address = address;
        this.email = email;
        this.cmnd = cmnd;
        this.outDate = outDate;
        this.outReason = outReason;
        this.department = department;
    }

//    @Override
//    public List<ItemDetailModel> convert() {
//        List<String> lecturerResources = ModelResourceLoader.loadResourceLecturer();
//        List<ItemDetailModel> itemDetailModelList = super.convert();
//        int index = 0;
//        itemDetailModelList.add(new ItemDetailModel(lecturerResources.get(index++), lecutrerStt));
//        return itemDetailModelList;
//    }
}
