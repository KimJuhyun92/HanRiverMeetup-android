package com.depromeet.hanriver.hanrivermeetup.Activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.depromeet.hanriver.hanrivermeetup.Activity.main.MainActivity;
import com.depromeet.hanriver.hanrivermeetup.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class LoginActivity extends AppCompatActivity {

    //    LoginManager loginManager; // 로그인 상태인지 아닌지 확인 할 것 (버튼을 커스텀으로 생성할 시 필요)
    LoginButton fb_loginButton; // 커스텀 아닌 기본 제공되는 버튼.
    CallbackManager callbackManager; //요청 응답 받을 것
    private static String accessed_token; // 로그인 되어있을 경우 저장된 토큰
    private static String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        if(AccessToken.getCurrentAccessToken().getToken()!=null) { //기존 로그인 되어있을 경우
            setAccessed_token(AccessToken.getCurrentAccessToken().getToken());
            setUserId(AccessToken.getCurrentAccessToken().getUserId());
            Log.d("accessed_token@@: "," "+getAccessed_token());
            Log.d("user Id@@: "," "+getUserId());
            //통신 (?) //

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        fb_loginButton = findViewById(R.id.login_button);
        fb_loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) { //로그인 버튼 눌러서 로그인 성공 시
                // App code

                Log.d("AccessToken@@ : "," "+loginResult.getAccessToken().getToken());
                Log.d("UserId@@ : ",""+loginResult.getAccessToken().getUserId());
                setAccessed_token(loginResult.getAccessToken().getToken());

                //통신 (?)//


                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);

            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public static String getAccessed_token() {
        return accessed_token;
    }

    public static void setAccessed_token(String accessed_token) {
        LoginActivity.accessed_token = accessed_token;
    }

    public static String getUserId() {
        return userId;
    }

    public static void setUserId(String userId) {
        LoginActivity.userId = userId;
    }
}
