package com.trungnguyen.android.houston123.util;


import android.content.Context;

import com.trungnguyen.android.houston123.R;
import com.trungnguyen.android.houston123.ui.main.home.HomeItem;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;


/**
 * Created by trungnd4 on 19/08/2018.
 */
public final class CommonResourceLoader {

    public Observable<List<HomeItem>> getHomeResource(Context context) {
        int[] imgResArr = {R.drawable.lecture, R.drawable.lecture,
                R.drawable.lecture, R.drawable.lecture, R.drawable.lecture,
                R.drawable.lecture, R.drawable.lecture, R.drawable.lecture, R.drawable.lecture};
        String[] titleResArr = context.getResources().getStringArray(R.array.home_resources_array);

        if (imgResArr.length != titleResArr.length) {
            return Observable.just(new ArrayList<>());
        }

        List<HomeItem> homeItems = new ArrayList<>();

        for (int index = 0; index < imgResArr.length; index++) {
            homeItems.add(new HomeItem(titleResArr[index], imgResArr[index]));
        }

        return Observable.just(homeItems);
    }
}
