package com.depromeet.hanriver.hanrivermeetup.service;

import android.text.TextUtils;

import com.depromeet.hanriver.hanrivermeetup.common.PreferencesManager;
import com.depromeet.hanriver.hanrivermeetup.firebase.CustomFirebaseInstanceIDService;
import com.depromeet.hanriver.hanrivermeetup.model.user.LoginInfo;
import com.depromeet.hanriver.hanrivermeetup.model.user.User;
import com.depromeet.hanriver.hanrivermeetup.network.APIUtiles;
import com.depromeet.hanriver.hanrivermeetup.network.LoginAPIService;

import java.security.InvalidParameterException;
import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
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

    public Observable<Response<User>> login(String id, String facebookAccessToken) {
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.userID = id;
        loginInfo.accessToken = facebookAccessToken;
        loginInfo.fcmToken = PreferencesManager.getFcmToken();

        return mService.getAccessToken(createUser(id, facebookAccessToken))
                .subscribeOn(Schedulers.io())
                .doOnNext(res -> {
                    if(res.code() == HttpsURLConnection.HTTP_OK) {
                        setServices(res.body().hangangToken, res.body().userID);
                    }
                });
    }

    private LoginInfo createUser(String id, String facebookAccessToken) {
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.userID = id;
        loginInfo.accessToken = facebookAccessToken;
        loginInfo.fcmToken = PreferencesManager.getFcmToken();

        return loginInfo;
    }

    public Observable<Response<User>> registerUser(String nickname) {
        if(TextUtils.isEmpty(nickname)) {
            return Observable.create(subscriber ->
                    subscriber.onError(new InvalidParameterException("registerUser : Wrong parameter")));
        }

        HashMap<String, Object> userJoinInfoObject = new HashMap<>();
        userJoinInfoObject.put("nickname", nickname);
        userJoinInfoObject.put("user_id", PreferencesManager.getUserID());
        userJoinInfoObject.put("access_token", PreferencesManager.getFacebookToken());
        userJoinInfoObject.put("fcm_token", PreferencesManager.getFcmToken());

        return mService.registerUser(userJoinInfoObject)
                .subscribeOn(Schedulers.io())
                .doOnNext(res -> {
                    if(res.code() == HttpsURLConnection.HTTP_OK) {
                        setServices(res.body().hangangToken, res.body().userID);
                    }
                });
    }

    private void setServices(String token, String id) {
        HostService.getInstance().setService(token, id);
        GuestService.getInstance().setService(token, id);
        CommunicationService.getInstance().setService(token, id);
        MyPageService.getInstance().setService(token, id);
        TimelineService.getInstance().setService(token, id);
        WeatherService.getInstance().setService(token,id);
        MapService.getInstance().setService(token,id);
        NotificationService.getInstance().setService(token,id);
        EventService.getInstance().setService(token, id);
    }
}
