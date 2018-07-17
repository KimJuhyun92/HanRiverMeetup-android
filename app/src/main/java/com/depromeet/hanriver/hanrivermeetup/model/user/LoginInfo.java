package com.depromeet.hanriver.hanrivermeetup.model.user;

import com.google.gson.annotations.SerializedName;

public class LoginInfo {
    @SerializedName("access_token")
    public String accessToken;

    @SerializedName("user_id")
    public String userID;
}
