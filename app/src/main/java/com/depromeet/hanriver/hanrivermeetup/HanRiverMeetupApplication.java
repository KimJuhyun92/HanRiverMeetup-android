package com.depromeet.hanriver.hanrivermeetup;

import android.app.Application;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatDialog;
import android.widget.ImageView;

import com.depromeet.hanriver.hanrivermeetup.common.PreferencesManager;
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
import com.depromeet.hanriver.hanrivermeetup.datamodel.mypage.IMyPageTab2DataModel;
import com.depromeet.hanriver.hanrivermeetup.datamodel.mypage.IMyPageTab3DataModel;
import com.depromeet.hanriver.hanrivermeetup.datamodel.mypage.MyPageTab2DataModel;
import com.depromeet.hanriver.hanrivermeetup.datamodel.mypage.MyPageTab3DataModel;
import com.depromeet.hanriver.hanrivermeetup.datamodel.mypage.MyPageTab1DataModel;
import com.depromeet.hanriver.hanrivermeetup.firebase.CustomFirebaseInstanceIDService;
import com.depromeet.hanriver.hanrivermeetup.fragment.meeting.MeetingCategoryViewModel;
import com.depromeet.hanriver.hanrivermeetup.fragment.meeting.MeetingCommentViewModel;
import com.depromeet.hanriver.hanrivermeetup.fragment.meeting.MeetingDetailViewModel;
import com.depromeet.hanriver.hanrivermeetup.fragment.mypage.ViewModel.Tab1ViewModel;

import com.depromeet.hanriver.hanrivermeetup.datamodel.meeting.ListFragment.IMeetingListInnerDataModel;
import com.depromeet.hanriver.hanrivermeetup.datamodel.meeting.ListFragment.MeetingListInnerDataModel;
import com.depromeet.hanriver.hanrivermeetup.fragment.meeting.MeetingListInnerViewModel;

import com.depromeet.hanriver.hanrivermeetup.fragment.mypage.ViewModel.Tab2ViewModel;
import com.depromeet.hanriver.hanrivermeetup.fragment.mypage.ViewModel.Tab3ViewModel;
import com.depromeet.hanriver.hanrivermeetup.schedulers.ISchedulerProvider;
import com.depromeet.hanriver.hanrivermeetup.schedulers.SchedulerProvider;

public class HanRiverMeetupApplication extends Application {
    @NonNull
    private static HanRiverMeetupApplication hanRiverMeetupApplication;

    AppCompatDialog progressDialog;

    @NonNull
    private final IActivityDataModel mActivityDataModel;

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

    public static HanRiverMeetupApplication getInstance(){
        return hanRiverMeetupApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        hanRiverMeetupApplication = this;
        setPreferences();
    }

    public void setPreferences() {
        PreferencesManager.setManager(this);
        PreferencesManager.setNickname("");
        PreferencesManager.setFacebookToken("");
        PreferencesManager.setUserID("");
        String fcmToken = PreferencesManager.getFcmToken();

        if(fcmToken.isEmpty()) {
            PreferencesManager.setFcmToken(CustomFirebaseInstanceIDService.getToken());
        }
    }

    public HanRiverMeetupApplication() {
        mActivityDataModel = new ActivityDataModel();
        mTab1DataModel = new MyPageTab1DataModel();
        mTab2DataModel = new MyPageTab2DataModel();
        mTab3DataModel = new MyPageTab3DataModel();
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
    public IMyPageTab1DataModel getTab1DataModel() {
        return mTab1DataModel;
    }

    @NonNull
    public IMyPageTab2DataModel getTab2DataModel() {
        return mTab2DataModel;
    }

    @NonNull
    public IMyPageTab3DataModel getTab3DataModel() {
        return mTab3DataModel;
    }

    public IMeetingListInnerDataModel getMeetingListInnerDataModel() {
        return mMeetingListInnerDataModel;
    }

    @NonNull
    public IMeetingDetailDataModel getMeetingDeailtDataModel() {
        return mMeetingDetailDataModel;
    }

    @NonNull
    public IMeetingListInnerDataModel getChickenListDataModel() {
        return mChickenDataModel;
    }

    @NonNull
    public IMeetingListInnerDataModel getCampingDataModel() {
        return mCampingDataModel;
    }

    @NonNull
    public IMeetingListInnerDataModel getBikeDataModel() {
        return mBikeDataModel;
    }

    @NonNull
    public IMeetingListInnerDataModel getBoatDataModel() {
        return mBoatDataModel;
    }

    @NonNull
    public IMeetingListInnerDataModel getPhotoDataModel() {
        return mPhotoDataModel;
    }

    @NonNull
    public IMeetingListInnerDataModel getEtcDataModel() {
        return mEtcDataModel;
    }

    @NonNull
    public ICommentDataModel getCommentDataModel() {
        return mCommentDataModel;
    }
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


    public MeetingListInnerViewModel getMeetingListInnerViewModel() {
        return new MeetingListInnerViewModel(getMeetingListInnerDataModel(), getSchedulerProvider());
    }

    public MeetingDetailViewModel getMeetingDetailViewModel() {
        return new MeetingDetailViewModel(getMeetingDeailtDataModel(), getSchedulerProvider());
    }

    public MeetingListInnerViewModel getChickenListViewModel() {
        return new MeetingListInnerViewModel(getChickenListDataModel(), getSchedulerProvider());
    }

    public MeetingListInnerViewModel getBikeListViewModel() {
        return new MeetingListInnerViewModel(getBikeDataModel(), getSchedulerProvider());
    }

    public MeetingListInnerViewModel getBoatListViewModel() {
        return new MeetingListInnerViewModel(getBoatDataModel(), getSchedulerProvider());
    }

    public MeetingListInnerViewModel getCampingListViewModel() {
        return new MeetingListInnerViewModel(getCampingDataModel(), getSchedulerProvider());
    }

    public MeetingListInnerViewModel getPhotoListViewModel() {
        return new MeetingListInnerViewModel(getPhotoDataModel(), getSchedulerProvider());
    }

    public MeetingListInnerViewModel getEtcListViewModel() {
        return new MeetingListInnerViewModel(getEtcDataModel(), getSchedulerProvider());
    }

    public MeetingCommentViewModel getCommentViewModel() {
        return new MeetingCommentViewModel(getCommentDataModel(), getSchedulerProvider());
    }

//    @NonNull
//    public CreateRoomDataModel getCreateRoomDataModel(){
//        return new CreateRoomDataModel(getCreateRoomDataModel(),getSchedulerProvider());
//    }

    //새로고침 Dialog 시작.
    public void progressON(android.app.Activity activity) {

        if (activity == null || activity.isFinishing()) {
            return;
        }
//        if (progressDialog != null && progressDialog.isShowing()) {
//            progressSET(message);
//        } else {
        progressDialog = new AppCompatDialog(activity);
        progressDialog.setCancelable(false);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        progressDialog.setContentView(R.layout.dialog_loading);
        progressDialog.show();
//        }
        final ImageView img_loading_frame = (ImageView) progressDialog.findViewById(R.id.iv_frame_loading);
        final AnimationDrawable frameAnimation = (AnimationDrawable) img_loading_frame.getBackground();
        img_loading_frame.post(new Runnable() {
            @Override
            public void run() {
                frameAnimation.start();
            }
        });
    }


    //새로고침 Dialog 종료
    public void progressOFF(SwipeRefreshLayout swipeRefreshLayout) {

        if (progressDialog != null && progressDialog.isShowing()) {
            if(swipeRefreshLayout.isRefreshing()==true) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                        progressDialog.dismiss();
                    }
                }, 1500);
            }

        }
    }


}
