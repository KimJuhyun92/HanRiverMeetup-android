package com.depromeet.hanriver.hanrivermeetup.schedulers;

import android.support.annotation.NonNull;

import io.reactivex.Scheduler;

public interface ISchedulerProvider {
    @NonNull
    Scheduler computation();

    @NonNull
    Scheduler ui();
}
