package com.trungnguyen.android.houston123.base;

import android.databinding.BaseObservable;

import java.io.Serializable;
import java.util.ArrayList;

import io.reactivex.Observable;

/**
 * Created by trungnd4 on 23/10/2018.
 */
public class BaseModel extends BaseObservable implements Serializable {
    private String mainContent;

    private String subContent;

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
        return Observable.just(new ArrayList<>());
    }
}
