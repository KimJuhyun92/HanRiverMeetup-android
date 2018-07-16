package com.depromeet.hanriver.hanrivermeetup.network;

import com.depromeet.hanriver.hanrivermeetup.BuildConfig;

public class APIUtiles {
    private APIUtiles() { }

    private static final String BASE_URL = BuildConfig.APIServerBaseURL;
    public static final String HOST_API_URL = BASE_URL + "host/";
    public static final String GUEST_API_URL = BASE_URL + "guest/";
    public static final String COMM_API_URL = BASE_URL + "comm/";
    public static final String ACCESS_API_URL = BASE_URL + "access/";

    public static HostService getHostService(){
        return RetrofitClient.getClient(HOST_API_URL).create(HostService.class);
    }

    public static GuestService getGuestService(){
        return RetrofitClient.getClient(GUEST_API_URL).create(GuestService.class);
    }

    public static LoginService getLoginService(){
        return RetrofitClient.getClient(ACCESS_API_URL).create(LoginService.class);
    }

    public static CommunicationService getCommunicationService(){
        return RetrofitClient.getClient(COMM_API_URL).create(CommunicationService.class);
    }
}