package com.trungnguyen.android.houston123.ui.main.home;

import com.trungnguyen.android.houston123.base.BaseModel;
import com.trungnguyen.android.houston123.base.IBaseLoadingView;

import java.util.Collection;
import java.util.List;

/**
 * Created by trungnd4 on 20/08/2018.
 */
public interface IHomeView extends IBaseLoadingView {
    void onLoadHomeResourcesCompleted(List<HomeItem> homeItems);

    void failedToLoadUsers(Throwable throwable);

    void successToLoadUsers(int code, Collection<? extends BaseModel> userModels);
}
