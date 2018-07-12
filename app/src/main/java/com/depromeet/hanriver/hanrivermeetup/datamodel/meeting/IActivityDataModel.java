package com.depromeet.hanriver.hanrivermeetup.datamodel.meeting;

import android.support.annotation.NonNull;

import com.depromeet.hanriver.hanrivermeetup.model.meeting.Activity;

import java.util.List;

import io.reactivex.Observable;

public interface IActivityDataModel {
    @NonNull
    Observable<List<Activity>> getAvailableActivites();

//    Observable<> loadMeetinglistFragment();
}
