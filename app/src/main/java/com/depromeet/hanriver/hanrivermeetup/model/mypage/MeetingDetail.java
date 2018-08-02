package com.depromeet.hanriver.hanrivermeetup.model.mypage;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MeetingDetail {

    @SerializedName("meeting_seq")
    @Expose
    private Integer meetingSeq;
    @SerializedName("activity_seq")
    @Expose
    private Integer activitySeq;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("description")
    @Expose
    private String description;
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
    private String modificationTime;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("contact")
    @Expose
    private String contact;
    @SerializedName("contact_seq")
    @Expose
    private Integer contactSeq;
    @SerializedName("nickname")
    @Expose
    private String nickname;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
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

    public String getModificationTime() {
        return modificationTime;
    }

    public void setModificationTime(String modificationTime) {
        this.modificationTime = modificationTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

}
