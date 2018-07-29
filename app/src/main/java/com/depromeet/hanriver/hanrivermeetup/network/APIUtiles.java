package com.depromeet.hanriver.hanrivermeetup.network;

import com.depromeet.hanriver.hanrivermeetup.BuildConfig;

public class APIUtiles {
    private APIUtiles() { }

    private static final String BASE_URL = BuildConfig.APIServerBaseURL;
    public static final String HOST_API_URL = BASE_URL + "host/";
    public static final String GUEST_API_URL = BASE_URL + "guest/";
    public static final String COMM_API_URL = BASE_URL + "comm/";
    public static final String ACCESS_API_URL = BASE_URL + "access/";
    public static final String FACEBOOK_API_URL = "https://graph.facebook.com/";

    public static LoginAPIService getLoginService(){
        return RetrofitClient.getClient(ACCESS_API_URL).create(LoginAPIService.class);
    }

    public static FacebookAPIService getFacebookService(){
        return RetrofitClient.getClient(FACEBOOK_API_URL).create(FacebookAPIService.class);
    }


    public static HostAPIService getHostService(String accessToken, String id){
        return RetrofitClient.getClientWithToken(HOST_API_URL, accessToken, id).create(HostAPIService.class);
    }

    public static GuestAPIService getGuestService(String accessToken, String id){
        return RetrofitClient.getClientWithToken(GUEST_API_URL, accessToken, id).create(GuestAPIService.class);
    }

    public static CommunicationAPIService getCommunicationService(String accessToken, String id){
        return RetrofitClient.getClientWithToken(COMM_API_URL, accessToken, id).create(CommunicationAPIService.class);
    }
}