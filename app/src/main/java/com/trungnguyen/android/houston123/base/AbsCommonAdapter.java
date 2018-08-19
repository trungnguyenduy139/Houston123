package com.trungnguyen.android.houston123.base;

import android.support.annotation.Nullable;

import java.util.List;

/**
 * Created by trungnd4 on 19/08/2018.
 */

public abstract class AbsCommonAdapter<L extends IAdapterListener, I> extends BaseInfinityAdapter<L> {

    protected List<I> mDataList;

    public AbsCommonAdapter(List<I> dataList) {
        mDataList = dataList;
    }

    public void addItems(@Nullable List<I> datas) {
        if (datas == null) {
            return;
        }
        if (mDataList == null) {
            return;
        }
        mDataList.addAll(datas);
        notifyDataSetChanged();
    }

    public void clearItems() {
        if (mDataList == null || mDataList.size() == 0) {
            return;
        }
        mDataList.clear();
    }

    @Override
    public boolean enableInfinity() {
        return false;
    }

    @Override
    public int getDataSize() {
        if (mDataList == null) {
            return 0;
        }
        return mDataList.size();
    }

}
