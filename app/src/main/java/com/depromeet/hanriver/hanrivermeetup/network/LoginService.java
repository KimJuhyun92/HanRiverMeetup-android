package com.depromeet.hanriver.hanrivermeetup.network;

import com.depromeet.hanriver.hanrivermeetup.model.meeting.MeetingDetail;
import com.depromeet.hanriver.hanrivermeetup.model.user.LoginInfo;

import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {
    @POST("loginValidate/")
    MeetingDetail getAccessToken(@Body LoginInfo loginInfo);
}
