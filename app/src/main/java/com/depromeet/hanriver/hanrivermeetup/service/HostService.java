package com.depromeet.hanriver.hanrivermeetup.service;

import com.depromeet.hanriver.hanrivermeetup.model.meeting.MeetingDetail;
import com.depromeet.hanriver.hanrivermeetup.model.meeting.MeetingDetail2;
import com.depromeet.hanriver.hanrivermeetup.network.APIUtiles;
import com.depromeet.hanriver.hanrivermeetup.network.HostAPIService;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

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

    public Observable<List<MeetingDetail>> getTodayList(){
        return mService.getMeetingsOnToday()
                .subscribeOn(Schedulers.io())
                .map(it -> it);
    }
}