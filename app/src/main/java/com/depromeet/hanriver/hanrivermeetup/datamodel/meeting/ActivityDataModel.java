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
                .asList(new Activity("한강은 역시 치킨이지!", "!", R.drawable.ic_chicken_icon),
                        new Activity("자전거 타고 달려볼까?", "!",R.drawable.ic_bike_icon),
                        new Activity("오리배를 타고 둥둥~", "!",R.drawable.ic_ori_icon),
                        new Activity("캠프 퐈이아!", "!",R.drawable.ic_camping_icon),
                        new Activity("인생샷 찰칵!", "!",R.drawable.ic_photo_icon),
                        new Activity("더 많은 한강 모임들", "!",R.drawable.ic_etc_icon));
    }
}
