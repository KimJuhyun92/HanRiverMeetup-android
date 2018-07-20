package com.depromeet.hanriver.hanrivermeetup.network;

import com.depromeet.hanriver.hanrivermeetup.model.user.LoginInfo;
import com.depromeet.hanriver.hanrivermeetup.model.user.User;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginAPIService {
    @POST("loginValidate/")
    Observable<User> getAccessToken(@Body LoginInfo loginInfo);

    @POST("registUser/")
    Observable<User> registerUser(@Body HashMap<String, Object> userJoinInfoObject);
}
