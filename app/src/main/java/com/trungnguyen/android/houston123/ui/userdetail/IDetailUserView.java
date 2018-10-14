package com.trungnguyen.android.houston123.ui.userdetail;

import com.trungnguyen.android.houston123.base.BaseUserModel;
import com.trungnguyen.android.houston123.base.IBaseLoadingView;

import java.util.List;

/**
 * Created by trungnd4 on 13/08/2018.
 */
public interface IDetailUserView extends IBaseLoadingView {
    BaseUserModel getUserModel();

    void updateResourceList(List<ItemDetailModel> list);
}
