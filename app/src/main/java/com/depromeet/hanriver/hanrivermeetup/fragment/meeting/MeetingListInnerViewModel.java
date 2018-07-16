package com.depromeet.hanriver.hanrivermeetup.fragment.meeting;

import android.support.annotation.NonNull;

import com.depromeet.hanriver.hanrivermeetup.datamodel.meeting.IActivityDataModel;
import com.depromeet.hanriver.hanrivermeetup.datamodel.meeting.IMeetingListInnerDataModel;
import com.depromeet.hanriver.hanrivermeetup.model.meeting.Activity;
import com.depromeet.hanriver.hanrivermeetup.model.meeting.MeetingDetail;
import com.depromeet.hanriver.hanrivermeetup.schedulers.ISchedulerProvider;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

public class MeetingListInnerViewModel {
    @NonNull
    IMeetingListInnerDataModel mRoomDataModel;

    @NonNull
    private final ISchedulerProvider mSchedulerProvider;

    @NonNull
    private final BehaviorSubject<MeetingDetail> mSelectedRoom = BehaviorSubject.create();

    public MeetingListInnerViewModel(@NonNull final IMeetingListInnerDataModel roomDataModel,
                                    @NonNull final ISchedulerProvider schedulerProvider) {
        mRoomDataModel = roomDataModel;
        mSchedulerProvider = schedulerProvider;
    }

    @NonNull
    public Observable<List<MeetingDetail>> getAvailableRooms() {
        return mRoomDataModel.getAvailableRooms();
    }
}
