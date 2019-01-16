package com.trungnguyen.android.houston123.ui.login

import android.content.Context
import android.text.TextUtils
import com.trungnguyen.android.houston123.anotation.OnClick
import com.trungnguyen.android.houston123.base.BaseViewModel
import com.trungnguyen.android.houston123.data.LoginInfoModel
import com.trungnguyen.android.houston123.data.LoginInfoResponse
import com.trungnguyen.android.houston123.injection.Injector
import com.trungnguyen.android.houston123.repository.login.AuthenticateRepository
import com.trungnguyen.android.houston123.repository.login.AuthenticateStore
import com.trungnguyen.android.houston123.rx.SchedulerHelper
import com.trungnguyen.android.houston123.ui.main.MainActivity
import com.trungnguyen.android.houston123.util.AppUtils
import com.trungnguyen.android.houston123.util.BundleBuilder
import com.trungnguyen.android.houston123.util.SingleLiveEvent
import timber.log.Timber
import javax.inject.Inject

class LoginViewModel
@Inject
constructor(private val mContext: Context, authenticateRepository: AuthenticateRepository) : BaseViewModel<ILoginView>(mContext) {

    var mLoginModel: LoginModel

    private val mAuthRepository: AuthenticateStore.Repository

    val isLoggedIn = SingleLiveEvent<Boolean>()

    val liveUserToken = SingleLiveEvent<String>()

    init {
        this.mAuthRepository = authenticateRepository
        mLoginModel = LoginModel()
    }

    fun getLoginStatus() {
        val disposable = mAuthRepository.loginState
                .compose(SchedulerHelper.applySchedulersLoadingAction({ showLoading() }, { hideLoading() }))
                .subscribe({
                    initGlobalUserCallback(it.convertToModel())
                    handleAuthenticateSuccess("quanly")
//                    isLoggedIn.setValue(true)
                }, {
//                    isLoggedIn.value = false
                    Timber.d(it)
                })
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
                .filter { t: LoginInfoResponse -> !TextUtils.isEmpty("quanly") }
                .doOnNext { response: LoginInfoResponse -> Timber.d("[Auth] User access token [%s]", "quanly") }
                .doOnSubscribe { mView?.showLoadingDialog() }
                .doOnTerminate { mView?.hideLoadingDialog() }
                .subscribe({
                    initGlobalUserCallback(it.convertToModel())
                    handleAuthenticateSuccess("quanly")
                }, {
                    mView?.onAuthFailed()
                })

        mSubscription.add(subscription)

    }


    private fun handleAuthenticateSuccess(permission: String) {
        val clz = MainActivity::class.java
        val bundle = BundleBuilder()
        mView?.onAuthSuccess(permission, clz, bundle.build())
    }

    private fun initGlobalUserCallback(model: LoginInfoModel?) {
        if (model == null) {
            return
        }
        Injector.getInstance().createUserScope(model)
    }
}
