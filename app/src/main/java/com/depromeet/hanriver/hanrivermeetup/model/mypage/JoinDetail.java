package com.depromeet.hanriver.hanrivermeetup.model.mypage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JoinDetail {

    @SerializedName("application_seq")
    @Expose
    private Integer applicationSeq;
    @SerializedName("meeting_seq")
    @Expose
    private Integer meetingSeq;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("description")
    @Expose
    private Object description;
    @SerializedName("participants_cnt")
    @Expose
    private Integer participantsCnt;
    @SerializedName("application_time")
    @Expose
    private String applicationTime;
    @SerializedName("contact")
    @Expose
    private String contact;
    @SerializedName("contact_seq")
    @Expose
    private Integer contactSeq;

    public Integer getApplicationSeq() {
        return applicationSeq;
    }

    public void setApplicationSeq(Integer applicationSeq) {
        this.applicationSeq = applicationSeq;
    }

    public Integer getMeetingSeq() {
        return meetingSeq;
    }

    public void setMeetingSeq(Integer meetingSeq) {
        this.meetingSeq = meetingSeq;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public Integer getParticipantsCnt() {
        return participantsCnt;
    }

    public void setParticipantsCnt(Integer participantsCnt) {
        this.participantsCnt = participantsCnt;
    }

    public String getApplicationTime() {
        return applicationTime;
    }

    public void setApplicationTime(String applicationTime) {
        this.applicationTime = applicationTime;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Integer getContactSeq() {
        return contactSeq;
    }

    public void setContactSeq(Integer contactSeq) {
        this.contactSeq = contactSeq;
    }

}
