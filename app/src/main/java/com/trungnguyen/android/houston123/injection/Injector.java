package com.trungnguyen.android.houston123.injection;

import android.app.Application;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.trungnguyen.android.houston123.data.LoginInfoModel;

public enum Injector {

    INSTANCE;

    private ApplicationComponent mApplicationComponent;

    @Nullable
    private DataManagerComponent mDataManagerComponent;

    @Nullable
    private UserComponent mUserComponent;

    @NonNull
    public static Injector getInstance() {
        return INSTANCE;
    }

    public void init(Application applicationContext) {
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

    @NonNull
    public void createUserScope(LoginInfoModel model) {
        mUserComponent = mApplicationComponent.userComponentBuilder().initUser(model).build();
    }


    @Nullable
    public UserComponent getUserComponent() {
        if (mUserComponent != null) {
            return mUserComponent;
        }
        return null;
    }

    public void releaseViewModelScope() {
        mDataManagerComponent = null;
    }

    public void releaseUserScope() {
        mUserComponent = null;
    }

}
