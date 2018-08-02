package com.depromeet.hanriver.hanrivermeetup;

import android.app.Application;
import android.support.annotation.NonNull;

import com.depromeet.hanriver.hanrivermeetup.datamodel.meeting.ActivityDataModel;
import com.depromeet.hanriver.hanrivermeetup.datamodel.meeting.IActivityDataModel;

import com.depromeet.hanriver.hanrivermeetup.datamodel.meeting.IMeetingDetailDataModel;
import com.depromeet.hanriver.hanrivermeetup.datamodel.meeting.MeetingDetailDataModel;
import com.depromeet.hanriver.hanrivermeetup.datamodel.mypage.IMyPageTab1DataModel;
import com.depromeet.hanriver.hanrivermeetup.datamodel.mypage.IMyPageTab2DataModel;
import com.depromeet.hanriver.hanrivermeetup.datamodel.mypage.IMyPageTab3DataModel;
import com.depromeet.hanriver.hanrivermeetup.datamodel.mypage.MyPageTab2DataModel;
import com.depromeet.hanriver.hanrivermeetup.datamodel.mypage.MyPageTab3DataModel;
import com.depromeet.hanriver.hanrivermeetup.datamodel.timeline.ITimelineDataModel;
import com.depromeet.hanriver.hanrivermeetup.datamodel.mypage.MyPageTab1DataModel;
import com.depromeet.hanriver.hanrivermeetup.datamodel.timeline.TimelineDataModel;
import com.depromeet.hanriver.hanrivermeetup.fragment.meeting.MeetingCategoryViewModel;
import com.depromeet.hanriver.hanrivermeetup.fragment.meeting.MeetingDetailViewModel;
import com.depromeet.hanriver.hanrivermeetup.fragment.mypage.Tab3;
import com.depromeet.hanriver.hanrivermeetup.fragment.mypage.ViewModel.Tab1ViewModel;

import com.depromeet.hanriver.hanrivermeetup.datamodel.meeting.IMeetingListInnerDataModel;
import com.depromeet.hanriver.hanrivermeetup.datamodel.meeting.MeetingListInnerDataModel;
import com.depromeet.hanriver.hanrivermeetup.fragment.meeting.MeetingListInnerViewModel;

import com.depromeet.hanriver.hanrivermeetup.fragment.mypage.ViewModel.Tab2ViewModel;
import com.depromeet.hanriver.hanrivermeetup.fragment.mypage.ViewModel.Tab3ViewModel;
import com.depromeet.hanriver.hanrivermeetup.fragment.timeline.TimelineViewModel;
import com.depromeet.hanriver.hanrivermeetup.schedulers.ISchedulerProvider;
import com.depromeet.hanriver.hanrivermeetup.schedulers.SchedulerProvider;

public class HanRiverMeetupApplication extends Application {

    @NonNull
    private final IActivityDataModel mActivityDataModel;

    @NonNull
    private final ITimelineDataModel mTimelineDataModel;

    @NonNull
    private final IMyPageTab1DataModel mTab1DataModel;

    @NonNull
    private final IMyPageTab2DataModel mTab2DataModel;

    @NonNull
    private final IMyPageTab3DataModel mTab3DataModel;



    @NonNull
    private final IMeetingListInnerDataModel mMeetingListInnerDataModel;

    @NonNull
    private final IMeetingDetailDataModel mMeetingDetailDataModel;

//    @NonNull
//    private final ICreateRoomDataModel mCreateRoomDataModel;



    public HanRiverMeetupApplication() {
        mActivityDataModel = new ActivityDataModel();
        mTimelineDataModel = new TimelineDataModel();
        mTab1DataModel =  new MyPageTab1DataModel();
        mTab2DataModel =  new MyPageTab2DataModel();
        mTab3DataModel =  new MyPageTab3DataModel();
        mMeetingListInnerDataModel = new MeetingListInnerDataModel();
        mMeetingDetailDataModel = new MeetingDetailDataModel();

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
    public IMyPageTab1DataModel getTab1DataModel() { return mTab1DataModel; }

    @NonNull
    public IMyPageTab2DataModel getTab2DataModel() { return mTab2DataModel; }

    @NonNull
    public IMyPageTab3DataModel getTab3DataModel() { return mTab3DataModel; }



    public IMeetingListInnerDataModel getMeetingListInnerDataModel(){ return mMeetingListInnerDataModel;}

    public IMeetingDetailDataModel getMeetingDeailtDataModel(){ return mMeetingDetailDataModel; }
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
    public Tab1ViewModel getTab1ViewModel() {
        return new Tab1ViewModel(getTab1DataModel(), getSchedulerProvider());
    }

    @NonNull
    public Tab2ViewModel getTab2ViewModel() {
        return new Tab2ViewModel(getTab2DataModel(), getSchedulerProvider());
    }

    @NonNull
    public Tab3ViewModel getTab3ViewModel() {
        return new Tab3ViewModel(getTab3DataModel(), getSchedulerProvider());
    }


    public MeetingListInnerViewModel getMeetingListInnerViewModel(){
        return new MeetingListInnerViewModel(getMeetingListInnerDataModel(),getSchedulerProvider());
    }

    public MeetingDetailViewModel getMeetingDetailViewModel(){
        return new MeetingDetailViewModel(getMeetingDeailtDataModel(),getSchedulerProvider());
    }
//    @NonNull
//    public CreateRoomDataModel getCreateRoomDataModel(){
//        return new CreateRoomDataModel(getCreateRoomDataModel(),getSchedulerProvider());
//    }



}
