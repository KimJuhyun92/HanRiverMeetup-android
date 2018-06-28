package com.depromeet.hanriver.hanrivermeetup.datamodel.meeting;

import android.support.annotation.NonNull;

import com.depromeet.hanriver.hanrivermeetup.model.meeting.Activity;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;

public class ActivityDataModel implements IActivityDataModel{
    @NonNull

    @Override
    public Observable<List<Activity>> getAvailableActivites() {
        return Observable.fromCallable(this::getActivites);
    }

    @NonNull
    private List<Activity> getActivites() {
        return Arrays
                .asList(new Activity("자전거타기", "자전거를 같이 타요!"),
                        new Activity("산책하기", "한강을 거닐어요"),
                        new Activity("오리배타기", "오리배를 같이 타요!"));
    }
}
