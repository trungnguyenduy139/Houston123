package com.trungnguyen.android.houston123.injection.module;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;


import com.trungnguyen.android.houston123.util.CommonResourceLoader;
import com.trungnguyen.android.houston123.util.PrefsUtil;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class ApplicationModule {

    @Provides
    @Singleton
    static protected Context provideApplicationContext(Application application) {
        return application;
    }

    @NonNull
    @Provides
    @Singleton
    static protected PrefsUtil providePrefsUtil(Context context) {
        return new PrefsUtil(context);
    }

    @Provides
    @NonNull
    @Singleton
    static protected CommonResourceLoader providesCommonResourceLoader() {
        return new CommonResourceLoader();
    }
}
