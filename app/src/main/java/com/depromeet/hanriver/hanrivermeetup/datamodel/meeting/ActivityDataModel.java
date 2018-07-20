package com.depromeet.hanriver.hanrivermeetup.datamodel.meeting;

import android.support.annotation.NonNull;

import com.depromeet.hanriver.hanrivermeetup.R;
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
                .asList(new Activity("치킨 같이먹을 사람~", "!", R.drawable.ic_chicken_icon),
                        new Activity("자전거 같이 탈래?", "!",R.drawable.ic_bike_icon),
                        new Activity("오리배 타자!", "!",R.drawable.ic_ori_icon),
                        new Activity("캠핑 함께 즐겨요~", "!",R.drawable.ic_camping_icon),
                        new Activity("사진 같이 찍을래?", "!",R.drawable.ic_photo_icon),
                        new Activity("그 밖의 한강모임들", "!",R.drawable.ic_etc_icon));
    }
}
