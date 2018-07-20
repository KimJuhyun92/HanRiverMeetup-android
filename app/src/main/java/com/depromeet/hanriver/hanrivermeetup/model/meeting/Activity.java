package com.depromeet.hanriver.hanrivermeetup.model.meeting;

import android.support.annotation.NonNull;

public class Activity {
    @NonNull
    private final String mName;

    @NonNull
    private final String mDescription;

    @NonNull
    private final int mImage;

    public Activity(@NonNull final String name, @NonNull final String description,final int Image) {
        mName = name;
        mDescription = description;
        mImage = Image;
    }

    @NonNull
    public String getName() {
        return mName;
    }

    @NonNull
    public String getDescription() {
        return mDescription;
    }

    @NonNull
    public int getmImage() {
        return mImage;
    }
}

