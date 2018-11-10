package com.trungnguyen.android.houston123.injection.module;

import android.support.annotation.NonNull;

import com.trungnguyen.android.houston123.injection.ViewModelScope;
import com.trungnguyen.android.houston123.repository.IDataFactory;
import com.trungnguyen.android.houston123.repository.userlist.UserListFactory;
import com.trungnguyen.android.houston123.repository.userlist.UserListLocalStorage;
import com.trungnguyen.android.houston123.repository.userlist.UserListStore;
import com.trungnguyen.android.houston123.util.PrefsUtil;

import dagger.Module;
import dagger.Provides;

/**
 * Created by trungnd4 on 08/10/2018.
 */
@Module
public abstract class UserListModule {
    @Provides
    @ViewModelScope
    static protected UserListStore.LocalStorage provideUserListLocalStorage(PrefsUtil prefsUtil) {
        return new UserListLocalStorage(prefsUtil);
    }

    @Provides
    @NonNull
    @ViewModelScope
    static IDataFactory provideDataFactory(UserListStore.RequestService requestService) {
        return new UserListFactory(requestService);
    }
}
