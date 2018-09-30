package com.depromeet.hanriver.hanrivermeetup.service;

import com.depromeet.hanriver.hanrivermeetup.model.timeline.EventVO;
import com.depromeet.hanriver.hanrivermeetup.network.APIUtiles;
import com.depromeet.hanriver.hanrivermeetup.network.EventAPIService;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class EventService {
    private static final EventService ourInstance = new EventService();
    private EventAPIService mService;

    public static EventService getInstance() {
        return ourInstance;
    }

    private EventService() {
    }

    public void setService(String token, String id) {
        mService = APIUtiles.getEventService(token, id);
    }

    public Observable<Response<List<EventVO>>> getEvents(){
        return mService.getEvents().subscribeOn(Schedulers.io())
                .doOnNext( res -> {
                    if(res.code() == HttpsURLConnection.HTTP_OK){
                        res.body();
                    }
                });
    }
}
