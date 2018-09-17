package com.depromeet.hanriver.hanrivermeetup.service;

import android.util.Log;

import com.depromeet.hanriver.hanrivermeetup.model.mypage.Tab1VO;
import com.depromeet.hanriver.hanrivermeetup.model.mypage.Tab2VO;
import com.depromeet.hanriver.hanrivermeetup.model.mypage.Tab3VO;
import com.depromeet.hanriver.hanrivermeetup.model.mypage.TestVO;
import com.depromeet.hanriver.hanrivermeetup.network.APIUtiles;
import com.depromeet.hanriver.hanrivermeetup.network.MyPageAPIService;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class MyPageService {
    private static final MyPageService ourInstance = new MyPageService();
    private MyPageAPIService mService;

    public static MyPageService getInstance(){
        return ourInstance;
    }

    private MyPageService() { }

    public void setService(String token, String id) {
        mService = APIUtiles.getMyPageService(token, id);
    }


    public Observable<List<Tab1VO>> getMyMeeting(String userID){
        return mService.getMyMeeting(userID)
                .subscribeOn(Schedulers.io());
    }

    public Observable<List<Tab3VO>> getMathcedMeeting(String userID){
        return mService.getMatchedMeeting(userID)
                .subscribeOn(Schedulers.io());
    }

    public Observable<List<Tab2VO>> getAppliedMeeting(String userID){
        return mService.getAppliedMeeting(userID)
                .subscribeOn(Schedulers.io());
    }
}
