package com.depromeet.hanriver.hanrivermeetup.network;

import com.depromeet.hanriver.hanrivermeetup.model.meeting.Comment;
import com.depromeet.hanriver.hanrivermeetup.model.meeting.MatchingDetail;
import com.depromeet.hanriver.hanrivermeetup.model.meeting.MeetingDetail;
import com.depromeet.hanriver.hanrivermeetup.model.mypage.TestVO;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CommunicationAPIService {
    @GET("comments/{meetingID}")
    Observable<Response<List<Comment>>> getComments(@Path("meetingID") int meetingID);

    @POST("comment/")
    Observable<Response<Comment>> addComment(@Body Comment comment);

    @DELETE("comment/{comment_seq}")
    Observable<Response<Boolean>> deleteComment(@Path("comment_seq") int commentID);

    @POST("match/")
    Observable<MatchingDetail> match(@Body MatchingDetail matchingDetail);

    @GET("match/{contactID}")
    MatchingDetail getMatchingDetail(@Path("contactID") int contactID);

    @POST("match/find")
    MatchingDetail getMatchingDetails(@Body MatchingDetail matchingDetail);

    @DELETE("match/{contactID}")
    boolean cancleMatch(@Path("contactID") int contactID);
}
