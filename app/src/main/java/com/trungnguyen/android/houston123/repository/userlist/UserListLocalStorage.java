package com.trungnguyen.android.houston123.repository.userlist;

import com.trungnguyen.android.houston123.util.PreferencesConst;
import com.trungnguyen.android.houston123.util.PrefsUtil;

/**
 * Created by trungnd4 on 08/10/2018.
 */
public class UserListLocalStorage implements UserListStore.LocalStorage {

    private PrefsUtil mPrefsUtil;

    public UserListLocalStorage(PrefsUtil prefsUtil) {
        this.mPrefsUtil = prefsUtil;
    }

    @Override
    public void putCurrentListPageLocal(int page) {
        mPrefsUtil.saveIntPreferences(PreferencesConst.LIST_CURRENT_PAGE, page);
    }

    @Override
    public int getCurrentPage() {
        return mPrefsUtil.getIntPreferences(PreferencesConst.LIST_CURRENT_PAGE, 1);
    }

    @Override
    public void putHasLoader(boolean empty) {
        mPrefsUtil.saveBoolPreferences(PreferencesConst.HAS_LOADER_PREF, empty);
    }

    @Override
    public boolean getHasLoader() {
        return mPrefsUtil.getBoolPreferences(PreferencesConst.HAS_LOADER_PREF, true);
    }
}
