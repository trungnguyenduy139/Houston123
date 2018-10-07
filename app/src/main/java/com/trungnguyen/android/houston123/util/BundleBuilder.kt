package com.trungnguyen.android.houston123.util

import android.os.Bundle
import org.jetbrains.annotations.NonNls
import org.jetbrains.annotations.Nullable
import java.io.Serializable

/**
 * Created by trungnd4 on 09/07/2018.
 */

class BundleBuilder {

    private var mBundle: Bundle? = null

    constructor() {
        mBundle = Bundle()
    }

    constructor(source: Bundle) {
        mBundle = source
    }

    fun putValue(key: String, value: Any): BundleBuilder {
        when (value) {
            is String -> mBundle?.putString(key, value)
            is Int -> mBundle?.putInt(key, value)
            is Serializable -> mBundle?.putSerializable(key, value)
            // continue update for others type
        }
        return this
    }

    @Nullable
    fun build(): Bundle? {
        return if (mBundle != null) mBundle else null
    }

    // continue update others "put" methods
}
