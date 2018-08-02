package com.depromeet.hanriver.hanrivermeetup.service;

import com.depromeet.hanriver.hanrivermeetup.model.meeting.Comment;
import com.depromeet.hanriver.hanrivermeetup.network.APIUtiles;
import com.depromeet.hanriver.hanrivermeetup.network.CommunicationAPIService;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

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

    public Observable<List<Comment>> getComments(int meetingId){
        return mService.getComments(meetingId)
                .subscribeOn(Schedulers.io())
                .map(it -> it);
    }

    public Observable<Comment> addComment(Comment comment){
        return mService.addComment(comment)
                .subscribeOn(Schedulers.io())
                .map(it -> it);
    }
}
