package com.trungnguyen.android.houston123.injection;

import com.trungnguyen.android.houston123.ui.login.LoginActivity;
import com.trungnguyen.android.houston123.ui.main.MainActivity;
import com.trungnguyen.android.houston123.ui.main.MainActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by trungnd4 on 11/07/2018.
 */
@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity bindMainActivity();

//    @ContributesAndroidInjector(modules = {DetailActivityModule.class, DetailFragmentProvider.class})
//    abstract LoginActivity bindDetailActivity();

}
