package com.depromeet.hanriver.hanrivermeetup.datamodel.mypage;

import android.support.annotation.NonNull;

import com.depromeet.hanriver.hanrivermeetup.model.mypage.Tab2VO;

import java.util.List;

import io.reactivex.Observable;

public interface IMyPageTab2DataModel {
    @NonNull
    Observable<List<Tab2VO>> getAvailableTab2VOs();
}
