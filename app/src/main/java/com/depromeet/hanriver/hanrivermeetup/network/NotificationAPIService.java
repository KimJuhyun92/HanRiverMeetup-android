package com.depromeet.hanriver.hanrivermeetup.network;

import com.depromeet.hanriver.hanrivermeetup.model.mypage.AlarmDetail;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NotificationAPIService {

    @GET("{userID}/")
    Observable<List<AlarmDetail>> getNotificationLog(@Path("userID") String userID);
}
