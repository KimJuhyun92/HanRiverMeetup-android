package com.depromeet.hanriver.hanrivermeetup.model.meeting;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class JoinRequest {
    @SerializedName("application_seq")
    private int id;

    @SerializedName("meeting_seq")
    private int meetingID;

    @SerializedName("user_id")
    private String userID;

    @SerializedName("description")
    private String description;

    @SerializedName("participants_cnt")
    private int participantsCnt;

    @SerializedName("application_time")
    private Date applicationTime;

    @SerializedName("contact")
    private String contact;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMeetingID() {
        return meetingID;
    }

    public void setMeetingID(int meetingID) {
        this.meetingID = meetingID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getParticipantsCnt() {
        return participantsCnt;
    }

    public void setParticipantsCnt(int participantsCnt) {
        this.participantsCnt = participantsCnt;
    }

    public Date getApplicationTime() {
        return applicationTime;
    }

    public void setApplicationTime(Date applicationTime) {
        this.applicationTime = applicationTime;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
