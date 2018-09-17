package com.depromeet.hanriver.hanrivermeetup.model.timeline;

import android.support.annotation.NonNull;

import java.util.Date;

public class TimeLineVO {
    public TimeLineVO()
    {

    }

    public TimeLineVO(@NonNull final String text) {
        content = text;
    }

    //@NonNull
    private int timeline_seq;

    //@NonNull
    private String user_id;

    //@NonNull
    private String location;

    //@NonNull
    private String imageurl;

    //@NonNull
    private String content;

    //@NonNull
    private String creation_time;

    //@NonNull
    public int getTimeline_seq() {
        return timeline_seq;
    }

    public void setTimeline_seq(@NonNull int timeline_seq) {
        this.timeline_seq = timeline_seq;
    }

    @NonNull
    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(@NonNull String user_id) {
        this.user_id = user_id;
    }

    @NonNull
    public String getLocation() {
        return location;
    }

    public void setLocation(@NonNull String location) {
        this.location = location;
    }

    @NonNull
    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(@NonNull String imageurl) {
        this.imageurl = imageurl;
    }

    @NonNull
    public String getContent() {
        return content;
    }

    public void setContent(@NonNull String content) {
        this.content = content;
    }

    @NonNull
    public String getCreation_time() {
        return creation_time;
    }

    public void setCreation_time(@NonNull String creation_time) {
        this.creation_time = creation_time;
    }
//
//    @NonNull
//    private final String mText;
//
//    @NonNull
//    private final int mImg;
//
//    public TimeLineVO(@NonNull final String text, @NonNull final int img) {
//        mText = text;
//        mImg = img;
//    }
//
//    @NonNull
//    public String getText(){
//        return mText;
//    }
//
//    @NonNull
//    public int getImg() {
//        return mImg;
//    }
}

