package com.depromeet.hanriver.hanrivermeetup.model.meeting;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class MatchingDetail {
    @SerializedName("contact_seq")
    private int id;

    @SerializedName("meeting_seq")
    private int meetingID;

    @SerializedName("application_seq")
    private int requestID;

    @SerializedName("contact_time")
    private Date contactTime;

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

    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public Date getContactTime() {
        return contactTime;
    }

    public void setContactTime(Date contactTime) {
        this.contactTime = contactTime;
    }
}
