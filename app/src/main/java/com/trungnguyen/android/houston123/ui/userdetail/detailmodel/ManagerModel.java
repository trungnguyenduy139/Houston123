package com.trungnguyen.android.houston123.ui.userdetail.detailmodel;


import android.os.Build;
import android.support.annotation.RequiresApi;

import com.trungnguyen.android.houston123.base.BaseUserModel;
import com.trungnguyen.android.houston123.ui.userdetail.ItemDetailModel;
import com.trungnguyen.android.houston123.util.ModelResourceLoader;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by trungnd4 on 14/08/2018.
 */
public class ManagerModel extends BaseUserModel {

    private String lecturerId;
    private String img;
    private String address;
    private String email;
    private String cmnd;
    private String outDate;
    private String outReason;
    private String department;
    private String position;


    public ManagerModel(String name, String phone, String lecturerId, String img, String address, String email, String cmnd, String outDate, String outReason, String department, String position) {
        super(name, phone);
        this.lecturerId = lecturerId;
        this.img = img;
        this.address = address;
        this.email = email;
        this.cmnd = cmnd;
        this.outDate = outDate;
        this.outReason = outReason;
        this.department = department;
        this.position = position;
    }

    public String getOutDate() {
        return outDate != null ? outDate : "";
    }

    public String getOutReason() {
        return outReason != null ? outReason : "";
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public Observable<List<ItemDetailModel>> convert() {
        Observable<String> observableResource = Observable.just(ModelResourceLoader.loadResourceManager())
                .flatMapIterable(items -> items)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io());

        Observable<String> observableValue = Observable.just(Arrays.asList(lecturerId, name, phoneNumber, address, email, cmnd, position, getOutDate(), getOutReason(), department))
                .flatMapIterable(items -> items)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io());


        return Observable.zip(observableResource, observableValue, (ItemDetailModel::new))
                .doOnNext(itemDetailModel -> Timber.d("[DetailUser] Print item detail mode %s", itemDetailModel.getKey()))
                .toList()
                .toObservable();
    }
}
