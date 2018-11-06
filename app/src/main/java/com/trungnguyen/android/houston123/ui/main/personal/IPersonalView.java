package com.trungnguyen.android.houston123.ui.main.personal;

import com.trungnguyen.android.houston123.ui.userdetail.ItemDetailModel;

import java.util.List;

/**
 * Created by trungnd4 on 06/10/2018.
 */
public interface IPersonalView {
    void successToLogout();

    void onLoadResourceCompleted(List<ItemDetailModel> items);
}
