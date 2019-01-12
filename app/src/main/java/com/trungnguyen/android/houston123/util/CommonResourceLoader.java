package com.trungnguyen.android.houston123.util;


import android.content.Context;

import com.trungnguyen.android.houston123.R;
import com.trungnguyen.android.houston123.anotation.UserType;
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
                R.drawable.lecture, R.drawable.lecture, R.drawable.lecture};
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

    public String getToolbarTitle(Context context, int code) {
        switch (code) {
            case UserType.LECTURER:
                return context.getString(R.string.lecturer);
            case UserType.MANAGER:
                return context.getString(R.string.manager);
            case UserType.STUDENT:
                return context.getString(R.string.student);
            case UserType.CLAZZ:
                return context.getString(R.string.clazz);
            case UserType.SUBJECT:
                return context.getString(R.string.subject);
            default:
                return context.getString(R.string.user_list);
        }
    }
}
