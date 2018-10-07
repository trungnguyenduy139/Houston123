package com.trungnguyen.android.houston123.injection.module;

import android.content.Context;
import android.os.Build;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.trungnguyen.android.houston123.BuildConfig;
import com.trungnguyen.android.houston123.network.BaseNetworkInterceptor;
import com.trungnguyen.android.houston123.network.TLSSocketFactory;
import com.trungnguyen.android.houston123.util.Constants;
import com.trungnguyen.android.houston123.util.NamedRetrofitConstants;

import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.ConnectionPool;
import okhttp3.ConnectionSpec;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.TlsVersion;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;


@Module
public class NetworkModule {

    public static final String BASE_URL = "http://10.0.2.2:8000/api/";

    private static final HttpUrl API_HTTP_URL = HttpUrl.parse(BASE_URL);

    @Provides
    @Singleton
    HttpUrl provideBaseUrl() {
        return API_HTTP_URL;
    }

    @Provides
    @Singleton
    Cache provideOkHttpCache(Context application) {
        int cacheSize = 50 * 1024 * 1024; // 10 MiB
        return new Cache(application.getCacheDir(), cacheSize);
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    public X509TrustManager provideX509TrustManager() {
        X509TrustManager x509TrustManager = null;
        try {
            TrustManagerFactory factory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            factory.init((KeyStore) null);
            TrustManager[] trustManagers = factory.getTrustManagers();
            x509TrustManager = (X509TrustManager) trustManagers[0];
        } catch (NoSuchAlgorithmException | KeyStoreException exception) {
            Timber.d("not trust manager available");
        }
        return x509TrustManager;
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Cache cache, X509TrustManager trustManager, BaseNetworkInterceptor baseNetworkInterceptor) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG || BuildConfig.BUILD_TYPE.equals("debugproguard")) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(Timber::i);
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);
        }
        builder.addInterceptor(baseNetworkInterceptor);
        builder.cache(cache);
        builder.connectionPool(new ConnectionPool(Constants.CONNECTION_POOL_COUNT, Constants.CONNECTION_KEEP_ALIVE_DURATION, TimeUnit.MINUTES));
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.readTimeout(15, TimeUnit.SECONDS);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            ConnectionSpec cs = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                    .tlsVersions(TlsVersion.TLS_1_2)
                    .build();

            List<ConnectionSpec> specs = new ArrayList<>();
            specs.add(cs);
            specs.add(ConnectionSpec.COMPATIBLE_TLS);
            specs.add(ConnectionSpec.CLEARTEXT);

            builder.sslSocketFactory(new TLSSocketFactory(), trustManager);
            builder.connectionSpecs(specs);
        }

        return builder.build();
    }

    @Provides
    @Singleton
    @Named(NamedRetrofitConstants.OK_HTTP_TIME_OUT_LOGGER)
    OkHttpClient provideOkHttpClientTimeoutLonger(Cache cache,
                                                  X509TrustManager trustManager) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG || BuildConfig.BUILD_TYPE.equals("debugproguard")) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(Timber::i);
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);
        }
        builder.cache(cache);
        builder.connectionPool(new ConnectionPool(Constants.CONNECTION_POOL_DOWNLOAD_COUNT,
                Constants.CONNECTION_KEEP_ALIVE_DOWNLOAD_DURATION, TimeUnit.MINUTES));
        builder.connectTimeout(30, TimeUnit.SECONDS);
        builder.readTimeout(30, TimeUnit.SECONDS);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            ConnectionSpec cs = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                    .tlsVersions(TlsVersion.TLS_1_2)
                    .build();

            List<ConnectionSpec> specs = new ArrayList<>();
            specs.add(cs);
            specs.add(ConnectionSpec.COMPATIBLE_TLS);
            specs.add(ConnectionSpec.CLEARTEXT);

            builder.sslSocketFactory(new TLSSocketFactory(), trustManager);
            builder.connectionSpecs(specs);
        }

        return builder.build();
    }

    @Provides
    @Singleton
    Converter.Factory providesConvertFactory(Gson gson) {
        return GsonConverterFactory.create(gson);
    }

    @Provides
    @Singleton
    CallAdapter.Factory provideCallAdapter() {
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    @Singleton
    @Named(NamedRetrofitConstants.AUTH_RETROFIT_API)
    Retrofit provideAuthRetrofit(Gson gson, OkHttpClient okHttpClient, CallAdapter.Factory callAdapter) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(callAdapter)
                .baseUrl(BASE_URL)
                .validateEagerly(BuildConfig.DEBUG)
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    @Named(NamedRetrofitConstants.USER_LIST_RETROFIT_API)
    Retrofit provideUserListRetrofit(Gson gson, OkHttpClient okHttpClient, CallAdapter.Factory callAdapter) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(callAdapter)
                .baseUrl(BASE_URL)
                .validateEagerly(BuildConfig.DEBUG)
                .client(okHttpClient)
                .build();
    }

}
