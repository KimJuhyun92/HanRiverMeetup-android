package com.depromeet.hanriver.hanrivermeetup.datamodel.meeting;

import android.support.annotation.NonNull;

import com.depromeet.hanriver.hanrivermeetup.model.meeting.JoinRequest;

import io.reactivex.Observable;

public interface IMeetingJoinDataModel {
    @NonNull
    Observable<JoinRequest> setJoin(JoinRequest joinRequest);

}
