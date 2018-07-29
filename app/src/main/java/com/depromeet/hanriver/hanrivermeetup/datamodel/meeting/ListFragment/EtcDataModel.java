package com.depromeet.hanriver.hanrivermeetup.datamodel.meeting.ListFragment;

import android.support.annotation.NonNull;

import com.depromeet.hanriver.hanrivermeetup.model.meeting.MeetingDetail;
import com.depromeet.hanriver.hanrivermeetup.service.HostService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class EtcDataModel implements IMeetingListInnerDataModel {
    @NonNull

    @Override
    public Observable<List<MeetingDetail>> getAvailableRooms() {
        return Observable.fromCallable(this::getRooms);
    }

    @NonNull
    private List<MeetingDetail> getRooms() {
        List<MeetingDetail> mdlist = new ArrayList<>();

        HostService.getInstance().getTodayList().subscribe(
                list ->{
                    for(int i =0 ;i<list.size();i++){
                        if(list.get(i).getActivity_seq()==6)
                            mdlist.add(list.get(i));
                    }
                }

        );
        return mdlist;
    }
}
