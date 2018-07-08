package com.trungnguyen.android.houston123.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.trungnguyen.android.houston123.BuildConfig;
import com.trungnguyen.android.houston123.injection.Injector;

/**
 * Created by goldze on 2017/6/15.
 */

public class BaseApplication extends Application {

    private static BaseApplication sInstance;
    private static RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;
        Injector.getInstance().init(this);

        registerActivityLifecycleCallbacks(mCallbacks);

        if (isDebugBuild()) {
            StrictMode.enableDefaults();
        }

        initLeakCanary();

    }

    @NonNull
    public static boolean isDebugBuild() {
		return BuildConfig.BUILD_TYPE.equals("debug");
    }

    private void initLeakCanary() {
        if (!LeakCanary.isInAnalyzerProcess(this)) {
            refWatcher = LeakCanary.install(this);
        }
    }

    public static RefWatcher getRefWatcher() {
        return BaseApplication.refWatcher;
    }


    public static BaseApplication getInstance() {
        return sInstance;
    }

    private ActivityLifecycleCallbacks mCallbacks = new ActivityLifecycleCallbacks() {

        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            AppManager.getAppManager().addActivity(activity);
        }

        @Override
        public void onActivityStarted(Activity activity) {
        }

        @Override
        public void onActivityResumed(Activity activity) {
        }

        @Override
        public void onActivityPaused(Activity activity) {
        }

        @Override
        public void onActivityStopped(Activity activity) {
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            AppManager.getAppManager().removeActivity(activity);
        }
    };
}
