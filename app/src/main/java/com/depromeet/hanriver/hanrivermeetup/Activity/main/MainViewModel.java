package com.depromeet.hanriver.hanrivermeetup.Activity.main;

import android.support.annotation.NonNull;

import com.depromeet.hanriver.hanrivermeetup.datamodel.meeting.IActivityDataModel;
import com.depromeet.hanriver.hanrivermeetup.model.meeting.Activity;
import com.depromeet.hanriver.hanrivermeetup.schedulers.ISchedulerProvider;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

public class MainViewModel {

    @NonNull
    IActivityDataModel mActivityDataModel;

    @NonNull
    private final ISchedulerProvider mSchedulerProvider;

    @NonNull
    private final BehaviorSubject<Activity> mSelectedActivity = BehaviorSubject.create();

    public MainViewModel(@NonNull final IActivityDataModel activityDataModel,
                         @NonNull final ISchedulerProvider schedulerProvider) {
        mActivityDataModel = activityDataModel;
        mSchedulerProvider = schedulerProvider;
    }

    @NonNull
    public Observable<List<Activity>> getAvailableActivites() {
        return mActivityDataModel.getAvailableActivites();
    }
}
