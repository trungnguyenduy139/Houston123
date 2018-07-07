package com.trungnguyen.android.houston123.base;

/**
 * Created by goldze on 2017/6/15.
 */

public interface IBaseViewModel {
//    void initData();

    /**
     */
    void onCreate();

    /**
     */
    void onDestroy();

    /**
     */
    void registerRxBus();
    /**
     */
    void removeRxBus();
}
