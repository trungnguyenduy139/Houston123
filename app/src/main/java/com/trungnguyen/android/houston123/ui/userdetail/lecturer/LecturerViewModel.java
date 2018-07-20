package com.trungnguyen.android.houston123.ui.userdetail.lecturer;

import android.content.Context;

import com.trungnguyen.android.houston123.base.BaseViewModel;
import com.trungnguyen.android.houston123.injection.Injector;

/**
 * Created by trungnd4 on 20/07/2018.
 */
public class LecturerViewModel extends BaseViewModel {

    public static final String HIHI = "hihi";

    public LecturerViewModel(Context context) {

        super(context);
        Injector.getInstance().getDataManagerComponent().inject(this);
    }
}
