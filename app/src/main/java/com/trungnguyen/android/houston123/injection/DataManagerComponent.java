package com.trungnguyen.android.houston123.injection;


import com.trungnguyen.android.houston123.ui.main.MainViewModel;

import dagger.Subcomponent;

/**
 * Subcomponents have access to all upstream objects in the graph but can have their own scope -
 * they don't need to explicitly state their dependencies as they have access anyway
 */
@SuppressWarnings("WeakerAccess")
@ViewModelScope
@Subcomponent(modules = DataManagerModule.class)
public interface DataManagerComponent {

    void inject(MainViewModel mainViewModel);

//
//    void inject(AddMarketViewModel addMarketViewModel);
//
//    void inject(OrderBookViewModel orderBookViewModel);
//
//    void inject(WatchlistMarketsViewModel watchlistMarketsViewModel);
//
//    void inject(MarketsViewModel marketsViewModel);
//
//    void inject(SendViewModel sendViewModel);
//
//    void inject(ChartViewModel chartViewModel);
//
//    void inject(PinEntryViewModel pinEntryViewModel);
//
//    void inject(MainViewModel mainViewModel);
//
//    void inject(TransactionsViewModel transactionsViewModel);
//
//    void inject(PairingViewModel pairingViewModel);
//
//    void inject(ReceiveViewModel receiveViewModel);
//
//    void inject(TransactionDetailViewModel transactionDetailViewModel);
//
//    void inject(FingerprintDialogViewModel fingerprintDialogViewModel);
//
//    void inject(LandingViewModel landingViewModel);
//
//    void inject(AddressBookManager addressBookManager);
//
//    void inject(IssueDetailViewModel issueDetailViewModel);
//
//    void inject(IssueViewModel issueViewModel);
//
//    void inject(ReissueDetailViewModel reissueDetailViewModel);
//
//    void inject(ExchangeTransactionDetailViewModel exchangeTransactionDetailViewModel);
//
//    void inject(UnknownDetailViewModel unknownDetailViewModel);
//
//    void inject(PlaceOrderViewModel placeOrderViewModel);
//
//    void inject(DexDetailsViewModel dexDetailsViewModel);
//
//    void inject(LastTradesViewModel lastTradesViewModel);
//
//    void inject(MyOrdersViewModel myOrdersViewModel);
}
