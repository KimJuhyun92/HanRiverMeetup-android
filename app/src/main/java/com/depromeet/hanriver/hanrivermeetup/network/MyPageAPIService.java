package com.depromeet.hanriver.hanrivermeetup.network;

import com.depromeet.hanriver.hanrivermeetup.model.meeting.JoinRequest;
import com.depromeet.hanriver.hanrivermeetup.model.meeting.MeetingDetail;
import com.depromeet.hanriver.hanrivermeetup.model.mypage.Tab1VO;
import com.depromeet.hanriver.hanrivermeetup.model.mypage.Tab2VO;
import com.depromeet.hanriver.hanrivermeetup.model.mypage.Tab3VO;
import com.depromeet.hanriver.hanrivermeetup.model.mypage.TestVO;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MyPageAPIService {

    @GET("{userID}/meetings/")
    Observable<List<Tab1VO>> getMyMeeting(@Path("userID") String userID);

    @GET("{userID}/join/meetings/")
    Observable<List<Tab2VO>> getAppliedMeeting(@Path("userID") String userID);

    @GET("{userID}/matchings/")
    Observable<List<Tab3VO>> getMatchedMeeting(@Path("userID") String userID);
}
