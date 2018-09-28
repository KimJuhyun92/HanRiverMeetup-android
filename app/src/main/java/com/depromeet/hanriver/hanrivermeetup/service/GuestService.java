package com.depromeet.hanriver.hanrivermeetup.service;

import android.telecom.Call;
import android.util.Log;

import com.depromeet.hanriver.hanrivermeetup.model.meeting.JoinRequest;
import com.depromeet.hanriver.hanrivermeetup.network.APIUtiles;
import com.depromeet.hanriver.hanrivermeetup.network.GuestAPIService;

import java.util.Calendar;

import javax.net.ssl.HttpsURLConnection;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Response;

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

    public Observable<Response<JoinRequest>> joinMeeting(JoinRequest joinRequest){
        return mService.joinMeeting(joinRequest).subscribeOn(Schedulers.io())
                .doOnNext(res->{
                    if(res.code() == HttpsURLConnection.HTTP_OK){
                        res.body();
                    }
                });
    }

    public retrofit2.Call<ResponseBody> deleteJoinRequest(int application_seq) {
        return mService.deleteJoinRequest(application_seq);
    }
}
