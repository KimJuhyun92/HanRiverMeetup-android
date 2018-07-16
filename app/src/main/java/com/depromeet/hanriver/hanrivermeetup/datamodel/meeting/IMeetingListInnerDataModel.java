package com.depromeet.hanriver.hanrivermeetup.datamodel.meeting;

import android.support.annotation.NonNull;

import com.depromeet.hanriver.hanrivermeetup.model.meeting.Activity;
import com.depromeet.hanriver.hanrivermeetup.model.meeting.MeetingDetail;

import java.util.List;

import io.reactivex.Observable;

public interface IMeetingListInnerDataModel {

    @NonNull
    Observable<List<MeetingDetail>> getAvailableRooms();
}
