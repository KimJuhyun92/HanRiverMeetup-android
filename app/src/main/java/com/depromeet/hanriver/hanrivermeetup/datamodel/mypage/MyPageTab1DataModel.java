package com.depromeet.hanriver.hanrivermeetup.datamodel.mypage;

import android.support.annotation.NonNull;

import com.depromeet.hanriver.hanrivermeetup.R;
import com.depromeet.hanriver.hanrivermeetup.model.mypage.Tab1VO;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;

public class MyPageTab1DataModel implements IMyPageTab1DataModel{

    @NonNull
    @Override
    public Observable<List<Tab1VO>> getAvailableTab1VOs() {
//        return Observable.fromCallable(this::getTab1VOs);
        return null;
    }

//    @NonNull
//    private List<Tab1VO> getTab1VOs() {
//        return Arrays
//                .asList(new Tab1VO("Test1", "11", 20000, 5),
//                        new Tab1VO("Test2", "8", 15000, 10),
//                        new Tab1VO("Test3", "12", 30000, 8));
//    }
}
