package com.depromeet.hanriver.hanrivermeetup.service;

import android.text.TextUtils;
import android.util.Log;

import com.depromeet.hanriver.hanrivermeetup.model.user.LoginInfo;
import com.depromeet.hanriver.hanrivermeetup.model.user.User;
import com.depromeet.hanriver.hanrivermeetup.network.APIUtiles;
import com.depromeet.hanriver.hanrivermeetup.network.LoginAPIService;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.HashMap;

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
    private String userID = null;

    public static LoginService getInstance() {
        return ourInstance;
    }

    private LoginService() {
        mService = APIUtiles.getLoginService();
    }

    public Observable<User> login(String id, String facebookAccessToken) {

        LoginInfo loginInfo = new LoginInfo();
        loginInfo.userID = id;
        loginInfo.accessToken = facebookAccessToken;

        return mService.getAccessToken(loginInfo)
                .subscribeOn(Schedulers.io())
                .map(it -> {
                    setServices(it.hangangToken, it.userID);
                    return it;
                });
    }

    public Observable<Boolean> registerUser(String nickname) {
        if(TextUtils.isEmpty(nickname) || TextUtils.isEmpty(userID)) {
            return Observable.create(subscriber ->
                    subscriber.onError(new InvalidParameterException("registerUser : Wrong parameter")));
        }

        HashMap<String, Object> userJoinInfoObject = new HashMap<>();
        userJoinInfoObject.put("nickname", nickname);
        userJoinInfoObject.put("user_id", userID);

        return mService.registerUser(userJoinInfoObject)
                .subscribeOn(Schedulers.io())
                .map(it -> it != null);
    }

    private void setServices(String token, String id) {
        userID = id;
        HostService.getInstance().setService(token, id);
        GuestService.getInstance().setService(token, id);
        CommunicationService.getInstance().setService(token, id);
        MyPageService.getInstance().setService(token, id);
        WeatherService.getInstance().setService(token,id);
    }
}
