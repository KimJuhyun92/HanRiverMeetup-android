package com.depromeet.hanriver.hanrivermeetup.service;

import com.depromeet.hanriver.hanrivermeetup.network.APIUtiles;
import com.depromeet.hanriver.hanrivermeetup.network.GuestAPIService;

public class GuestService {
    private static final GuestService ourInstance = new GuestService();
    private GuestAPIService mService;

    public static GuestService getInstance() {
        return ourInstance;
    }

    private GuestService() { }

    public void setService(String token, String id) {
        mService = APIUtiles.getGuestService(token, id);
    }
}
