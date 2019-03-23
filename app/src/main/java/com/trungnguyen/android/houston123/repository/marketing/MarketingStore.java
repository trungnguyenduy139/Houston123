package com.trungnguyen.android.houston123.repository.marketing;

import com.trungnguyen.android.houston123.data.AnalyzeResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

public class MarketingStore {
    public interface RequestService {
        @GET("analyze_dataset")
        Observable<AnalyzeResponse> analyzeDataSet();
    }

    public interface Repository {
        Observable<AnalyzeResponse> makeAnalyzeRequest();
    }
}
