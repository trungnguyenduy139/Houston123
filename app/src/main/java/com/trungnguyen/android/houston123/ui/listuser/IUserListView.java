package com.trungnguyen.android.houston123.ui.listuser;

import com.trungnguyen.android.houston123.base.IBaseView;

/**
 * Created by trungnd4 on 12/08/2018.
 */
public interface IUserListView extends IBaseView {
    void showConfirmDeleteUserDialog(int position);

    void doSearchAction(String text);
}
