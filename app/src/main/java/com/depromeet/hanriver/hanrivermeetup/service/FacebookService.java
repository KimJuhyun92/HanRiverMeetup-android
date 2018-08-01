package com.depromeet.hanriver.hanrivermeetup.service;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import com.depromeet.hanriver.hanrivermeetup.network.APIUtiles;
import com.depromeet.hanriver.hanrivermeetup.network.FacebookAPIService;
import com.facebook.Profile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
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
                .map(it -> {
                    return it;
                });
    }

    public Bitmap getProfileURI(String userID) {
//        Profile profile = Profile.getCurrentProfile();
//        String link = profile.getProfilePictureUri(240, 240).toString();
//        Log.d("@@@@@@","@@@@@"+link);
        String link = "https://graph.facebook.com/v3.1/"+userID+"/picture?height=240&width=240";
        URL url = null;
        try {
            url = new URL(link);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        InputStream is = null;
        try {
            is = url.openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Bitmap bitmap = BitmapFactory.decodeStream(is);
        Log.d("@@@@#","@@: "+bitmap);
            return bitmap;
    }


}