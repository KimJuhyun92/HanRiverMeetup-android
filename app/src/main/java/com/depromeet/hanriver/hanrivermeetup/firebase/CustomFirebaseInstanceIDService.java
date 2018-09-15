package com.depromeet.hanriver.hanrivermeetup.firebase;

import android.content.SharedPreferences;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class CustomFirebaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG = CustomFirebaseInstanceIDService.class.getSimpleName();

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);

//        if (refreshedToken!=null) {
//            SettingPreferences.setStringValueInPref(this, SettingPreferences.REG_ID, refreshedToken);
//        }
        // TODO: Implement this method to send any registration to your app's servers.
        sendRegistrationToServer(refreshedToken);
    }

    private void sendRegistrationToServer(String token) {
        // Add custom implementation, as needed.
    }
}

//    private String AD_FCM_TOKEN = "AD_FCM_TOKEN";
//
//    @Override
//    public void onTokenRefresh() {
//        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
//
//        if (isValidString(refreshedToken)) { //토큰이 널이거나 빈 문자열이 아닌 경우
//            if (!isValidString(getSharedPreferencesStringData())) { //토큰에 데이터가 없는 경우에만 저장
//                setSharedPreferencesStringData(refreshedToken);
//            }
//
//            if (isValidString(getSharedPreferencesStringData())) { //로그인 상태일 경우에는 서버로 보낸다.
//                if (!refreshedToken.equals(getSharedPreferencesStringData())) { //기존에 저장된 토큰과 비교하여 다를 경우에만 서버 업데이트
//                    setSharedPreferencesStringData(refreshedToken);
//                    sendRegistrationToServer(refreshedToken);
//                }
//            }
//        }
//    }
//
//    private void sendRegistrationToServer(String token) {
//        //FCM 토큰 갱신
//    }
//
//    private void setSharedPreferencesStringData(String token){
//        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
//        SharedPreferences.Editor editor = pref.edit();
//        editor.putString(AD_FCM_TOKEN, token);
//        editor.commit();
//    }
//
//    private String getSharedPreferencesStringData(){
//        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
//        return pref.getString(AD_FCM_TOKEN, "");
//    }
//
//    private boolean isValidString(String token) {
//        return token.isEmpty();
//    }
// }
