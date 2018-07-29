package com.depromeet.hanriver.hanrivermeetup.datamodel.meeting.ListFragment;

import android.support.annotation.NonNull;
import android.util.Log;

import com.depromeet.hanriver.hanrivermeetup.model.meeting.MeetingDetail;
import com.depromeet.hanriver.hanrivermeetup.service.HostService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class ChickenDataModel implements IMeetingListInnerDataModel {
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
                        if(list.get(i).getActivity_seq()==1)
                        mdlist.add(list.get(i));
                    }
                }

        );
          return mdlist;
          // 리스트 반환이 아닌 rx 사용한 observable 형태로 반환하는 구조변경이 필요함.  +filter() 알아 볼것.
    }
}
