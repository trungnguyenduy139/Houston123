package com.trungnguyen.android.houston123.repository;

import com.trungnguyen.android.houston123.data.EmptyResponse;
import com.trungnguyen.android.houston123.data.ListBaseResponse;

import io.reactivex.Observable;

/**
 * Created by trungnd4 on 11/11/2018.
 */
public interface IDataFactory {
    String getDataType(int code);

    Observable<? extends ListBaseResponse<? extends EmptyResponse>> getUserFlow(int api, int page);
}
