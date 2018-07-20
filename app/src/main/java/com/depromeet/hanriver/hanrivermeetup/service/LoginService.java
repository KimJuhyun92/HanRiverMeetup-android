package com.depromeet.hanriver.hanrivermeetup.service;

import com.depromeet.hanriver.hanrivermeetup.model.user.LoginInfo;
import com.depromeet.hanriver.hanrivermeetup.model.user.User;
import com.depromeet.hanriver.hanrivermeetup.network.APIUtiles;
import com.depromeet.hanriver.hanrivermeetup.network.LoginAPIService;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginService {
    private static final LoginService ourInstance = new LoginService();
    private LoginAPIService mService;

    public static LoginService getInstance() {
        return ourInstance;
    }

    private LoginService() {
        mService = APIUtiles.getLoginService();
    }

    public Observable<Boolean> login(String id, String facebookAccessToken) {

        LoginInfo loginInfo = new LoginInfo();
        loginInfo.userID = id;
        loginInfo.accessToken = facebookAccessToken;

        return mService.getAccessToken(loginInfo)
                .subscribeOn(Schedulers.io())
                .map(it -> {
                    setServices(it.hangangToken, it.userID);
                    return it != null;
                });
    }

    private void setServices(String token, String id) {
        HostService.getInstance().setService(token, id);
        GuestService.getInstance().setService(token, id);
        CommunicationService.getInstance().setService(token, id);
    }
}
