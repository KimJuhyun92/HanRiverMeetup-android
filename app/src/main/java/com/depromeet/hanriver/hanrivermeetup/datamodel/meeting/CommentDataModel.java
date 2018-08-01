package com.depromeet.hanriver.hanrivermeetup.datamodel.meeting;

import android.support.annotation.NonNull;

import com.depromeet.hanriver.hanrivermeetup.model.meeting.Comment;
import com.depromeet.hanriver.hanrivermeetup.service.CommunicationService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;

public class CommentDataModel implements ICommentDataModel{
    int meeting_seq;

    @NonNull
    @Override
    public Observable<List<Comment>> getComments(int meeting_seq) {
        this.meeting_seq=meeting_seq;
        return Observable.fromCallable(this::getCommentlist);
    }

    @NonNull
    private List<Comment> getCommentlist() {
        List<Comment> comments = new ArrayList<>();
        CommunicationService.getInstance().getComments(meeting_seq).subscribe(
                list->{
                    comments.addAll(list);
                }
        );

        return comments;
//        return Arrays
//                .asList(new Comment("123123123123","같이 하고 싶어요!","11:00","최준영"),
//                        new Comment("123123123123","같이 하고 싶어요!","11:00","최준영"),
//                        new Comment("123123123123","같이 하고 싶어요!","11:00","최준영"));
    }
}
