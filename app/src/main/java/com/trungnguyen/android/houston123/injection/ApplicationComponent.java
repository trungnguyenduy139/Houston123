package com.trungnguyen.android.houston123.injection;

import android.app.Application;
import android.support.annotation.NonNull;

import com.trungnguyen.android.houston123.HoustonApplication;
import com.trungnguyen.android.houston123.injection.module.ApiModule;
import com.trungnguyen.android.houston123.injection.module.ApplicationModule;
import com.trungnguyen.android.houston123.injection.module.NetworkModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        NetworkModule.class,
        ApiModule.class
})
public interface ApplicationComponent {

    void inject(HoustonApplication app);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        @NonNull
        ApplicationComponent build();
    }

    @NonNull
    DataManagerComponent.Builder dataManagerComponentBuilder();

    UserComponent.Builder userComponentBuilder();
}
