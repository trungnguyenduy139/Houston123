package com.trungnguyen.android.houston123.ui.main;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by trungnd4 on 11/07/2018.
 */

@Module
public abstract class MainActivityModule {

    @Provides
    MainViewModel provideMainViewModel(Context context) {
        return new MainViewModel(context);
    }
}
