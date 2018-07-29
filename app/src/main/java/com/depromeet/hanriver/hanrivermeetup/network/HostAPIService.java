package com.depromeet.hanriver.hanrivermeetup.network;

import com.depromeet.hanriver.hanrivermeetup.model.meeting.MeetingDetail;
import com.depromeet.hanriver.hanrivermeetup.model.meeting.JoinRequest;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface HostAPIService {
    @GET("meeting/{meetingID}")
    MeetingDetail getMeetingDetail(@Path("meetingID") int meetingID);

    @POST("meeting/")
    MeetingDetail createMeeting(@Body MeetingDetail meetingDetail);

    @PUT("meeting/{meetingID}")
    MeetingDetail updateMeeting(@Path("meetingID") int meetingID, @Body MeetingDetail user);

    @DELETE("meeting/{meetingID}")
    boolean deleteMeeting(@Path("meetingID") int meetingID);

    @GET("meetings/today")
    Observable<List<MeetingDetail>> getMeetingsOnToday();

    @GET("requests/{requestID}")
    JoinRequest getRequestsById(@Path("requestID") int id);
}
