package com.trungnguyen.android.houston123.ui.login;

import android.os.Bundle;

import com.trungnguyen.android.houston123.base.IBaseLoadingView;

/**
 * Created by trungnd4 on 13/08/2018.
 */
public interface ILoginView extends IBaseLoadingView {
    void onAuthSuccess(String accessToken, Class callToClz, Bundle bundle);

    void onAuthFailed();

    void showNetworkDialog();
}
