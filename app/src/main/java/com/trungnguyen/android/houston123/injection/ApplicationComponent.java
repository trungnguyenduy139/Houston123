package com.trungnguyen.android.houston123.injection;

import com.trungnguyen.android.houston123.ui.main.MainViewModel;

import javax.inject.Singleton;

import dagger.Component;

@SuppressWarnings("WeakerAccess")
@Singleton
@Component(modules = {
        ApplicationModule.class,
        ApiModule.class
})
public interface ApplicationComponent {

    DataManagerComponent plus(DataManagerModule userModule);




//    void inject(AppUtil appUtil);
//
//    void inject(LoggingExceptionHandler loggingExceptionHandler);
//
//    void inject(EnvironmentManager environmentManager);
//
//    RxEventBus eventBus();

}
