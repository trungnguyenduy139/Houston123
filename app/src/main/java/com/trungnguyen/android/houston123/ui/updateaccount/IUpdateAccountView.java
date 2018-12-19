package com.trungnguyen.android.houston123.ui.updateaccount;

import com.trungnguyen.android.houston123.base.IBaseLoadingView;
import com.trungnguyen.android.houston123.ui.userdetail.ItemDetailModel;

import java.util.List;

/**
 * Created by trungnd4 on 13/12/2018.
 */
public interface IUpdateAccountView extends IBaseLoadingView {

    void onLoadResourceCompleted(List<ItemDetailModel> items);

    void updateAccountLogin();

    void updateSuccess(String message);
}
