package com.depromeet.hanriver.hanrivermeetup.datamodel.timeline;

import android.support.annotation.NonNull;

import com.depromeet.hanriver.hanrivermeetup.R;
import com.depromeet.hanriver.hanrivermeetup.model.timeline.TimeLineVO;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;

public class TimelineDataModel implements ITimelineDataModel {

    @NonNull
    @Override
    public Observable<List<TimeLineVO>> getAvailableTimeLineVOs() {
        return Observable.fromCallable(this::getTimeLineVOs);
    }

    @NonNull
    private List<TimeLineVO> getTimeLineVOs() {
        return Arrays
                .asList(new TimeLineVO("test1", R.mipmap.ic_launcher),
                        new TimeLineVO("test2", R.mipmap.ic_launcher_round),
                        new TimeLineVO("test3", R.mipmap.ic_launcher_round));
    }



}
