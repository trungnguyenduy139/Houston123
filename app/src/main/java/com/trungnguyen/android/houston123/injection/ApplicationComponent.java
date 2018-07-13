package com.trungnguyen.android.houston123.injection;

import android.app.Application;
import android.support.annotation.NonNull;

import com.trungnguyen.android.houston123.HoustonApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@SuppressWarnings("WeakerAccess")
@Singleton
@Component(modules = {
        ApplicationModule.class,
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

    DataManagerComponent.Builder dataManagerComponentBuilder();
}
