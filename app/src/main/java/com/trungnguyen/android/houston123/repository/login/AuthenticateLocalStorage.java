package com.trungnguyen.android.houston123.repository.login;

import com.trungnguyen.android.houston123.util.PreferencesConst;
import com.trungnguyen.android.houston123.util.PrefsUtil;

import io.reactivex.Observable;

/**
 * Created by trungnd4 on 23/09/2018.
 */
public class AuthenticateLocalStorage implements AuthenticateStore.LocalStorage {
    private PrefsUtil mPreferences;

    public AuthenticateLocalStorage(PrefsUtil prefsUtil) {
        this.mPreferences = prefsUtil;
    }

    @Override
    public Observable<Boolean> getLoginStatus() {
        boolean state = mPreferences.getBoolPreferences(PreferencesConst.LOGIN_STATUS);
        return Observable.just(state);
    }

    @Override
    public Observable<Boolean> setLoginState(boolean state) {
        mPreferences.saveBoolPreferences(PreferencesConst.LOGIN_STATUS, state);
        return Observable.just(state);
    }
}
