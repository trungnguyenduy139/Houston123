package com.trungnguyen.android.houston123.injection.module;

import android.support.annotation.NonNull;

import com.trungnguyen.android.houston123.injection.ViewModelScope;
import com.trungnguyen.android.houston123.repository.IDataFactory;
import com.trungnguyen.android.houston123.repository.updateuser.UpdateUserFactoryImpl;
import com.trungnguyen.android.houston123.repository.updateuser.UpdateUserStore;
import com.trungnguyen.android.houston123.repository.userlist.UserListFactoryImpl;
import com.trungnguyen.android.houston123.repository.userlist.UserListLocalStorage;
import com.trungnguyen.android.houston123.repository.userlist.UserListStore;
import com.trungnguyen.android.houston123.util.PrefsUtil;

import javax.inject.Named;

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
    @Named("userListFactory")
    static IDataFactory provideDataFactory(UserListStore.RequestService requestService) {
        return new UserListFactoryImpl(requestService);
    }

    @Provides
    @NonNull
    @ViewModelScope
    @Named("updateUserFactory")
    static IDataFactory provideUpdateUserFactory(UpdateUserStore.RequestService requestService) {
        return new UpdateUserFactoryImpl(requestService);
    }
}
