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
}
