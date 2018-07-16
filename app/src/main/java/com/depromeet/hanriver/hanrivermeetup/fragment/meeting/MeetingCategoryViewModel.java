package com.depromeet.hanriver.hanrivermeetup.fragment.meeting;

import android.support.annotation.NonNull;
import android.view.View;

import com.depromeet.hanriver.hanrivermeetup.datamodel.meeting.IActivityDataModel;
import com.depromeet.hanriver.hanrivermeetup.model.meeting.Activity;
import com.depromeet.hanriver.hanrivermeetup.schedulers.ISchedulerProvider;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

public class MeetingCategoryViewModel {
    @NonNull
    IActivityDataModel mActivityDataModel;

    @NonNull
    private final ISchedulerProvider mSchedulerProvider;

    @NonNull
    private final BehaviorSubject<Activity> mSelectedActivity = BehaviorSubject.create();

    public MeetingCategoryViewModel(@NonNull final IActivityDataModel activityDataModel,
                         @NonNull final ISchedulerProvider schedulerProvider) {
        mActivityDataModel = activityDataModel;
        mSchedulerProvider = schedulerProvider;
    }

    @NonNull
    public Observable<List<Activity>> getAvailableActivites() {
        return mActivityDataModel.getAvailableActivites();
    }


}
