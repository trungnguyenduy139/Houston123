package com.trungnguyen.android.houston123.ui.login

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.text.TextUtils

import com.trungnguyen.android.houston123.anotation.OnClick
import com.trungnguyen.android.houston123.base.BaseViewModel
import com.trungnguyen.android.houston123.repository.login.AuthenticateRepository
import com.trungnguyen.android.houston123.repository.login.AuthenticateStore
import com.trungnguyen.android.houston123.rx.DefaultSubscriber
import com.trungnguyen.android.houston123.rx.SchedulerHelper
import com.trungnguyen.android.houston123.util.AppUtils

import javax.inject.Inject

import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class LoginViewModel
@Inject
constructor(private val mContext: Context, authenticateRepository: AuthenticateRepository) : BaseViewModel<ILoginView>(mContext) {
    var mLoginModel: LoginModel

    private val mLoginState = false

    private val mAuthRepository: AuthenticateStore.Repository

    private val isLoggedIn = MutableLiveData<Boolean>()

    init {
        isLoggedIn.value = mLoginState
        this.mAuthRepository = authenticateRepository
        mLoginModel = LoginModel()
    }

    fun getIsLoggedIn(): MutableLiveData<Boolean> {
        val disposable = mAuthRepository.loginState
                .compose(SchedulerHelper.applySchedulers())
                .subscribe({ isLoggedIn.setValue(it) }, { Timber.d(it) })
        mSubscription.add(disposable)
        return isLoggedIn
    }

    @OnClick
    fun onLoginButtonClick() {
        if (!AppUtils.isNetworkAvailable(mContext) && mView != null) {
            mView!!.showNetworkDialog()
        }
        val userName = mLoginModel.userName
        val password = mLoginModel.password

        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)) {
            return
        }

        val subscription = mAuthRepository.callLoginApi(userName, password)
                .compose(SchedulerHelper.applySchedulers())
                .doOnSubscribe { mView?.showLoadingDialog() }
                .doOnTerminate { mView?.hideLoadingDialog() }
                .subscribe({
                    mView?.onAuthSuccess(userName)
                }, {
                    mView?.onAuthSuccess(userName)
                })

        mSubscription.add(subscription)

    }

    fun putAuthInfoToLocal(loginState: Boolean, accessToken: String) {
        val disposable = mAuthRepository.putAuthInfoLocal(loginState, accessToken)
                .subscribeOn(Schedulers.io())
                .subscribeWith(DefaultSubscriber())
        mSubscription.add(disposable)
    }
}
