package com.depromeet.hanriver.hanrivermeetup.fragment.mypage.ViewModel;

import android.support.annotation.NonNull;

import com.depromeet.hanriver.hanrivermeetup.datamodel.mypage.IMyPageTab1DataModel;
import com.depromeet.hanriver.hanrivermeetup.datamodel.mypage.IMyPageTab3DataModel;
import com.depromeet.hanriver.hanrivermeetup.model.mypage.Tab1VO;
import com.depromeet.hanriver.hanrivermeetup.model.mypage.Tab3VO;
import com.depromeet.hanriver.hanrivermeetup.schedulers.ISchedulerProvider;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

public class Tab3ViewModel {
    @NonNull
    IMyPageTab3DataModel mMyPageTab3Model;

    @NonNull
    private ISchedulerProvider mSchedulerProvider;

    @NonNull
    private final BehaviorSubject<Tab3VO> mSelectedActivity = BehaviorSubject.create();

    public Tab3ViewModel(@NonNull final IMyPageTab3DataModel myPageTab3DataModel,
                         @NonNull final ISchedulerProvider schedulerProvider) {
        mMyPageTab3Model = myPageTab3DataModel;
        mSchedulerProvider = schedulerProvider;
    }

    @NonNull
    public Observable<List<Tab3VO>> getAvailableTab3VOs() {
        return mMyPageTab3Model.getAvailableTab3VOs();
    }
}
