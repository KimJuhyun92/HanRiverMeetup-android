package com.depromeet.hanriver.hanrivermeetup.datamodel.meeting;

import android.support.annotation.NonNull;
import android.util.Log;

import com.depromeet.hanriver.hanrivermeetup.R;
import com.depromeet.hanriver.hanrivermeetup.model.meeting.MeetingDetail;
import com.depromeet.hanriver.hanrivermeetup.service.HostService;

import java.util.List;

import io.reactivex.Observable;

public class MeetingDetailDataModel implements IMeetingDetailDataModel {
    int meeting_seq;
    MeetingDetail md;
    @NonNull
    @Override
    public Observable<MeetingDetail> getMeetingDetail(int meeting_seq) {
        this.meeting_seq=meeting_seq;
        return Observable.fromCallable(this::getDetail);

    }
    @NonNull
    private MeetingDetail getDetail() {

//        HostService.getInstance().getMeetingDetail(meeting_seq).subscribe(
//                it->{
//                    md= it;
//                    Log.d("@@@@@@","md : "+md.getDescription());
//                }
//        );
//
//        return md;
        return new MeetingDetail(0,0,"123123123","여의도 한강공원에서 놀아요",3,"여의도","12:00",30000,"12:00","12:00","한강 공원 가자","01012341234",1,"nick");
    }
}
