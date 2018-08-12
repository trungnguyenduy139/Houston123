package com.trungnguyen.android.houston123.base;

/**
 * Created by goldze on 2017/6/15.
 */

public interface IBaseViewModel<View> {
//    void initData();

    /**
     */
    void onCreate(View view);

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
