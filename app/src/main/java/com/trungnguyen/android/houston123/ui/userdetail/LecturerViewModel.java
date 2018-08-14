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

    private List<ItemDetailModel> mItemDetailList = new ArrayList<>();

    public void setLecturerModel(@Nullable BaseUserModel userModel) {
        if (userModel == null) {
            return;
        }
        if (userModel instanceof LecturerModel) {
            mItemDetailList.addAll(((LecturerModel) userModel).convert());
        } else if (userModel instanceof ManagerModel) {
            mItemDetailList.addAll(((ManagerModel) userModel).convert());
        }
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
