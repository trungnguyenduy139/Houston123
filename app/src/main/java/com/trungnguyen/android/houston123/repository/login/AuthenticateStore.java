package com.trungnguyen.android.houston123.repository.login;

import com.trungnguyen.android.houston123.data.AuthenticateResponse;
import com.trungnguyen.android.houston123.util.Constants;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by trungnd4 on 23/09/2018.
 */
public class AuthenticateStore {

    public interface RequestService {
        @POST(Constants.LoginApi.LOGIN_API)
        Observable<AuthenticateResponse> loginService(@Query("username") String userName,
                                                      @Query("password") String password);
    }

    public interface Repository {

    }

    public interface LocalStorage {
        Observable<Boolean> getLoginStatus();

        Observable<Boolean> setLoginState(boolean state);
    }

}
