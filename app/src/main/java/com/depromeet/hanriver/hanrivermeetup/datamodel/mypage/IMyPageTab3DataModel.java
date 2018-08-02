package com.depromeet.hanriver.hanrivermeetup.datamodel.mypage;

import android.support.annotation.NonNull;

import com.depromeet.hanriver.hanrivermeetup.model.mypage.Tab3VO;

import java.util.List;

import io.reactivex.Observable;

public interface IMyPageTab3DataModel {
    @NonNull
    Observable<List<Tab3VO>> getAvailableTab3VOs();
}
