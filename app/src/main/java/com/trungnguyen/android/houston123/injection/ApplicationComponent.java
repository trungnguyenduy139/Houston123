package com.trungnguyen.android.houston123.injection;

import com.trungnguyen.android.houston123.HoustonApplication;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@SuppressWarnings("WeakerAccess")
@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        ApplicationModule.class,
        ActivityBuilder.class
})
public interface ApplicationComponent extends AndroidInjector<HoustonApplication> {

    DataManagerComponent plus(DataManagerModule userModule);

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<HoustonApplication> {
    }

//    void inject(AppUtil appUtil);
//
//    void inject(LoggingExceptionHandler loggingExceptionHandler);
//
//    void inject(EnvironmentManager environmentManager);
//
//    RxEventBus eventBus();

}
