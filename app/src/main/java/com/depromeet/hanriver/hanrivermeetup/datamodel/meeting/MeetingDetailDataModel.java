package com.depromeet.hanriver.hanrivermeetup.datamodel.meeting;

import android.support.annotation.NonNull;

import com.depromeet.hanriver.hanrivermeetup.R;
import com.depromeet.hanriver.hanrivermeetup.model.meeting.MeetingDetail;

import java.util.List;

import io.reactivex.Observable;

public class MeetingDetailDataModel implements IMeetingDetailDataModel {
    @NonNull
    @Override
    public Observable<MeetingDetail> getMeetingDetail() {
        return Observable.fromCallable(this::getDetail);

    }
    @NonNull
    private MeetingDetail getDetail() {
        return new MeetingDetail(R.drawable.ic_app_logo,"같이 놀아요!!","여의도 한강 공원","13:00",3,20000,"캠핑 용품은 모두 구비 된 상태라 회비와 몸만 오시면 재밌게 즐기다 가실 수 있을거라고 장담합니다!","010-1234-1234");
    }
}
