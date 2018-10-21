package com.trungnguyen.android.houston123.ui.login

import android.content.Context
import android.text.TextUtils
import com.trungnguyen.android.houston123.anotation.OnClick
import com.trungnguyen.android.houston123.base.BaseViewModel
import com.trungnguyen.android.houston123.data.AuthenticateResponse
import com.trungnguyen.android.houston123.repository.login.AuthenticateRepository
import com.trungnguyen.android.houston123.repository.login.AuthenticateStore
import com.trungnguyen.android.houston123.rx.DefaultSubscriber
import com.trungnguyen.android.houston123.rx.SchedulerHelper
import com.trungnguyen.android.houston123.util.AppUtils
import com.trungnguyen.android.houston123.util.SingleLiveEvent
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class LoginViewModel
@Inject
constructor(private val mContext: Context, authenticateRepository: AuthenticateRepository) : BaseViewModel<ILoginView>(mContext) {
    var mLoginModel: LoginModel

    private val mLoginState = false

    private val mAuthRepository: AuthenticateStore.Repository

    val isLoggedIn = SingleLiveEvent<Boolean>()

    val liveUserToken = SingleLiveEvent<String>()

    init {
        isLoggedIn.value = mLoginState
        this.mAuthRepository = authenticateRepository
        mLoginModel = LoginModel()
    }

    fun getLoginStatus() {
        val disposable = mAuthRepository.loginState
                .compose(SchedulerHelper.applySchedulers())
                .subscribe({ isLoggedIn.setValue(it) }, { Timber.d(it) })
        mSubscription.add(disposable)
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
                .filter { t: AuthenticateResponse -> !TextUtils.isEmpty(t.userToken) }
                .doOnNext { response: AuthenticateResponse -> Timber.d("[Auth] User access token [%s]", response.userToken) }
                .doOnSubscribe { mView?.showLoadingDialog() }
                .doOnTerminate { mView?.hideLoadingDialog() }
                .subscribe({
                    liveUserToken.value = it.userToken
                }, {
                    mView?.onAuthFailed()
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
