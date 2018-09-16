package com.trungnguyen.android.houston123.injection;

import android.app.Application;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public enum Injector {

    INSTANCE;

    private ApplicationComponent mApplicationComponent;

    @Nullable
    private DataManagerComponent mDataManagerComponent;

    @NonNull
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

    @NonNull
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
