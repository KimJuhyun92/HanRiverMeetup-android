package com.depromeet.hanriver.hanrivermeetup.fragment.mypage.ViewModel;

import android.support.annotation.NonNull;

import com.depromeet.hanriver.hanrivermeetup.datamodel.mypage.IMyPageTab1DataModel;
import com.depromeet.hanriver.hanrivermeetup.model.mypage.Tab1VO;
import com.depromeet.hanriver.hanrivermeetup.schedulers.ISchedulerProvider;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

public class Tab1ViewModel {
    @NonNull
    IMyPageTab1DataModel mMyPageTab1Model;

    @NonNull
    private ISchedulerProvider mSchedulerProvider;

    @NonNull
    private final BehaviorSubject<Tab1VO> mSelectedActivity = BehaviorSubject.create();

    public Tab1ViewModel(@NonNull final IMyPageTab1DataModel myPageTab1DataModel,
                             @NonNull final ISchedulerProvider schedulerProvider) {
        mMyPageTab1Model = myPageTab1DataModel;
        mSchedulerProvider = schedulerProvider;
    }

    @NonNull
    public Observable<List<Tab1VO>> getAvailableTab1VOs() {
        return mMyPageTab1Model.getAvailableTab1VOs();
    }
}
