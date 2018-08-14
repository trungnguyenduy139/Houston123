package com.trungnguyen.android.houston123.ui.userdetail;

import android.content.Context;
import android.support.annotation.Nullable;

import com.trungnguyen.android.houston123.base.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by trungnd4 on 20/07/2018.
 */
public class LecturerViewModel extends BaseViewModel<ILecturerView> {

    @Nullable
    public LecturerModel mLecturerModel;

    public void setLecturerModel(@Nullable LecturerModel lecturerModel) {
        if (lecturerModel == null) {
            return;
        }
        mLecturerModel = lecturerModel;
    }

    public LecturerViewModel(Context context) {

        super(context);
        getDataManagerComponent().inject(this);
    }

    public List<ItemDetailModel> getListItemDetail() {

        // Using mLecturerModel to convert -> List<ItemDetailModel> to show on List View for dynamic User Detail types

        List<ItemDetailModel> listItemDetail = new ArrayList<>();

        // convert from Lecturer Model to List of Detail Model

        return listItemDetail;
    }

    @Override
    public void onDestroy() {
        mLecturerModel = null;
        super.onDestroy();
    }
}
