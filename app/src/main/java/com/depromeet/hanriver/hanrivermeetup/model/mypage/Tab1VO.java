package com.depromeet.hanriver.hanrivermeetup.model.mypage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tab1VO {

    @SerializedName("meeting_seq")
    @Expose
    private Integer meetingSeq;
    @SerializedName("activity_seq")
    @Expose
    private Integer activitySeq;
    @SerializedName("user_id")
    @Expose
    private Object userId;
    @SerializedName("description")
    @Expose
    private Object description;
    @SerializedName("participants_cnt")
    @Expose
    private Integer participantsCnt;
    @SerializedName("meeting_location")
    @Expose
    private String meetingLocation;
    @SerializedName("meeting_time")
    @Expose
    private String meetingTime;
    @SerializedName("expected_cost")
    @Expose
    private Integer expectedCost;
    @SerializedName("creation_time")
    @Expose
    private String creationTime;
    @SerializedName("modification_time")
    @Expose
    private Object modificationTime;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("contact")
    @Expose
    private Object contact;
    @SerializedName("contact_seq")
    @Expose
    private Integer contactSeq;
    @SerializedName("nickname")
    @Expose
    private Object nickname;

    public Integer getMeetingSeq() {
        return meetingSeq;
    }

    public void setMeetingSeq(Integer meetingSeq) {
        this.meetingSeq = meetingSeq;
    }

    public Integer getActivitySeq() {
        return activitySeq;
    }

    public void setActivitySeq(Integer activitySeq) {
        this.activitySeq = activitySeq;
    }

    public Object getUserId() {
        return userId;
    }

    public void setUserId(Object userId) {
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

    public String getMeetingLocation() {
        return meetingLocation;
    }

    public void setMeetingLocation(String meetingLocation) {
        this.meetingLocation = meetingLocation;
    }

    public String getMeetingTime() {
        return meetingTime;
    }

    public void setMeetingTime(String meetingTime) {
        this.meetingTime = meetingTime;
    }

    public Integer getExpectedCost() {
        return expectedCost;
    }

    public void setExpectedCost(Integer expectedCost) {
        this.expectedCost = expectedCost;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public Object getModificationTime() {
        return modificationTime;
    }

    public void setModificationTime(Object modificationTime) {
        this.modificationTime = modificationTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Object getContact() {
        return contact;
    }

    public void setContact(Object contact) {
        this.contact = contact;
    }

    public Integer getContactSeq() {
        return contactSeq;
    }

    public void setContactSeq(Integer contactSeq) {
        this.contactSeq = contactSeq;
    }

    public Object getNickname() {
        return nickname;
    }

    public void setNickname(Object nickname) {
        this.nickname = nickname;
    }

}