package com.trungnguyen.android.houston123.base;

/**
 * Created by trungnd4 on 17/09/2018.
 */
public interface IBaseLoadingView extends IBaseView {
    void showLoadingDialog();

    void hideLoadingDialog();

    void showErrorDialog(String errorMessage);
}
