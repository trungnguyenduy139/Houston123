package com.trungnguyen.android.houston123.base;

import android.databinding.BaseObservable;

import com.trungnguyen.android.houston123.ui.userdetail.ItemDetailModel;

import java.io.Serializable;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by trungnd4 on 23/10/2018.
 */
public abstract class BaseModel extends BaseObservable implements Serializable {
    private String mainContent;

    private String subContent;

    public BaseModel() {

    }

    public BaseModel(String mainContent, String subContent) {
        this.mainContent = mainContent;
        this.subContent = subContent;
    }

    public String getMainContent() {
        return mainContent;
    }

    public String getSubCotent() {
        return subContent;
    }

    public Observable convert() {
        Observable<String> observableResource = Observable.just(this.getSource())
                .flatMapIterable(items -> items)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io());

        Observable<String> observableValue = Observable.just(this.getValue())
                .flatMapIterable(items -> items)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io());

        return Observable.zip(observableResource, observableValue, (ItemDetailModel::new))
                .doOnNext(itemDetailModel -> Timber.d("[DetailUser] Print item detail mode %s", itemDetailModel.getValue()))
                .toList()
                .toObservable();
    }

    public abstract List<String> getSource();

    public abstract List<String> getValue();
}
