package com.trungnguyen.android.houston123.injection;

import android.app.Application;

public enum Injector {

    INSTANCE;

    private ApplicationComponent mApplicationComponent;

    private DataManagerComponent mDataManagerComponent;

    public static Injector getInstance() {
        return INSTANCE;
    }

    public void init(Application applicationContext) {

        initAppComponent(applicationContext);
    }

    protected void initAppComponent(Application applicationContext) {

        mApplicationComponent = DaggerApplicationComponent.builder()
                .application(applicationContext)
                .build();

        mDataManagerComponent = getDataManagerComponent();
    }

    public ApplicationComponent getAppComponent() {
        return mApplicationComponent;
    }

    public DataManagerComponent getDataManagerComponent() {
        if (mDataManagerComponent == null) {
            mDataManagerComponent = mApplicationComponent.dataManagerComponentBuilder().build();
        }
        return mDataManagerComponent;
    }

    public void releaseViewModelScope() {
        mDataManagerComponent = null;
    }
}
