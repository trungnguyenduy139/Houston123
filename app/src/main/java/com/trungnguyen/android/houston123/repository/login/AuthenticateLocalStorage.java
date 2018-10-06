package com.trungnguyen.android.houston123.repository.login;

import android.text.TextUtils;

import com.trungnguyen.android.houston123.util.PreferencesConst;
import com.trungnguyen.android.houston123.util.PrefsUtil;
import com.trungnguyen.android.houston123.util.Security;

import io.reactivex.Observable;
import timber.log.Timber;

/**
 * Created by trungnd4 on 23/09/2018.
 */
public class AuthenticateLocalStorage implements AuthenticateStore.LocalStorage {

    private PrefsUtil mPreferences;
    private Security mSecurity;

    public AuthenticateLocalStorage(PrefsUtil prefsUtil, Security security) {
        this.mPreferences = prefsUtil;
        this.mSecurity = security;
    }

    @Override
    public void putSafeAccessToken(String accessToken) {
        try {
            String encryptToken = mSecurity.encrypt(accessToken);
            mPreferences.saveStringPreferences(PreferencesConst.ACCESS_TOKEN_PREF, encryptToken);
        } catch (Exception e) {
            Timber.d("Error when PUT safe Access token in Local storage");
        }
    }

    @Override
    public String getSafeAccessToken() {
        try {
            String encryptToken = mPreferences.getStringPreferences(PreferencesConst.ACCESS_TOKEN_PREF);
            if (TextUtils.isEmpty(encryptToken)) {
                return mPreferences.EMPTY_STR;
            }
            return mSecurity.decrypt(encryptToken);
        } catch (Exception e) {
            Timber.d("Error when GET safe Access token in Local storage");
            return mPreferences.EMPTY_STR;

        }
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
