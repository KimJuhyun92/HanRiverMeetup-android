package com.depromeet.hanriver.hanrivermeetup.network;

import com.depromeet.hanriver.hanrivermeetup.model.meeting.CreateRoom;
import com.depromeet.hanriver.hanrivermeetup.model.meeting.MeetingDetail;
import com.depromeet.hanriver.hanrivermeetup.model.meeting.JoinRequest;
import com.depromeet.hanriver.hanrivermeetup.model.mypage.ApplicantVO;
import com.depromeet.hanriver.hanrivermeetup.model.mypage.Tab1VO;
import com.google.gson.JsonObject;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface HostAPIService {
    @GET("meeting/{meeting_seq}")
    Observable<Response<MeetingDetail>> getMeetingDetail(@Path("meeting_seq") int meetingID);

    @POST("meeting/")
    Observable<Response<MeetingDetail>> createMeeting(@Body MeetingDetail createRoom);

    @GET("meetings/today")
    Observable<List<MeetingDetail>> getMeetingsOnToday();

    @GET("requests/{requestID}")
    JoinRequest getRequestsById(@Path("requestID") int id);

    @GET("requests/{meeting_seq}")
    Observable<List<ApplicantVO>> getMeetingApplicants(@Path("meeting_seq") int meeting_seq);

    @PUT("meeting/{meeting_seq}")
    Observable<Response<MeetingDetail>> modifyMeeting(@Path("meeting_seq")int meeting_seq, @Body MeetingDetail meetingDetail);

    @GET("meetings/week/{activity_seq}")
    Observable<Response<List<MeetingDetail>>> getMeetingsOnWeek(@Path("activity_seq")int activity_seq);
}
