package com.depromeet.hanriver.hanrivermeetup.model.meeting;

import android.support.annotation.NonNull;

public class Activity {
    @NonNull
    private final String mName;

    @NonNull
    private final String mDescription;

    public Activity(@NonNull final String name, @NonNull final String description) {
        mName = name;
        mDescription = description;
    }

    @NonNull
    public String getName() {
        return mName;
    }

    @NonNull
    public String getDescription() {
        return mDescription;
    }
}

