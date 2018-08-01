package com.depromeet.hanriver.hanrivermeetup.datamodel.meeting;

import android.graphics.Paint;
import android.support.annotation.NonNull;

import com.depromeet.hanriver.hanrivermeetup.model.meeting.JoinRequest;
import com.depromeet.hanriver.hanrivermeetup.model.meeting.MeetingDetail;

import io.reactivex.Observable;

public class MeetingJoinDataModel implements IMeetingJoinDataModel {

    @NonNull
    @Override
    public Observable<JoinRequest> setJoin(JoinRequest joinRequest) {
        return Observable.fromCallable(this::RequestJoin);
    }

    @NonNull
    private JoinRequest RequestJoin() {

        return null;
    }
}
