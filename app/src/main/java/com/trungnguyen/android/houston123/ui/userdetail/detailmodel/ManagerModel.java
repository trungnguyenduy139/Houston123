package com.trungnguyen.android.houston123.ui.userdetail.detailmodel;


import com.trungnguyen.android.houston123.base.BaseUserModel;
import com.trungnguyen.android.houston123.ui.userdetail.ItemDetailModel;
import com.trungnguyen.android.houston123.util.ModelResourceLoader;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by trungnd4 on 14/08/2018.
 */
public class ManagerModel extends BaseUserModel {

    private String img;
    private String outDate;
    private String outReason;
    private String department;
    private String position;


    public ManagerModel(String name, String phone, String lecturerId, String img, String address, String email, String cmnd, String outDate, String outReason, String department, String position) {
        super(name, phone, address, lecturerId, email, cmnd);
        this.img = img;
        this.outDate = outDate;
        this.outReason = outReason;
        this.department = department;
        this.position = position;
    }

    public String getImg() {
        return img;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getCmnd() {
        return cmnd;
    }

    public String getDepartment() {
        return department;
    }

    public String getPosition() {
        return position;
    }

    public String getOutDate() {
        return outDate != null ? outDate : "";
    }

    public String getOutReason() {
        return outReason != null ? outReason : "";
    }

    @Override
    public Observable<List<ItemDetailModel>> convert() {
        Observable<String> observableResource = Observable.just(ModelResourceLoader.loadResourceManager())
                .flatMapIterable(items -> items)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io());

        Observable<String> observableValue = Observable.just(Arrays.asList(userId, getMainContent(), getSubCotent(), address, email, cmnd, position, getOutDate(), getOutReason(), department))
                .flatMapIterable(items -> items)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io());

        return Observable.zip(observableResource, observableValue, (ItemDetailModel::new))
                .doOnNext(itemDetailModel -> Timber.d("[DetailUser] Print item detail mode %s", itemDetailModel.getKey()))
                .toList()
                .toObservable();
    }
}
