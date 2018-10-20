package com.trungnguyen.android.houston123.ui.listuser;

import com.trungnguyen.android.houston123.base.BaseUserModel;
import com.trungnguyen.android.houston123.base.IBaseView;

import java.util.Collection;

/**
 * Created by trungnd4 on 12/08/2018.
 */
public interface IUserListView extends IBaseView {
    void showConfirmDeleteUserDialog(int position);

    void doSearchAction(String text);

    void doLoadMore(Collection<? extends BaseUserModel> userModels);

    void doRefreshList(Collection<? extends BaseUserModel> usersModels);

    void setRefreshing(boolean isRefreshing);

    void successToDeleteUser(int position);

    void failedToDeleteUser();

    int getCurrentUserCode();

    void handleSearchResult();
}
