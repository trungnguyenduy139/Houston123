package com.trungnguyen.android.houston123.injection.module;

import com.trungnguyen.android.houston123.repository.login.AuthenticateStore;
import com.trungnguyen.android.houston123.repository.marketing.MarketingStore;
import com.trungnguyen.android.houston123.repository.updateuser.UpdateUserStore;
import com.trungnguyen.android.houston123.repository.userlist.UserListStore;
import com.trungnguyen.android.houston123.util.NamedRetrofitConstants;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public abstract class ApiModule {

    @Provides
    @Singleton
    static protected AuthenticateStore.RequestService provideAuthRetrofitApi(@Named(NamedRetrofitConstants.AUTH_RETROFIT_API) Retrofit retrofit) {
        return retrofit.create(AuthenticateStore.RequestService.class);
    }


    @Provides
    @Singleton
    static protected UserListStore.RequestService provideUserListRetrofitApi(@Named(NamedRetrofitConstants.USER_LIST_RETROFIT_API) Retrofit retrofit) {
        return retrofit.create(UserListStore.RequestService.class);
    }

    @Provides
    @Singleton
    static protected UpdateUserStore.RequestService provideUpdateUserRetrofitApi(@Named(NamedRetrofitConstants.UPDATE_USER_RETROFIT_API) Retrofit retrofit) {
        return retrofit.create(UpdateUserStore.RequestService.class);
    }


    @Provides
    @Singleton
    static protected MarketingStore.RequestService provideMarketingRetrofitApi(@Named(NamedRetrofitConstants.MARKETING_RETROFIT_API) Retrofit retrofit) {
        return retrofit.create(MarketingStore.RequestService.class);
    }
}
