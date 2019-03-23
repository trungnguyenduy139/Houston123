package com.trungnguyen.android.houston123.ui.main.tool;

import android.content.Context;

import com.trungnguyen.android.houston123.anotation.OnClick;
import com.trungnguyen.android.houston123.base.BaseViewModel;
import com.trungnguyen.android.houston123.data.AnalyzeResponse;
import com.trungnguyen.android.houston123.data.ColumnsData;
import com.trungnguyen.android.houston123.data.SummaryData;
import com.trungnguyen.android.houston123.repository.marketing.AnalyzeDataRepository;
import com.trungnguyen.android.houston123.rx.SchedulerHelper;
import com.trungnguyen.android.houston123.util.Navigator;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import timber.log.Timber;

/**
 * Created by trungnd4 on 19/08/2018.
 */
public class ToolViewModel extends BaseViewModel<IToolView> {

    private Context mContext;
    private Navigator mNavigator;
    private AnalyzeDataRepository mRepository;
    private AnalyzeResponse mResponse = new AnalyzeResponse();

    @Inject
    public ToolViewModel(Context context, Navigator navigator, AnalyzeDataRepository repository) {
        mContext = context;
        this.mNavigator = navigator;
        this.mRepository = repository;
    }

    @OnClick
    public void onClickFacebook() {
        if (mView == null) {
            return;
        }
        ColumnsData columnsData = mResponse.facebook.columns;
        SummaryData summaryData = mResponse.facebook.summary;
    }

    @OnClick
    public void onClickEmail() {
        if (mView == null) {
            return;
        }
        ColumnsData columnsData = mResponse.email.columns;
        SummaryData summaryData = mResponse.email.summary;
    }

    @OnClick
    public void onClickPoster() {
        if (mView == null) {
            return;
        }
        ColumnsData columnsData = mResponse.poster.columns;
        SummaryData summaryData = mResponse.poster.summary;
    }

    @OnClick
    public void onClickPhoneCall() {
        if (mView == null) {
            return;
        }
        ColumnsData columnsData = mResponse.phoneCall.columns;
        SummaryData summaryData = mResponse.phoneCall.summary;
    }


    @OnClick
    public void loadAnalyze() {
        Disposable subscription = mRepository.makeAnalyzeRequest()
                .compose(SchedulerHelper.applySchedulers())
                .doOnSubscribe(disposable -> showLoading())
                .doOnTerminate(this::hideLoading)
                .subscribe(analyzeResponse -> mResponse = analyzeResponse, Timber::d);
        mSubscription.add(subscription);
    }
}
