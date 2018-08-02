package com.depromeet.hanriver.hanrivermeetup.fragment.meeting;

import android.support.annotation.NonNull;

import com.depromeet.hanriver.hanrivermeetup.datamodel.meeting.ICommentDataModel;
import com.depromeet.hanriver.hanrivermeetup.model.meeting.Comment;
import com.depromeet.hanriver.hanrivermeetup.schedulers.ISchedulerProvider;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

public class MeetingCommentViewModel {
    @NonNull
    ICommentDataModel mCommentDataModel;

    @NonNull
    private final ISchedulerProvider mSchedulerProvider;

    @NonNull
    private final BehaviorSubject<Comment> mSelectedActivity = BehaviorSubject.create();

    public MeetingCommentViewModel(@NonNull final ICommentDataModel commentDataModel,
                                    @NonNull final ISchedulerProvider schedulerProvider) {
        mCommentDataModel = commentDataModel;
        mSchedulerProvider = schedulerProvider;
    }

    @NonNull
    public Observable<List<Comment>> getComments(int meeting_seq) {
        return mCommentDataModel.getComments(meeting_seq);
    }
}
