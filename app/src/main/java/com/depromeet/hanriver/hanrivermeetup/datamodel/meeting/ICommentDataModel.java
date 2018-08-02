package com.depromeet.hanriver.hanrivermeetup.datamodel.meeting;

import android.support.annotation.NonNull;


import com.depromeet.hanriver.hanrivermeetup.model.meeting.Comment;

import java.util.List;

import io.reactivex.Observable;

public interface ICommentDataModel {
    @NonNull
    Observable<List<Comment>> getComments(int meeting_seq);
}
