package com.depromeet.hanriver.hanrivermeetup.network;

import com.depromeet.hanriver.hanrivermeetup.model.user.LoginInfo;
import com.depromeet.hanriver.hanrivermeetup.model.user.User;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginAPIService {
    @POST("login/")
    Observable<Response<User>> getAccessToken(@Body LoginInfo loginInfo);

    @POST("register/")
    Observable<Response<User>> registerUser(@Body HashMap<String, Object> userJoinInfoObject);
}
