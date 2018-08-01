package com.depromeet.hanriver.hanrivermeetup;

import android.app.Application;
import android.support.annotation.NonNull;

import com.depromeet.hanriver.hanrivermeetup.datamodel.meeting.ActivityDataModel;
import com.depromeet.hanriver.hanrivermeetup.datamodel.meeting.CommentDataModel;
import com.depromeet.hanriver.hanrivermeetup.datamodel.meeting.IActivityDataModel;

import com.depromeet.hanriver.hanrivermeetup.datamodel.meeting.ICommentDataModel;
import com.depromeet.hanriver.hanrivermeetup.datamodel.meeting.IMeetingDetailDataModel;
import com.depromeet.hanriver.hanrivermeetup.datamodel.meeting.ListFragment.BikeDataModel;
import com.depromeet.hanriver.hanrivermeetup.datamodel.meeting.ListFragment.BoatDataModel;
import com.depromeet.hanriver.hanrivermeetup.datamodel.meeting.ListFragment.CampingDataModel;
import com.depromeet.hanriver.hanrivermeetup.datamodel.meeting.ListFragment.ChickenDataModel;
import com.depromeet.hanriver.hanrivermeetup.datamodel.meeting.ListFragment.EtcDataModel;
import com.depromeet.hanriver.hanrivermeetup.datamodel.meeting.ListFragment.PhotoDataModel;
import com.depromeet.hanriver.hanrivermeetup.datamodel.meeting.MeetingDetailDataModel;
import com.depromeet.hanriver.hanrivermeetup.datamodel.mypage.IMyPageTab1DataModel;
import com.depromeet.hanriver.hanrivermeetup.datamodel.timeline.ITimelineDataModel;
import com.depromeet.hanriver.hanrivermeetup.datamodel.mypage.MyPageTab1DataModel;
import com.depromeet.hanriver.hanrivermeetup.datamodel.timeline.TimelineDataModel;
import com.depromeet.hanriver.hanrivermeetup.fragment.meeting.MeetingCategoryViewModel;
import com.depromeet.hanriver.hanrivermeetup.fragment.meeting.MeetingCommentViewModel;
import com.depromeet.hanriver.hanrivermeetup.fragment.meeting.MeetingDetailViewModel;
import com.depromeet.hanriver.hanrivermeetup.fragment.mypage.ViewModel.Tab1ViewModel;

import com.depromeet.hanriver.hanrivermeetup.datamodel.meeting.ListFragment.IMeetingListInnerDataModel;
import com.depromeet.hanriver.hanrivermeetup.datamodel.meeting.ListFragment.MeetingListInnerDataModel;
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
    private final IMyPageTab1DataModel mTab1DataModel;

    @NonNull
    private final IMeetingListInnerDataModel mMeetingListInnerDataModel;

    @NonNull
    private final IMeetingDetailDataModel mMeetingDetailDataModel;

    @NonNull
    private final IMeetingListInnerDataModel mChickenDataModel;

    @NonNull
    private final IMeetingListInnerDataModel mBoatDataModel;
    @NonNull
    private final IMeetingListInnerDataModel mCampingDataModel;
    @NonNull
    private final IMeetingListInnerDataModel mBikeDataModel;
    @NonNull
    private final IMeetingListInnerDataModel mPhotoDataModel;
    @NonNull
    private final IMeetingListInnerDataModel mEtcDataModel;


    @NonNull
    private final ICommentDataModel mCommentDataModel;
//    @NonNull
//    private final ICreateRoomDataModel mCreateRoomDataModel;



    public HanRiverMeetupApplication() {
        mActivityDataModel = new ActivityDataModel();
        mTimelineDataModel = new TimelineDataModel();
        mTab1DataModel =  new MyPageTab1DataModel();
        mMeetingListInnerDataModel = new MeetingListInnerDataModel();
        mMeetingDetailDataModel = new MeetingDetailDataModel();
        mChickenDataModel = new ChickenDataModel();
        mBikeDataModel = new BikeDataModel();
        mBoatDataModel = new BoatDataModel();
        mCampingDataModel = new CampingDataModel();
        mPhotoDataModel = new PhotoDataModel();
        mEtcDataModel = new EtcDataModel();
        mCommentDataModel = new CommentDataModel();
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
    public IMeetingListInnerDataModel getMeetingListInnerDataModel(){ return mMeetingListInnerDataModel;}

    @NonNull
    public IMeetingDetailDataModel getMeetingDeailtDataModel(){ return mMeetingDetailDataModel; }

    @NonNull
    public IMeetingListInnerDataModel getChickenListDataModel(){ return mChickenDataModel; }
    @NonNull
    public IMeetingListInnerDataModel getCampingDataModel(){ return mCampingDataModel; }
    @NonNull
    public IMeetingListInnerDataModel getBikeDataModel(){ return mBikeDataModel; }
    @NonNull
    public IMeetingListInnerDataModel getBoatDataModel(){ return mBoatDataModel; }
    @NonNull
    public IMeetingListInnerDataModel getPhotoDataModel(){ return mPhotoDataModel; }
    @NonNull
    public IMeetingListInnerDataModel getEtcDataModel(){ return mEtcDataModel; }

    @NonNull
    public ICommentDataModel getCommentDataModel(){ return mCommentDataModel;}
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


    public MeetingListInnerViewModel getMeetingListInnerViewModel(){
        return new MeetingListInnerViewModel(getMeetingListInnerDataModel(),getSchedulerProvider());
    }

    public MeetingDetailViewModel getMeetingDetailViewModel(){
        return new MeetingDetailViewModel(getMeetingDeailtDataModel(),getSchedulerProvider());
    }

    public MeetingListInnerViewModel getChickenListViewModel(){
        return new MeetingListInnerViewModel(getChickenListDataModel(),getSchedulerProvider());
    }

    public MeetingListInnerViewModel getBikeListViewModel(){
        return new MeetingListInnerViewModel(getBikeDataModel(),getSchedulerProvider());
    }
    public MeetingListInnerViewModel getBoatListViewModel(){
        return new MeetingListInnerViewModel(getBoatDataModel(),getSchedulerProvider());
    }
    public MeetingListInnerViewModel getCampingListViewModel(){
        return new MeetingListInnerViewModel(getCampingDataModel(),getSchedulerProvider());
    }
    public MeetingListInnerViewModel getPhotoListViewModel(){
        return new MeetingListInnerViewModel(getPhotoDataModel(),getSchedulerProvider());
    }
    public MeetingListInnerViewModel getEtcListViewModel(){
        return new MeetingListInnerViewModel(getEtcDataModel(),getSchedulerProvider());
    }

    public MeetingCommentViewModel getCommentViewModel(){
        return new MeetingCommentViewModel(getCommentDataModel(),getSchedulerProvider());
    }

//    @NonNull
//    public CreateRoomDataModel getCreateRoomDataModel(){
//        return new CreateRoomDataModel(getCreateRoomDataModel(),getSchedulerProvider());
//    }



}
