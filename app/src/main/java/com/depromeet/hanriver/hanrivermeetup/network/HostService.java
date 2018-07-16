package com.depromeet.hanriver.hanrivermeetup.network;

import com.depromeet.hanriver.hanrivermeetup.model.meeting.MeetingDetail;
import com.depromeet.hanriver.hanrivermeetup.model.meeting.JoinRequest;

import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface HostService {
    @GET("meeting/{meetingID}")
    MeetingDetail getMeetingDetail(@Path("meetingID") int meetingID);

    @POST("meeting/")
    MeetingDetail createMeeting(@Body MeetingDetail meetingDetail);

    @PUT("meeting/{meetingID}")
    MeetingDetail updateMeeting(@Path("meetingID") int meetingID, @Body MeetingDetail user);

    @DELETE("meeting/{meetingID}")
    boolean deleteMeeting(@Path("meetingID") int meetingID);

    @GET("meetings/today")
    MeetingDetail getMeetingsOnToday();

    @GET("requests/{requestID}")
    JoinRequest getRequestsById(@Path("requestID") int id);
}
