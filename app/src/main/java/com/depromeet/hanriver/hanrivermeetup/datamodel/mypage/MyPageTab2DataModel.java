package com.depromeet.hanriver.hanrivermeetup.datamodel.mypage;

import android.support.annotation.NonNull;

import com.depromeet.hanriver.hanrivermeetup.model.mypage.Tab1VO;
import com.depromeet.hanriver.hanrivermeetup.model.mypage.Tab2VO;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;

public class MyPageTab2DataModel implements IMyPageTab2DataModel{

    @NonNull
    @Override
    public Observable<List<Tab2VO>> getAvailableTab2VOs() {
        return Observable.fromCallable(this::getTab2VOs);
    }

    @NonNull
    private List<Tab2VO> getTab2VOs() {
//        return Arrays
//                .asList(new Tab2VO("Test1", "11", 20000, 5),
//                        new Tab2VO("Test2", "8", 15000, 10),
//                        new Tab2VO("Test3", "12", 30000, 8));
        return null;
    }
}
