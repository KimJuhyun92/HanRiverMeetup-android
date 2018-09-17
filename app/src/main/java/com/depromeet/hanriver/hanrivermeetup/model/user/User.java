package com.depromeet.hanriver.hanrivermeetup.model.user;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class User {
    @SerializedName("user_id")
    public String userID;

    @SerializedName("nickname")
    public String nickname;

    @SerializedName("access_token")
    public String accessToken;

    @SerializedName("hangang_token")
    public String hangangToken;

    @SerializedName("last_login_time")
    public Date lastLoginTime;

    @SerializedName("creation_time")
    public Date creationTime;

    @SerializedName("fcm_token")
    public String fcmToken;
}
