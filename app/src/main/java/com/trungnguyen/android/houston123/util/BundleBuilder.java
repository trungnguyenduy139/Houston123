package com.trungnguyen.android.houston123.util;

import android.os.Bundle;

/**
 * Created by trungnd4 on 09/07/2018.
 */

public class BundleBuilder {

    private Bundle mBundle;

    public BundleBuilder() {
        mBundle = new Bundle();
    }

    public BundleBuilder(Bundle source) {
        mBundle = source;
    }

    public BundleBuilder putString(String key, String value) {
        mBundle.putString(key, value);
        return this;
    }

    public Bundle build() {
        if (mBundle != null)
            return mBundle;
        return null;
    }

    // continue update others "put" methods
}
