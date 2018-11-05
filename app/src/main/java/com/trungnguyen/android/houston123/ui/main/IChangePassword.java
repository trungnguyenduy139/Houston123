package com.trungnguyen.android.houston123.ui.main;

import com.trungnguyen.android.houston123.base.IBaseView;

/**
 * Created by trungnd4 on 05/11/2018.
 */
public interface IChangePassword extends IBaseView {
    void onVerifyPasswordFailed();

    void onChangePasswordSuccess(String message);

    void onChangePasswordFailed(String message);
}
