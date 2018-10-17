package com.trungnguyen.android.houston123.repository.login;

import com.trungnguyen.android.houston123.data.AuthenticateResponse;
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
    }

    public interface Repository {
        Observable<AuthenticateResponse> callLoginApi(String userName, String password);

        Observable<AuthenticateResponse> callLogoutApi();

        Observable<Boolean> getLoginState();

        Observable<Boolean> putAuthInfoLocal(boolean state, String accessToken);

    }

    public interface LocalStorage {
        void putSafeAccessToken(String accessToken);

        Observable<String> getSafeAccessToken();

        boolean getLoginStatus();

        Observable<Boolean> setLoginState(boolean state);
    }

}
