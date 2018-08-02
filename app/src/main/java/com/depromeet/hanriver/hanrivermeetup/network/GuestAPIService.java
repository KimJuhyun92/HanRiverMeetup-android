package com.depromeet.hanriver.hanrivermeetup.network;

import com.depromeet.hanriver.hanrivermeetup.model.meeting.JoinRequest;


import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface GuestAPIService {
    @POST("join/")
    Observable<JoinRequest> joinMeeting(@Body JoinRequest joinRequest);

    @DELETE("request/{requestID}")
    JoinRequest deleteJoinRequest(@Path("requestID") int requestID);
}
