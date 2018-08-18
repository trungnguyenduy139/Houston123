package com.trungnguyen.android.houston123.injection;


import android.support.annotation.NonNull;

import com.trungnguyen.android.houston123.ui.listuser.UserListViewModel;
import com.trungnguyen.android.houston123.ui.login.LoginViewModel;
import com.trungnguyen.android.houston123.ui.main.MainViewModel;
import com.trungnguyen.android.houston123.ui.main.home.HomeViewModel;
import com.trungnguyen.android.houston123.ui.userdetail.DetailUserViewModel;

import dagger.Subcomponent;

/**
 * Subcomponents have access to all upstream objects in the graph but can have their own scope -
 * they don't need to explicitly state their dependencies as they have access anyway
 */
@ViewModelScope
@Subcomponent(modules = DataManagerModule.class)
public interface DataManagerComponent {

    @Subcomponent.Builder
    interface Builder {
        @NonNull
        DataManagerComponent build();
    }

    void inject(MainViewModel mainViewModel);

    void inject(LoginViewModel loginViewModel);

    void inject(UserListViewModel userListViewModel);

    void inject(DetailUserViewModel detailUserViewModel);

    void inject(HomeViewModel homeViewModel);

}
