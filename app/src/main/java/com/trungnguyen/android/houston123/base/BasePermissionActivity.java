package com.trungnguyen.android.houston123.base;

import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;

import com.trungnguyen.android.houston123.util.AppLogger;
import com.trungnguyen.android.houston123.util.PermissionUtil;

import java.util.Arrays;

/**
 * Created by trungnd4 on 13/07/2018.
 */
public abstract class BasePermissionActivity<V extends ViewDataBinding, VM extends BaseViewModel>
        extends BaseActivity<V, VM> {

    protected abstract void permissionGranted(int permissionRequestCode, boolean isGranted);

    protected boolean isPermissionGrantedAndRequest(@NonNull String[] permissions, int requestPermissionCode) {
        if (!PermissionUtil.checkAndroidMVersion()) {
            return true;
        }

        int[] selfPermission = PermissionUtil.getSelfPermission(this, permissions);

        if (PermissionUtil.verifyPermission(selfPermission)) {
            return true;
        } else {
            requestPermissions(permissions, requestPermissionCode);
        }

        return false;
    }

    protected boolean isPermissionGrantedAndRequest(String permissions, int requestPermissionCode) {
        return isPermissionGrantedAndRequest(new String[]{permissions}, requestPermissionCode);
    }

    protected boolean isPermissionGranted(@NonNull String[] permissions) {
        if (!PermissionUtil.checkAndroidMVersion()) {
            return true;
        }

        int[] selfPermission = PermissionUtil.getSelfPermission(this, permissions);

        return PermissionUtil.verifyPermission(selfPermission);
    }

    protected boolean isPermissionGranted(String permissions) {
        return isPermissionGranted(new String[]{permissions});
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        AppLogger.d("Permissions result: requestCode [%s] permissions [%s] results[%s]", requestCode, Arrays.toString(permissions), Arrays.toString(grantResults));
        permissionGranted(requestCode, PermissionUtil.verifyPermission(grantResults));
    }
}
