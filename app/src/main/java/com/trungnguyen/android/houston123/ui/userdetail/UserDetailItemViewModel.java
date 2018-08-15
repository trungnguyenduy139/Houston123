package com.trungnguyen.android.houston123.ui.userdetail;

import android.databinding.ObservableField;
import android.support.annotation.NonNull;

/**
 * Created by trungnd4 on 15/08/2018.
 */

public class UserDetailItemViewModel {
    @NonNull
    public final ObservableField<String> mKey;

    @NonNull
    public final ObservableField<String> mValue;

    private final ItemDetailModel mItemDetailModel;

    public UserDetailItemViewModel(ItemDetailModel itemDetailModel) {
        this.mItemDetailModel = itemDetailModel;
        mKey = new ObservableField<>(mItemDetailModel.getKey());
        mValue = new ObservableField<>(mItemDetailModel.getValue());
    }
}
