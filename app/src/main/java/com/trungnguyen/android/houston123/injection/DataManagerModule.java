package com.trungnguyen.android.houston123.injection;

import android.content.Context;


import com.trungnguyen.android.houston123.util.Navigator;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataManagerModule {


    @Provides
    @ViewModelScope
    protected Navigator provideNavigator() {
        return new Navigator();
    }

//
//    @Provides
//    @ViewModelScope
//    protected ReceiveDataManager provideReceiveDataManager() {
//        return new ReceiveDataManager();
//    }
//
//    @Provides
//    @ViewModelScope
//    protected AssetsHelper provideWalletAccountHelper(PrefsUtil prefsUtil) {
//        return new AssetsHelper(prefsUtil);
//    }
//
//    @Provides
//    @ViewModelScope
//    protected TransactionListDataManager provideTransactionListDataManager(TransactionListStore transactionListStore) {
//        return new TransactionListDataManager(transactionListStore);
//    }
//
//    @Provides
//    @ViewModelScope
//    protected FingerprintHelper provideFingerprintHelper(Context applicationContext,
//                                                         PrefsUtil prefsUtil) {
//        return new FingerprintHelper(applicationContext, prefsUtil, new FingerprintAuthImpl());
//    }

}
