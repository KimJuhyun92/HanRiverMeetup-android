package com.depromeet.hanriver.hanrivermeetup.fragment.meeting;

import android.support.annotation.NonNull;

import com.depromeet.hanriver.hanrivermeetup.datamodel.meeting.IActivityDataModel;
import com.depromeet.hanriver.hanrivermeetup.datamodel.meeting.IMeetingDetailDataModel;
import com.depromeet.hanriver.hanrivermeetup.model.meeting.Activity;
import com.depromeet.hanriver.hanrivermeetup.model.meeting.MeetingDetail;
import com.depromeet.hanriver.hanrivermeetup.schedulers.ISchedulerProvider;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

public class MeetingDetailViewModel {
    @NonNull
    IMeetingDetailDataModel mMeetingdetaildatamodel;

    @NonNull
    private final ISchedulerProvider mSchedulerProvider;

    @NonNull
    private final BehaviorSubject<MeetingDetail> mSelectedActivity = BehaviorSubject.create();

    public MeetingDetailViewModel(@NonNull final IMeetingDetailDataModel meetingdetaildatamodel,
                                    @NonNull final ISchedulerProvider schedulerProvider) {
        mMeetingdetaildatamodel = meetingdetaildatamodel;
        mSchedulerProvider = schedulerProvider;
    }

    @NonNull
    public Observable<MeetingDetail> getMeetingDetail(int meeting_seq) {
        return mMeetingdetaildatamodel.getMeetingDetail(meeting_seq);
    }
}
