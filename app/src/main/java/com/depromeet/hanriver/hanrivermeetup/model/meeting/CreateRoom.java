package com.depromeet.hanriver.hanrivermeetup.model.meeting;

import android.support.annotation.NonNull;

public class CreateRoom {

    @NonNull
    private int activity_seq;

    @NonNull
    private String user_id;

    @NonNull
    private String description;

    @NonNull
    private int participants_cnt;

    @NonNull
    private String meeting_location;

    @NonNull
    private String meeting_time;

    @NonNull
    private int expected_cost;

    @NonNull
    private String title;

    @NonNull
    private String contact;




    @NonNull
    public int getActivity_seq() {
        return activity_seq;
    }

    public void setActivity_seq(@NonNull int activity_seq) {
        this.activity_seq = activity_seq;
    }

    @NonNull
    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(@NonNull String user_id) {
        this.user_id = user_id;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    public void setDescription(@NonNull String description) {
        this.description = description;
    }

    @NonNull
    public int getParticipants_cnt() {
        return participants_cnt;
    }

    public void setParticipants_cnt(@NonNull int participants_cnt) {
        this.participants_cnt = participants_cnt;
    }

    @NonNull
    public String getMeeting_location() {
        return meeting_location;
    }

    public void setMeeting_location(@NonNull String meeting_location) {
        this.meeting_location = meeting_location;
    }

    @NonNull
    public String getMeeting_time() {
        return meeting_time;
    }

    public void setMeeting_time(@NonNull String meeting_time) {
        this.meeting_time = meeting_time;
    }

    @NonNull
    public int getExpected_cost() {
        return expected_cost;
    }

    public void setExpected_cost(@NonNull int expected_cost) {
        this.expected_cost = expected_cost;
    }


    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    @NonNull
    public String getContact() {
        return contact;
    }

    public void setContact(@NonNull String contact) {
        this.contact = contact;
    }
}
