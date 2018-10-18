package com.trungnguyen.android.houston123.ui.userdetail.detailmodel;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.trungnguyen.android.houston123.base.BaseUserModel;
import com.trungnguyen.android.houston123.ui.userdetail.ItemDetailModel;
import com.trungnguyen.android.houston123.util.ModelResourceLoader;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by trungnd4 on 13/07/2018.
 */
public class LecturerModel extends BaseUserModel implements Serializable {

    private String lecturerId;
    private String img;
    private String address;
    private String email;
    private String cmnd;
    private String outDate;
    private String outReason;
    private String department;


    public LecturerModel(String name, String phone, String lecturerId, String img, String address, String email, String cmnd, String outDate, String outReason, String department) {
        super(name, phone, address);
        this.lecturerId = lecturerId;
        this.img = img;
        this.email = email;
        this.cmnd = cmnd;
        this.outDate = outDate;
        this.outReason = outReason;
        this.department = department;
    }

    public String getLecturerId() {
        return lecturerId;
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

    public String getOutDate() {
        if (outDate == null) {
            return "";
        }
        return outDate;    }

    public String getOutReason() {
        if (outReason == null) {
            return "";
        }
        return outReason;
    }

    public String getDepartment() {
        return department;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public Observable<List<ItemDetailModel>> convert() {
        Observable<String> observableResource = Observable.just(ModelResourceLoader.loadResourceLecturer())
                .flatMapIterable(items -> items)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io());

        Observable<String> observableValue = Observable.just(Arrays.asList(lecturerId, name, phoneNumber, address, email, cmnd, getOutDate(), getOutReason(), department))
                .flatMapIterable(items -> items)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io());

        return Observable.zip(observableResource, observableValue, (ItemDetailModel::new))
                .doOnNext(itemDetailModel -> Timber.d("[DetailUser] Print item detail mode %s", itemDetailModel.getKey()))
                .toList()
                .toObservable();
    }

//    @Override
//    public List<ItemDetailModel> convert() {
//        List<String> lecturerResources = ModelResourceLoader.loadResourceLecturer();
//        List<ItemDetailModel> itemDetailModelList = super.convert();
//        int index = 0;
//        itemDetailModelList.add(new ItemDetailModel(lecturerResources.get(index++), lecutrerStt));
//        return itemDetailModelList;
//    }
}
