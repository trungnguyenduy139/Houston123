package com.trungnguyen.android.houston123.injection.module;

/**
 * Created by trungnd4 on 23/09/2018.
 */

import com.trungnguyen.android.houston123.injection.ViewModelScope;
import com.trungnguyen.android.houston123.repository.login.AuthenticateLocalStorage;
import com.trungnguyen.android.houston123.repository.login.AuthenticateStore;
import com.trungnguyen.android.houston123.util.PrefsUtil;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class LoginModule {

    @Provides
    @ViewModelScope
    static protected AuthenticateStore.LocalStorage provideAuthenticateRepository(PrefsUtil prefsUtil) {
        return new AuthenticateLocalStorage(prefsUtil);
    }

}