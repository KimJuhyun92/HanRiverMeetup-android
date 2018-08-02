package com.depromeet.hanriver.hanrivermeetup.service;

import android.text.TextUtils;

import com.depromeet.hanriver.hanrivermeetup.network.APIUtiles;
import com.depromeet.hanriver.hanrivermeetup.network.FacebookAPIService;

import java.security.InvalidParameterException;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class FacebookService {
    private static final FacebookService ourInstance = new FacebookService();
    private FacebookAPIService mService;

    public static FacebookService getInstance() {
        return ourInstance;
    }

    private FacebookService() {
        mService = APIUtiles.getFacebookService();
    }

    public Observable<ResponseBody> getProfileById(String userID) {
        if(TextUtils.isEmpty(userID)) {
            return Observable.create(subscriber ->
                    subscriber.onError(new InvalidParameterException("getProfileById : Wrong parameter")));
        }

        return mService.getProfileById(userID)
                .subscribeOn(Schedulers.io())
                .map(it -> it);
    }

    public String getProfileURL(String userID) {
        return "https://graph.facebook.com/" + userID + "/picture?type=large";
    }

}