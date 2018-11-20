package com.trungnguyen.android.houston123.repository.updateuser;

import com.trungnguyen.android.houston123.data.EmptyResponse;
import com.trungnguyen.android.houston123.data.ListBaseResponse;
import com.trungnguyen.android.houston123.repository.IDataFactory;

import io.reactivex.Observable;

/**
 * Created by trungnd4 on 20/11/2018.
 */
public interface UpdateUserFactory extends IDataFactory {
    Observable<? extends ListBaseResponse<? extends EmptyResponse>> handleUpdateRepositoryFlow(int code, String id);
}
