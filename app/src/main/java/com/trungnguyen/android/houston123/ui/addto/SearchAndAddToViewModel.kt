package com.trungnguyen.android.houston123.ui.addto

import android.content.Context
import android.text.TextUtils
import android.view.View
import com.trungnguyen.android.houston123.R
import com.trungnguyen.android.houston123.anotation.OnClick
import com.trungnguyen.android.houston123.anotation.UserType
import com.trungnguyen.android.houston123.base.BaseModel
import com.trungnguyen.android.houston123.base.BaseViewModel
import com.trungnguyen.android.houston123.data.EmptyResponse
import com.trungnguyen.android.houston123.repository.updateuser.UpdateUserRepository
import com.trungnguyen.android.houston123.repository.updateuser.UpdateUserStore
import com.trungnguyen.android.houston123.repository.userlist.UserListRepository
import com.trungnguyen.android.houston123.repository.userlist.UserListStore
import com.trungnguyen.android.houston123.rx.SchedulerHelper
import com.trungnguyen.android.houston123.util.AddUserUtils
import com.trungnguyen.android.houston123.util.Constants
import com.trungnguyen.android.houston123.widget.ToastCustom
import io.reactivex.disposables.Disposable
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by trungnd4 on 23/12/2018.
 */
class SearchAndAddToViewModel @Inject
constructor(private val mContext: Context, userListRepository: UserListRepository,
            updateUserRepository: UpdateUserRepository) : BaseViewModel<ISearchAndAddToView>() {

    private val mUserListRepository: UserListStore.Repository
    private val mUpdateUserListRepository: UpdateUserStore.Repository

    init {
        this.mUserListRepository = userListRepository
        this.mUpdateUserListRepository = updateUserRepository
    }

    @OnClick
    fun onSubmitSearchToAdd(view: View, text: String) {
        if (TextUtils.isEmpty(text)) {
            ToastCustom.makeTopToastText(mContext, mContext.getString(R.string.general_error_message))
            return
        }
        val subscription = mUserListRepository.handleSearchServiceFlow<EmptyResponse>(UserType.CLAZZ, text)
                .compose(SchedulerHelper.applySchedulers())
                .doOnSubscribe { showLoading() }
                .doOnTerminate { this.hideLoading() }
                .subscribe({ data -> mView?.onSearchCompleted(data) }, {
                    Timber.d(it)
                })

        mSubscription.add(subscription)
    }

    fun onAddUserAction(model: BaseModel, modelId: String?, codeSource: Int) {
        val code = AddUserUtils.getCodeFromModel(model)
        if (code == Constants.DEFAULT_CODE_VALUE) {
            return
        }
        if (codeSource == UserType.STUDENT) {
            val detailClazzModel = AddUserUtils.tranformDetailClsasModel(model, modelId!!)
            val subscription: Disposable = mUpdateUserListRepository.callApiUpdateUser(UserType.DETAIL_CLAZZ, detailClazzModel)
                    .compose(SchedulerHelper.applySchedulers())
                    .doOnSubscribe { showLoading() }
                    .doOnTerminate { hideLoading() }
                    .subscribe({ mView?.addToCompleted() }, {
                        Timber.d(it)
                    })

            mSubscription.add(subscription)

            return
        }
        val clazzModel = AddUserUtils.transformClassModel(model, modelId!!)
        val subscription: Disposable = mUpdateUserListRepository.callApiUpdateUser(UserType.CLAZZ, clazzModel)
                .compose(SchedulerHelper.applySchedulers())
                .doOnSubscribe { showLoading() }
                .doOnTerminate { hideLoading() }
                .subscribe({ mView?.addToCompleted() }, {
                    Timber.d(it)
                })

        mSubscription.add(subscription)
    }
}
