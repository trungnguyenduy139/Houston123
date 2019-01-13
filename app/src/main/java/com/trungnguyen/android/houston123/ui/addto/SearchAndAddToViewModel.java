package com.trungnguyen.android.houston123.ui.addto;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import com.trungnguyen.android.houston123.R;
import com.trungnguyen.android.houston123.anotation.OnClick;
import com.trungnguyen.android.houston123.anotation.UserType;
import com.trungnguyen.android.houston123.base.BaseModel;
import com.trungnguyen.android.houston123.base.BaseViewModel;
import com.trungnguyen.android.houston123.repository.updateuser.UpdateUserRepository;
import com.trungnguyen.android.houston123.repository.updateuser.UpdateUserStore;
import com.trungnguyen.android.houston123.repository.userlist.UserListRepository;
import com.trungnguyen.android.houston123.repository.userlist.UserListStore;
import com.trungnguyen.android.houston123.rx.SchedulerHelper;
import com.trungnguyen.android.houston123.ui.userdetail.detailmodel.ClassModel;
import com.trungnguyen.android.houston123.ui.userdetail.detailmodel.DetailClassModel;
import com.trungnguyen.android.houston123.util.AddUserUtils;
import com.trungnguyen.android.houston123.util.Constants;
import com.trungnguyen.android.houston123.widget.ToastCustom;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import timber.log.Timber;

/**
 * Created by trungnd4 on 23/12/2018.
 */
public class SearchAndAddToViewModel extends BaseViewModel<ISearchAndAddToView> {

    private UserListStore.Repository mUserListRepository;

    private UpdateUserStore.Repository mUpdateUserRepository;

    private Context mContext;

    @Inject
    public SearchAndAddToViewModel(Context context, UserListRepository userListRepository, UpdateUserRepository updateUserRepository) {
        this.mContext = context;
        this.mUserListRepository = userListRepository;
        this.mUpdateUserRepository = updateUserRepository;
    }

    @OnClick
    public void onSubmitSearchToAdd(View view, String text) {
        if (TextUtils.isEmpty(text)) {
            ToastCustom.makeTopToastText(mContext, mContext.getString(R.string.general_error_message));
            return;
        }
        Disposable subscription = mUserListRepository.handleSearchServiceFlow(UserType.CLAZZ, text)
                .compose(SchedulerHelper.applySchedulersLoadingAction(this::showLoading, this::hideLoading))
                .doOnError(throwable -> Timber.d("NetworkModule error caused [%s]", throwable.getMessage()))
                .subscribe(data -> {
                    if (mView != null) {
                        mView.onSearchCompleted(data);
                    }
                }, Timber::d);

        mSubscription.add(subscription);
    }

    public void onAddUserAction(BaseModel model, String modelId, int codeSource) {
        int code = AddUserUtils.INSTANCE.getCodeFromModel(model);
        if (code == Constants.DEFAULT_CODE_VALUE) {
            return;
        }
        if (codeSource == UserType.STUDENT) {
            DetailClassModel detailClazzModel = AddUserUtils.INSTANCE.tranformDetailClsasModel(model, modelId);
            Disposable subscription = mUpdateUserRepository.callApiUpdateUser(UserType.DETAIL_CLAZZ, detailClazzModel, modelId, false)
                    .compose(SchedulerHelper.applySchedulers())
                    .doOnSubscribe(disposable -> showLoading())
                    .doOnTerminate(this::hideLoading)
                    .subscribe(response -> {
                        if (mView != null) {
                            mView.addToCompleted();
                        }
                    }, throwable -> {
                        if (mView == null) {
                            return;
                        }
                        mView.addToFailed();
                    });


            mSubscription.add(subscription);

            return;
        }
        ClassModel clazzModel = AddUserUtils.INSTANCE.transformClassModel(model, modelId);
        Disposable subscription = mUpdateUserRepository.callApiUpdateUser(UserType.CLAZZ, clazzModel, modelId, false)
                .compose(SchedulerHelper.applySchedulers())
                .doOnSubscribe(disposable -> showLoading())
                .doOnTerminate(this::hideLoading)
                .subscribe(response -> {
                    if (mView != null) {
                        mView.addToCompleted();
                    }
                }, throwable -> {
                    if (mView == null) {
                        return;
                    }
                    mView.addToFailed();
                });

        mSubscription.add(subscription);
    }


}
