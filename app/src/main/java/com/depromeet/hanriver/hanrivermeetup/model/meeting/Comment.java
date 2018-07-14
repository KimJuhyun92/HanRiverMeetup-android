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
    private String test;

    @SerializedName("creation_time")
    private Date createdTime;
}
