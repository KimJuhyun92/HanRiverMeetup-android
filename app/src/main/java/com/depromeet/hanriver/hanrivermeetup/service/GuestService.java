package com.depromeet.hanriver.hanrivermeetup.service;

import com.depromeet.hanriver.hanrivermeetup.model.meeting.JoinRequest;
import com.depromeet.hanriver.hanrivermeetup.network.APIUtiles;
import com.depromeet.hanriver.hanrivermeetup.network.GuestAPIService;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

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

    public Observable<JoinRequest> joinMeeting(JoinRequest joinRequest){
        return mService.joinMeeting(joinRequest).subscribeOn(Schedulers.io()).map(it->it);
    }
}
