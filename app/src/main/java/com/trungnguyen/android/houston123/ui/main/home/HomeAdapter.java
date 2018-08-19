package com.trungnguyen.android.houston123.ui.main.home;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.trungnguyen.android.houston123.base.AbsCommonAdapter;
import com.trungnguyen.android.houston123.base.BaseViewHolder;

import java.util.List;

/**
 * Created by trungnd4 on 19/08/2018.
 */
public class HomeAdapter extends AbsCommonAdapter<HomeAdapterListener, HomeItem> {


    public HomeAdapter(List<HomeItem> dataList) {
        super(dataList);
    }

    @Override
    public int getLayoutResource() {
        return 0;
    }


    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }
}
