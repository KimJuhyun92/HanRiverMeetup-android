package com.depromeet.hanriver.hanrivermeetup.datamodel.mypage;

import android.support.annotation.NonNull;

import com.depromeet.hanriver.hanrivermeetup.model.mypage.Tab1VO;
import com.depromeet.hanriver.hanrivermeetup.model.mypage.Tab3VO;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;

public class MyPageTab3DataModel implements IMyPageTab3DataModel {
    @NonNull
    @Override
    public Observable<List<Tab3VO>> getAvailableTab3VOs() {
        return null;
    }
}
