package com.trungnguyen.android.houston123.ui.main.home;

import android.databinding.ObservableField;
import android.support.annotation.NonNull;

/**
 * Created by trungnd4 on 20/08/2018.
 */
public class HomeItemViewModel {
    @NonNull
    public final ObservableField<String> mTitle;

    @NonNull
    public final ObservableField<Integer> mImgRes;

    private final HomeItem mHomeItem;

    public HomeItemViewModel(HomeItem homeItem) {
        this.mHomeItem = homeItem;
        mTitle = new ObservableField<>(mHomeItem.getTitle());
        mImgRes = new ObservableField<>(mHomeItem.getResourceId());
    }
}
