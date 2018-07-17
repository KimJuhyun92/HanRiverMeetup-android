package com.depromeet.hanriver.hanrivermeetup.model.mypage;

import android.support.annotation.NonNull;

public class Tab3VO {
    @NonNull
    private final String mText;

    @NonNull
    private final int mImg;

    public Tab3VO(@NonNull final String text, @NonNull final int img) {
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
