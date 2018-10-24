package com.trungnguyen.android.houston123.ui.userdetail.detailmodel;

import com.trungnguyen.android.houston123.base.BaseModel;
import com.trungnguyen.android.houston123.ui.userdetail.ItemDetailModel;
import com.trungnguyen.android.houston123.util.ModelResourceLoader;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by trungnd4 on 23/10/2018.
 */
public class ClassModel extends BaseModel {

    public String clazzId;

    public String clazz;

//    public String subjectId; -> Sub content
//
//    public String clazzName; -> Main content

    public String lecturerId;

    public String lecturerName;

    public String startDate;

    public String endDate;

    public String branch;

    public String departmen;

    public ClassModel(String mainContent, String subCotent) {
        super(mainContent, subCotent);
    }

    public ClassModel(String mainContent, String subCotent, String clazzId, String clazz, String lecturerId, String lecturerName, String startDate, String endDate, String branch, String departmen) {
        super(mainContent, subCotent);
        this.clazzId = clazzId;
        this.clazz = clazz;
        this.lecturerId = lecturerId;
        this.lecturerName = lecturerName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.branch = branch;
        this.departmen = departmen;
    }

    @Override
    public Observable<List<ItemDetailModel>> convert() {
        Observable<String> observableResource = Observable.just(ModelResourceLoader.loadClassResource())
                .flatMapIterable(items -> items)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io());

        Observable<String> observableValue = Observable.just(Arrays.asList(clazzId, getMainContent(), getSubCotent(), lecturerId, lecturerName, startDate, endDate, branch, departmen))
                .flatMapIterable(items -> items)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io());

        return Observable.zip(observableResource, observableValue, (ItemDetailModel::new))
                .doOnNext(itemDetailModel -> Timber.d("[DetailUser] Print item detail mode %s", itemDetailModel.getKey()))
                .toList()
                .toObservable();
    }
}
