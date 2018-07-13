package com.trungnguyen.android.houston123.injection;

import android.app.Application;
import android.content.Context;

import com.trungnguyen.android.houston123.HoustonApplication;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

public enum Injector {

    INSTANCE;

    private ApplicationComponent applicationComponent;
    private DataManagerComponent dataManagerComponent;

    public static Injector getInstance() {
        return INSTANCE;
    }

    public void init(Context applicationContext) {

        ApplicationModule applicationModule = new ApplicationModule((Application) applicationContext);
//        ApiModule apiModule = new ApiModule();
        DataManagerModule managerModule = new DataManagerModule();

        initAppComponent(applicationModule, managerModule);
    }

    protected void initAppComponent(ApplicationModule applicationModule, DataManagerModule managerModule) {

        applicationComponent = applicationInjector();

        dataManagerComponent = applicationComponent.plus(managerModule);
    }

    public ApplicationComponent applicationInjector() {
        ApplicationComponent appComponent = DaggerAppComponent.builder().application(this).build();
        appComponent.inject(HoustonApplication.getInstance());
        return appComponent;
    }

    public ApplicationComponent getAppComponent() {
        return applicationComponent;
    }

    public DataManagerComponent getDataManagerComponent() {
        if (dataManagerComponent == null) {
            dataManagerComponent = applicationComponent.plus(new DataManagerModule());
        }
        return dataManagerComponent;
    }

    public void releaseViewModelScope() {
        dataManagerComponent = null;
    }
}
