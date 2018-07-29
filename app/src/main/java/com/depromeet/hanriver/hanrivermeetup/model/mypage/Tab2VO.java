package com.depromeet.hanriver.hanrivermeetup.model.mypage;

import android.support.annotation.NonNull;

public class Tab2VO {
    @NonNull
    private final String title;

    @NonNull
    private final String location;

    @NonNull
    private final String meeting_time;

    @NonNull
    private final int expected_cost;

    @NonNull
    private final int participants_cnt;

    public Tab2VO(@NonNull final String title, @NonNull final String location, @NonNull final String meeting_time, @NonNull final int expected_cost, @NonNull final int participants_cnt) {
        this.title = title;
        this.location = location;
        this.meeting_time = meeting_time;
        this.expected_cost = expected_cost;
        this.participants_cnt = participants_cnt;
    }

    @NonNull
    public String getTitle(){
        return title;
    }

    @NonNull
    public String getLocation() {
        return location;
    }

    @NonNull
    public String getMeeting_time() {
        return meeting_time;
    }

    @NonNull
    public int getExpected_cost(){
        return expected_cost;
    }

    @NonNull
    public int getParticipants_cnt(){
        return participants_cnt;
    }
}
