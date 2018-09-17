package com.depromeet.hanriver.hanrivermeetup.common;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class PreferencesManager {
    private static String BASE_STORE_PROPERTY_NAME = "pre";
    private static String FCM_TOKEN_PROPERTY_NAME = "FCM_TOKEN";
    private static String FACEBOOK_TOKEN_PROPERTY_NAME = "FACEBOOK_TOKEN";
    private static String USER_ID_PROPERTY_NAME = "USER_ID";
    private static String NICKNAME_PROPERTY_NAME = "NICKNAME";

    private static SharedPreferences pref = null;

    public static void setManager(Context context) {
        if(pref == null) {
            pref = context.getSharedPreferences(
                    BASE_STORE_PROPERTY_NAME,
                    MODE_PRIVATE);
        }
    }

    public static String getFcmToken() {
        return pref.getString(FCM_TOKEN_PROPERTY_NAME, "");
    }

    public static void setFcmToken(String token) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(FCM_TOKEN_PROPERTY_NAME, token);
        editor.commit();
    }

    public static String getFacebookToken() {
        return pref.getString(FACEBOOK_TOKEN_PROPERTY_NAME, "");
    }

    public static void setFacebookToken(String token) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(FACEBOOK_TOKEN_PROPERTY_NAME, token);
        editor.commit();
    }

    public static String getNickname() {
        return pref.getString(NICKNAME_PROPERTY_NAME, "");
    }

    public static void setNickname(String nickname) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(NICKNAME_PROPERTY_NAME, nickname);
        editor.commit();
    }

    public static String getUserID() {
        return pref.getString(USER_ID_PROPERTY_NAME, "");
    }

    public static void setUserID(String userID) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(USER_ID_PROPERTY_NAME, userID);
        editor.commit();
    }
}
