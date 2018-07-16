package com.depromeet.hanriver.hanrivermeetup;

import android.app.Application;
import android.support.annotation.NonNull;

import com.depromeet.hanriver.hanrivermeetup.datamodel.meeting.ActivityDataModel;
import com.depromeet.hanriver.hanrivermeetup.datamodel.meeting.CreateRoomDataModel;
import com.depromeet.hanriver.hanrivermeetup.datamodel.meeting.IActivityDataModel;
import com.depromeet.hanriver.hanrivermeetup.datamodel.meeting.ICreateRoomDataModel;
import com.depromeet.hanriver.hanrivermeetup.datamodel.meeting.IMeetingListInnerDataModel;
import com.depromeet.hanriver.hanrivermeetup.datamodel.meeting.ITimelineDataModel;
import com.depromeet.hanriver.hanrivermeetup.datamodel.meeting.MeetingListInnerDataModel;
import com.depromeet.hanriver.hanrivermeetup.datamodel.meeting.TimelineDataModel;
import com.depromeet.hanriver.hanrivermeetup.fragment.meeting.MeetingCategoryViewModel;
import com.depromeet.hanriver.hanrivermeetup.fragment.meeting.MeetingListInnerViewModel;
import com.depromeet.hanriver.hanrivermeetup.fragment.timeline.TimelineViewModel;
import com.depromeet.hanriver.hanrivermeetup.schedulers.ISchedulerProvider;
import com.depromeet.hanriver.hanrivermeetup.schedulers.SchedulerProvider;

public class HanRiverMeetupApplication extends Application {

    @NonNull
    private final IActivityDataModel mActivityDataModel;

    @NonNull
    private final ITimelineDataModel mTimelineDataModel;

    @NonNull
    private final IMeetingListInnerDataModel mMeetingListInnerDataModel;

//    @NonNull
//    private final ICreateRoomDataModel mCreateRoomDataModel;


    public HanRiverMeetupApplication() {
        mActivityDataModel = new ActivityDataModel();
        mTimelineDataModel = new TimelineDataModel();
        mMeetingListInnerDataModel = new MeetingListInnerDataModel();
    }

    @NonNull
    public IActivityDataModel getActivityDataModel() {
        return mActivityDataModel;
    }

    @NonNull
    public ITimelineDataModel getTimelineDataModel() {
        return mTimelineDataModel;
    }

    @NonNull
    public IMeetingListInnerDataModel getMeetingListInnerDataModel(){ return mMeetingListInnerDataModel;}
//    @NonNull
//    public ICreateRoomDataModel getCreateRoomDataModel(){
//        return mCreateRoomDataModel;
//    }


    @NonNull
    public ISchedulerProvider getSchedulerProvider() {
        return SchedulerProvider.getInstance();
    }

    @NonNull
    public MeetingCategoryViewModel getMeetingCategoryViewModel() {
        return new MeetingCategoryViewModel(getActivityDataModel(), getSchedulerProvider());
    }

    @NonNull
    public TimelineViewModel getTimelineViewModel() {
        return new TimelineViewModel(getTimelineDataModel(), getSchedulerProvider());
    }

    @NonNull
    public MeetingListInnerViewModel getMeetingListInnerViewModel(){
        return new MeetingListInnerViewModel(getMeetingListInnerDataModel(),getSchedulerProvider());
    }
//    @NonNull
//    public CreateRoomDataModel getCreateRoomDataModel(){
//        return new CreateRoomDataModel(getCreateRoomDataModel(),getSchedulerProvider());
//    }


}
