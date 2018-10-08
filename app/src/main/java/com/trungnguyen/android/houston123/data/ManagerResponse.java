package com.trungnguyen.android.houston123.data;

import com.trungnguyen.android.houston123.ui.userdetail.detailmodel.ManagerModel;

/**
 * Created by trungnd4 on 08/10/2018.
 */
public class ManagerResponse extends BaseUserResponse{
    public ManagerModel convertToModel() {
        return new ManagerModel(userName, phoneNumber);
    }
}
