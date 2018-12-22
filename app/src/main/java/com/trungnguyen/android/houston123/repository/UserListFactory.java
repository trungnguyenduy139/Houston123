package com.trungnguyen.android.houston123.repository;

import com.trungnguyen.android.houston123.data.EmptyResponse;
import com.trungnguyen.android.houston123.data.ListBaseResponse;

import io.reactivex.Observable;

/**
 * Created by trungnd4 on 20/11/2018.
 */
public interface UserListFactory extends IDataFactory {
    String getDataType(int code);

    Observable<? extends ListBaseResponse<? extends EmptyResponse>> getUserFlow(int api, int page);

    Observable<? extends ListBaseResponse<? extends EmptyResponse>> getSearchUserFlow(int api, String textSearch);
}
