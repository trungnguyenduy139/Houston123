package com.trungnguyen.android.houston123.repository.login;

import com.trungnguyen.android.houston123.data.AuthenticateResponse;
import com.trungnguyen.android.houston123.data.BaseResponse;
import com.trungnguyen.android.houston123.data.ListBaseResponse;
import com.trungnguyen.android.houston123.data.LoginInfoResponse;
import com.trungnguyen.android.houston123.util.Constants;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
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
        Observable<ListBaseResponse<LoginInfoResponse>> getAccountInfo(@Header(value = "Authorization") String userToken);


        @POST(Constants.LoginApi.CHANGE_PASSWORD)
        Observable<BaseResponse> changePassword(@Header(value = "Authorization") String userToken,
                                                @Query("pass_old") String passOld,
                                                @Query("pass_new") String passNew,
                                                @Query("pass_confirm") String passConfirm);

        @Headers({
                "Content-Type: application/json",
                "X-Requested-With: XMLHttpRequest",
        })
        @PUT(Constants.LoginApi.ACCOUNT_INFO)
        Observable<BaseResponse> updateAccountInfo(@Header(value = "Authorization") String userToken,
                                                   @Query("HoVaTen") String name,
                                                   @Query("Cmnd") String cmnd,
                                                   @Query("Sdt") String phone,
                                                   @Query("Email") String email,
                                                   @Query("DiaChi") String address);
    }

    public interface Repository {
        Observable<LoginInfoResponse> callLoginApi(String userName, String password);

        Observable<AuthenticateResponse> callLogoutApi();

        Observable<LoginInfoResponse> callAccountInformationApi(String token, boolean shouldReSaveState);

        Observable<BaseResponse> callChangePasswordApi(String passOld, String passNew, String passConfirm);

        Observable<LoginInfoResponse> getLoginState();

        void putAuthInfoLocal(boolean state, String accessToken);

        Observable<BaseResponse> callApiUpdateAccountInfo(String name, String cmnd, String phone, String email, String address);

    }

    public interface LocalStorage {
        void putSafeAccessToken(String accessToken);

        Observable<String> getSafeAccessToken();

        boolean getLoginStatus();

        void setLoginState(boolean state);

        void putGlobalPermissionLocal(String permission);

        String getGlobalPermission(String permission);
    }

}
