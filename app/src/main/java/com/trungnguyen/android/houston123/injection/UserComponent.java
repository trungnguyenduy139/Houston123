package com.trungnguyen.android.houston123.injection;

import android.support.annotation.NonNull;

import com.trungnguyen.android.houston123.data.LoginInfoModel;
import com.trungnguyen.android.houston123.injection.module.UserModule;

import dagger.BindsInstance;
import dagger.Subcomponent;

/**
 * Created by trungnd4 on 07/11/2018.
 */

@UserScope
@Subcomponent(modules = {
        UserModule.class
})
public interface UserComponent {

    @Subcomponent.Builder
    interface Builder {

        @BindsInstance
        UserComponent.Builder initUser(LoginInfoModel model);

        @NonNull
        UserComponent build();
    }

    LoginInfoModel getLoginModel();
}
