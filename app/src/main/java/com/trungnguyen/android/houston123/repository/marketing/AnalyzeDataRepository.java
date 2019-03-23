package com.trungnguyen.android.houston123.repository.marketing;

import com.trungnguyen.android.houston123.data.AnalyzeResponse;

import javax.inject.Inject;

import io.reactivex.Observable;

public class AnalyzeDataRepository implements MarketingStore.Repository {
    private MarketingStore.RequestService mService;

    @Inject
    public AnalyzeDataRepository(MarketingStore.RequestService requestService) {
        mService = requestService;
    }

    @Override
    public Observable<AnalyzeResponse> makeAnalyzeRequest() {
        return mService.analyzeDataSet();
    }
}
