package com.trungnguyen.android.houston123.util;


import com.trungnguyen.android.houston123.ui.main.home.HomeItem;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;


/**
 * Created by trungnd4 on 19/08/2018.
 */
public final class CommonResourceLoader {

    public Observable<List<HomeItem>> getHomeResource() {
        return Observable.just(new ArrayList<>());
    }
}
