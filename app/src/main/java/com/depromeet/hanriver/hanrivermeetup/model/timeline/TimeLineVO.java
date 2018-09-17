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

    @NonNull
    public int timeline_seq;

    @NonNull
    public String user_id;

    @NonNull
    public String location;

    @NonNull
    public String imageurl;

    @NonNull
    public String content;

    @NonNull
    public String creation_time;

    @NonNull
    public String nickname;
}

