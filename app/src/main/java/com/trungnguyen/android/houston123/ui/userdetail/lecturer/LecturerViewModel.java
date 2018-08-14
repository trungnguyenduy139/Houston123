package com.trungnguyen.android.houston123.ui.userdetail.lecturer;

import android.content.Context;
import android.support.annotation.Nullable;

import com.trungnguyen.android.houston123.base.BaseViewModel;
import com.trungnguyen.android.houston123.injection.Injector;

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

    @Override
    public void onDestroy() {
        mLecturerModel = null;
        super.onDestroy();
    }
}
