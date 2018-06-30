package com.depromeet.hanriver.hanrivermeetup.datamodel.meeting;

import android.support.annotation.NonNull;

import com.depromeet.hanriver.hanrivermeetup.model.timeline.TimeLineVO;

import java.util.List;

import io.reactivex.Observable;

public interface ITimelineDataModel {
    @NonNull
    Observable<List<TimeLineVO>> getAvailableTimeLineVOs();
}
