package com.trungnguyen.android.houston123.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by trungnd4 on 07/10/2018.
 */

public class ListBaseResponse<U extends BaseUserResponse> {
    @SerializedName("current_page")
    private int page;
    @SerializedName("data")
    private List<U> dataList;

    public int getPage() {
        return this.page;
    }

    public List<U> getDataList() {
        return this.dataList;
    }
}
