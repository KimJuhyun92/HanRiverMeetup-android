package com.depromeet.hanriver.hanrivermeetup.service;

import com.depromeet.hanriver.hanrivermeetup.model.meeting.Comment;
import com.depromeet.hanriver.hanrivermeetup.model.meeting.MatchingDetail;
import com.depromeet.hanriver.hanrivermeetup.network.APIUtiles;
import com.depromeet.hanriver.hanrivermeetup.network.CommunicationAPIService;

import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class CommunicationService {
    private static final CommunicationService ourInstance = new CommunicationService();
    private CommunicationAPIService mService;

    public static CommunicationService getInstance() {
        return ourInstance;
    }

    private CommunicationService() { }

    public void setService(String token, String id) {
        mService = APIUtiles.getCommunicationService(token, id);
}

    public Observable<Response<List<Comment>>> getComments(int meetingId){
        return mService.getComments(meetingId)
                .subscribeOn(Schedulers.io())
                .doOnNext(res -> {
                    if(res.code() == HttpsURLConnection.HTTP_OK){
                        res.body();
                    }
                });
    }

    public Observable<Response<Comment>> addComment(Comment comment){
        return mService.addComment(comment)
                .subscribeOn(Schedulers.io())
                .doOnNext(res->{
                    if(res.code() == HttpsURLConnection.HTTP_OK){
                        res.body();
                    }
                });
    }

    public Observable<MatchingDetail> match(MatchingDetail matchingDetail){
        return mService.match(matchingDetail)
                .subscribeOn(Schedulers.io());
    }

    public Observable<Response<Boolean>> deleteComment(int comment_seq){
        return mService.deleteComment(comment_seq)
                .subscribeOn(Schedulers.io())
                .doOnNext(res->{
                    if(res.code() == HttpsURLConnection.HTTP_OK){
                        res.body();
                    }
                });
    }
}
