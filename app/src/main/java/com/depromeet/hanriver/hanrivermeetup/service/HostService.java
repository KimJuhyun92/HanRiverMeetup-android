package com.depromeet.hanriver.hanrivermeetup.service;

import com.depromeet.hanriver.hanrivermeetup.network.APIUtiles;
import com.depromeet.hanriver.hanrivermeetup.network.HostAPIService;

public class HostService {
    private static final HostService ourInstance = new HostService();
    private HostAPIService mService;

    public static HostService getInstance() {
        return ourInstance;
    }

    private HostService() { }

    public void setService(String token, String id) {
        mService = APIUtiles.getHostService(token, id);
    }
}