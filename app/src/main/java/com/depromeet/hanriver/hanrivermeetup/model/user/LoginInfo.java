package com.depromeet.hanriver.hanrivermeetup.model.user;

import com.google.gson.annotations.SerializedName;

public class LoginInfo {
    @SerializedName("access_token")
    private String accessToken;

    @SerializedName("user_id")
    private String userID;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
