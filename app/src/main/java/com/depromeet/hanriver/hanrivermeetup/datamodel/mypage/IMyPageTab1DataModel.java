package com.depromeet.hanriver.hanrivermeetup.datamodel.mypage;

import android.support.annotation.NonNull;

import com.depromeet.hanriver.hanrivermeetup.model.mypage.Tab1VO;

import java.util.List;

import io.reactivex.Observable;

public interface IMyPageTab1DataModel {
    @NonNull
    Observable<List<Tab1VO>> getAvailableTab1VOs();
}
