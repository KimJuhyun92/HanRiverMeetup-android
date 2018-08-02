package com.depromeet.hanriver.hanrivermeetup.fragment.mypage.ViewModel;

import android.support.annotation.NonNull;

import com.depromeet.hanriver.hanrivermeetup.datamodel.mypage.IMyPageTab1DataModel;
import com.depromeet.hanriver.hanrivermeetup.datamodel.mypage.IMyPageTab2DataModel;
import com.depromeet.hanriver.hanrivermeetup.model.mypage.Tab1VO;
import com.depromeet.hanriver.hanrivermeetup.model.mypage.Tab2VO;
import com.depromeet.hanriver.hanrivermeetup.schedulers.ISchedulerProvider;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

public class Tab2ViewModel {
    @NonNull
    IMyPageTab2DataModel mMyPageTab2Model;

    @NonNull
    private ISchedulerProvider mSchedulerProvider;

    @NonNull
    private final BehaviorSubject<Tab2VO> mSelectedActivity = BehaviorSubject.create();

    public Tab2ViewModel(@NonNull final IMyPageTab2DataModel myPageTab2DataModel,
                         @NonNull final ISchedulerProvider schedulerProvider) {
        mMyPageTab2Model = myPageTab2DataModel;
        mSchedulerProvider = schedulerProvider;
    }

    @NonNull
    public Observable<List<Tab2VO>> getAvailableTab2VOs() {
        return mMyPageTab2Model.getAvailableTab2VOs();
    }
}
