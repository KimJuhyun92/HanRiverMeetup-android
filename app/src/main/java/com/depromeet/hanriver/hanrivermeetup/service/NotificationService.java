package com.depromeet.hanriver.hanrivermeetup.service;

import com.depromeet.hanriver.hanrivermeetup.model.mypage.AlarmDetail;
import com.depromeet.hanriver.hanrivermeetup.network.APIUtiles;
import com.depromeet.hanriver.hanrivermeetup.network.NotificationAPIService;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class NotificationService {
    private static final NotificationService ourInstance = new NotificationService();
    private NotificationAPIService mService;

    public static NotificationService getInstance(){
        return ourInstance;
    }

    private NotificationService() { }

    public void setService(String token, String id) {
        mService = APIUtiles.getNotificationService(token, id);
    }

    public Observable<List<AlarmDetail>> getNotificationLog(String userID){
        return mService.getNotificationLog(userID)
                .subscribeOn(Schedulers.io());
    }
}
