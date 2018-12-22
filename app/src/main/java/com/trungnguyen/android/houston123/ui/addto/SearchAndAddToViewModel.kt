package com.trungnguyen.android.houston123.ui.addto

import android.content.Context
import android.text.TextUtils
import android.view.View
import com.trungnguyen.android.houston123.R
import com.trungnguyen.android.houston123.anotation.OnClick
import com.trungnguyen.android.houston123.anotation.UserType
import com.trungnguyen.android.houston123.base.BaseViewModel
import com.trungnguyen.android.houston123.data.EmptyResponse
import com.trungnguyen.android.houston123.repository.userlist.UserListRepository
import com.trungnguyen.android.houston123.repository.userlist.UserListStore
import com.trungnguyen.android.houston123.rx.SchedulerHelper
import com.trungnguyen.android.houston123.widget.ToastCustom
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by trungnd4 on 23/12/2018.
 */
class SearchAndAddToViewModel @Inject
constructor(private val mContext: Context, userListRepository: UserListRepository) : BaseViewModel<ISearchAndAddToView>() {

    private val mUserListRepository: UserListStore.Repository

    init {
        this.mUserListRepository = userListRepository
    }

    @OnClick
    fun onSubmitSearchToAdd(view: View, text: String) {
        if (TextUtils.isEmpty(text)) {
            ToastCustom.makeTopToastText(mContext, mContext.getString(R.string.general_error_message))
            return
        }
        val subscription = mUserListRepository.handleSearchServiceFlow<EmptyResponse>(UserType.LECTURER, text)
                .compose(SchedulerHelper.applySchedulers())
                .doOnSubscribe { showLoading() }
                .doOnTerminate { this.hideLoading() }
                .subscribe({ data -> mView?.onSearchCompleted(data) }, {
                    Timber.d(it)
                })

        mSubscription.add(subscription)
    }

    fun onAddUserAction() {

    }
}
