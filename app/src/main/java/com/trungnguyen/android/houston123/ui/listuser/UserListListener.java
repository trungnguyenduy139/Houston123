package com.trungnguyen.android.houston123.ui.listuser;

import com.trungnguyen.android.houston123.base.BaseModel;
import com.trungnguyen.android.houston123.base.BaseUserModel;
import com.trungnguyen.android.houston123.base.IAdapterListener;

/**
 * Created by trungnd4 on 14/07/2018.
 */
public interface UserListListener extends IAdapterListener {
    void onItemClick(BaseModel baseUserModel, int position);

    void onItemLongClick(int position);
}
