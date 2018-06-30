package com.depromeet.hanriver.hanrivermeetup.fragment.timeline;

import android.support.annotation.NonNull;

import com.depromeet.hanriver.hanrivermeetup.datamodel.meeting.ITimelineDataModel;
import com.depromeet.hanriver.hanrivermeetup.model.timeline.TimeLineVO;
import com.depromeet.hanriver.hanrivermeetup.schedulers.ISchedulerProvider;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

public class TimelineViewModel {
    @NonNull
    ITimelineDataModel mTimelineDataModel;

    @NonNull
    private ISchedulerProvider mSchedulerProvider;

    @NonNull
    private final BehaviorSubject<TimeLineVO> mSelectedActivity = BehaviorSubject.create();

    public TimelineViewModel(@NonNull final ITimelineDataModel timelineDataModel,
                             @NonNull final ISchedulerProvider schedulerProvider) {
        mTimelineDataModel = timelineDataModel;
        mSchedulerProvider = schedulerProvider;
    }

    @NonNull
    public Observable<List<TimeLineVO>> getAvailableTimelineVOs() {
        return mTimelineDataModel.getAvailableTimeLineVOs();
    }

}
