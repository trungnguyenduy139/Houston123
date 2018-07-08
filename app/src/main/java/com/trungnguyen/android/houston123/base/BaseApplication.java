package com.trungnguyen.android.houston123.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.squareup.leakcanary.LeakCanary;
import com.trungnguyen.android.houston123.injection.Injector;

/**
 * Created by goldze on 2017/6/15.
 */

public class BaseApplication extends Application {
    private static BaseApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);

        sInstance = this;
        Injector.getInstance().init(this);

        registerActivityLifecycleCallbacks(mCallbacks);
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
