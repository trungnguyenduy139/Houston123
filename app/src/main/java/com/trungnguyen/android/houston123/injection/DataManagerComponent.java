package com.trungnguyen.android.houston123.injection;


import android.support.annotation.NonNull;

import com.trungnguyen.android.houston123.injection.module.DataManagerModule;
import com.trungnguyen.android.houston123.injection.module.LoginModule;
import com.trungnguyen.android.houston123.injection.module.UserListModule;
import com.trungnguyen.android.houston123.ui.addto.SearchAndAddToActivity;
import com.trungnguyen.android.houston123.ui.listuser.UserListActivity;
import com.trungnguyen.android.houston123.ui.login.LoginActivity;
import com.trungnguyen.android.houston123.ui.main.ChangePasswordActivity;
import com.trungnguyen.android.houston123.ui.main.MainActivity;
import com.trungnguyen.android.houston123.ui.main.home.HomeFragment;
import com.trungnguyen.android.houston123.ui.main.personal.PersonalFragment;
import com.trungnguyen.android.houston123.ui.main.tool.ToolFragment;
import com.trungnguyen.android.houston123.ui.register.RegisterUserActivity;
import com.trungnguyen.android.houston123.ui.updateaccount.UpdateAccountActivity;
import com.trungnguyen.android.houston123.ui.userdetail.DetailUserActivity;

import dagger.Subcomponent;

/**
 * Subcomponents have access to all upstream objects in the graph but can have their own scope -
 * they don't need to explicitly state their dependencies as they have access anyway
 */
@ViewModelScope
@Subcomponent(modules = {
        DataManagerModule.class,
        LoginModule.class,
        UserListModule.class
})
public interface DataManagerComponent {

    @Subcomponent.Builder
    interface Builder {

        @NonNull
        DataManagerComponent build();
    }

    void inject(UserListActivity activity);

    void inject(RegisterUserActivity activity);

    void inject(SearchAndAddToActivity activity);

    void inject(UpdateAccountActivity activity);

    void inject(MainActivity activity);

    void inject(LoginActivity activity);

    void inject(DetailUserActivity activity);

    void inject(HomeFragment fragment);

    void inject(ToolFragment fragment);

    void inject(PersonalFragment fragment);

    void inject(ChangePasswordActivity activity);
}
