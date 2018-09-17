package com.depromeet.hanriver.hanrivermeetup.firebase;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class CustomFirebaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG = CustomFirebaseInstanceIDService.class.getSimpleName();

    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);
    }

    public static String getToken() {
        return FirebaseInstanceId.getInstance().getToken();
    }
}