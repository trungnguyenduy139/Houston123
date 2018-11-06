package com.trungnguyen.android.houston123.repository.login;

import com.trungnguyen.android.houston123.data.AccountInfoResponse;
import com.trungnguyen.android.houston123.data.AuthenticateResponse;
import com.trungnguyen.android.houston123.data.BaseResponse;
import com.trungnguyen.android.houston123.data.LoginInfoResponse;
import com.trungnguyen.android.houston123.util.Constants;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by trungnd4 on 23/09/2018.
 */
public class AuthenticateStore {

    public interface RequestService {
        @Headers({
                "Content-Type: application/json",
                "X-Requested-With: XMLHttpRequest"
        })
        @POST(Constants.LoginApi.LOGIN)
        Observable<AuthenticateResponse> loginService(@Query("loginID") String userName,
                                                      @Query("loginPASS") String password);

        @Headers({
                "Content-Type: application/json",
                "X-Requested-With: XMLHttpRequest",
        })
        @GET(Constants.LoginApi.LOGOUT)
        Observable<AuthenticateResponse> logoutService(@Header(value = "Authorization") String userToken);

        @Headers({
                "Content-Type: application/json",
                "X-Requested-With: XMLHttpRequest",
        })
        @GET(Constants.LoginApi.ACCOUNT_INFO)
        Observable<AccountInfoResponse> getAccountInfo(@Header(value = "Authorization") String userToken);


        @POST(Constants.LoginApi.CHANGE_PASSWORD)
        Observable<BaseResponse> changePassword(@Header(value = "Authorization") String userToken,
                                                @Query("pass_old") String passOld,
                                                @Query("pass_new") String passNew,
                                                @Query("pass_confirm") String passConfirm);
    }

    public interface Repository {
        Observable<LoginInfoResponse> callLoginApi(String userName, String password);

        Observable<AuthenticateResponse> callLogoutApi();

        Observable<LoginInfoResponse> callAccountInformationApi(String token, boolean shouldReSaveState);

        Observable<BaseResponse> callChangePasswordApi(String passOld, String passNew, String passConfirm);

        Observable<LoginInfoResponse> getLoginState();

        Observable<Boolean> putAuthInfoLocal(boolean state, String accessToken);

    }

    public interface LocalStorage {
        void putSafeAccessToken(String accessToken);

        Observable<String> getSafeAccessToken();

        boolean getLoginStatus();

        Observable<Boolean> setLoginState(boolean state);

        void putGlobalPermissionLocal(String permission);

        String getGlobalPermission(String permission);
    }

}
