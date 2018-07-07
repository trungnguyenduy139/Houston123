package com.trungnguyen.android.houston123.injection;

import android.app.Application;
import android.content.Context;


import com.trungnguyen.android.houston123.util.Navigator;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    protected Context provideApplicationContext() {
        return mApplication;
    }


//
//    @Provides
//    @Singleton
//    protected AppUtil provideAppUtil() {
//        return new AppUtil(mApplication);
//    }
//
//    @Provides
//    protected AccessState provideAccessState() {
//        return AccessState.getInstance();
//    }
//
//    @Provides
//    protected AESUtilWrapper provideAesUtils() {
//        return new AESUtilWrapper();
//    }
//
//    @Provides
//    protected StringUtils provideStringUtils() {
//        return new StringUtils(mApplication);
//    }

}
