package com.depromeet.hanriver.hanrivermeetup.model.meeting;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Comment {
    @SerializedName("comment_seq")
    private int id;

    @SerializedName("meeting_seq")
    private int meeting_seq;

    @SerializedName("user_id")
    private String userID;

    @SerializedName("comment")
    private String text;

    @SerializedName("creation_time")
    private String createdTime;

    @SerializedName("nickname")
    private String nickname;

    public Comment(String userID, String text, String createdTime, String nickname) {
        this.userID = userID;
        this.text = text;
        this.createdTime = createdTime;
        this.nickname = nickname;
    }

    public Comment() {
    }

    public int getId() {
        return id;
    }

    public int getMeeting_seq() {
        return meeting_seq;
    }

    public String getUserID() {
        return userID;
    }

    public String getText() {
        return text;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public String getNickname() {
        return nickname;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMeeting_seq(int meeting_seq) {
        this.meeting_seq = meeting_seq;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
