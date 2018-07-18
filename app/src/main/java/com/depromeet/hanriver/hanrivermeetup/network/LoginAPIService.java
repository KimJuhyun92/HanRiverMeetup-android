package com.depromeet.hanriver.hanrivermeetup.network;

import com.depromeet.hanriver.hanrivermeetup.model.user.LoginInfo;
import com.depromeet.hanriver.hanrivermeetup.model.user.User;

import java.util.Observable;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginAPIService {
    @POST("loginValidate/")
    io.reactivex.Observable<User> getAccessToken(@Body LoginInfo loginInfo);
}
