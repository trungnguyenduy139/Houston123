package com.trungnguyen.android.houston123.ui.userdetail;

import android.content.Context;
import android.support.annotation.Nullable;

import com.trungnguyen.android.houston123.base.BaseUserModel;
import com.trungnguyen.android.houston123.base.BaseViewModel;
import com.trungnguyen.android.houston123.ui.userdetail.detailmodel.LecturerModel;
import com.trungnguyen.android.houston123.ui.userdetail.detailmodel.ManagerModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by trungnd4 on 20/07/2018.
 */
public class LecturerViewModel extends BaseViewModel<ILecturerView> {

    @Nullable
    public BaseUserModel mUserModel;

    public void setLecturerModel(@Nullable BaseUserModel userModel) {
        if (userModel == null) {
            return;
        }
        mUserModel = userModel;
    }

    public List<ItemDetailModel> initDetailList(BaseUserModel baseUserModel) {

        List<ItemDetailModel> itemDetailModelList = new ArrayList<>();

        if (baseUserModel instanceof LecturerModel) {
            itemDetailModelList.addAll(((LecturerModel) baseUserModel).convert());
        } else if (baseUserModel instanceof ManagerModel) {
            itemDetailModelList.addAll(((ManagerModel) baseUserModel).convert());
        }
        return itemDetailModelList;
    }

    public LecturerViewModel(Context context) {

        super(context);
        getDataManagerComponent().inject(this);
    }

    @Override
    public void onDestroy() {
        mUserModel = null;
        super.onDestroy();
    }
}
