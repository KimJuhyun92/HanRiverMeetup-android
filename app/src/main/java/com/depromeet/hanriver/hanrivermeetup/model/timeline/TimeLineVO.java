package com.depromeet.hanriver.hanrivermeetup.model.timeline;

import android.support.annotation.NonNull;

public class TimeLineVO {

    @NonNull
    private final String mText;

    @NonNull
    private final int mImg;

    public TimeLineVO(@NonNull final String text, @NonNull final int img) {
        mText = text;
        mImg = img;
    }

    @NonNull
    public String getText(){
        return mText;
    }

    @NonNull
    public int getImg() {
        return mImg;
    }
}

