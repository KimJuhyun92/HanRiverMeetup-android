package com.depromeet.hanriver.hanrivermeetup.model.meeting;

import android.support.annotation.NonNull;

public class MeetingDetail2 {

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
    private String creation_time;

    @NonNull
    private String modification_time;

    @NonNull
    private String title;

    @NonNull
    private String contact;

    public MeetingDetail2(@NonNull int activity_seq, @NonNull String user_id, @NonNull String description, @NonNull int participants_cnt, @NonNull String meeting_location, @NonNull String meeting_time, @NonNull int expected_cost, @NonNull String creation_time, @NonNull String modification_time, @NonNull String title, @NonNull String contact) {
        this.activity_seq = activity_seq;
        this.user_id = user_id;
        this.description = description;
        this.participants_cnt = participants_cnt;
        this.meeting_location = meeting_location;
        this.meeting_time = meeting_time;
        this.expected_cost = expected_cost;
        this.creation_time = creation_time;
        this.modification_time = modification_time;
        this.title = title;
        this.contact = contact;
    }

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
    public String getCreation_time() {
        return creation_time;
    }

    public void setCreation_time(@NonNull String creation_time) {
        this.creation_time = creation_time;
    }

    @NonNull
    public String getModification_time() {
        return modification_time;
    }

    public void setModification_time(@NonNull String modification_time) {
        this.modification_time = modification_time;
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
