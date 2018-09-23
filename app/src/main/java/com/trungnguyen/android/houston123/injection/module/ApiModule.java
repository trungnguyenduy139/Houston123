package com.trungnguyen.android.houston123.injection.module;

import com.trungnguyen.android.houston123.repository.interceptor.Authenticate;
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
    static protected Authenticate.RequestService provideAuthRetrofitApi(@Named(NamedRetrofitConstants.AUTH_RETROFIT_API) Retrofit retrofit) {
        return retrofit.create(Authenticate.RequestService.class);
    }
}
