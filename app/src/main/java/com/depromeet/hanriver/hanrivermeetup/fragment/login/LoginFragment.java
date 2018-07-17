package com.depromeet.hanriver.hanrivermeetup.fragment.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.depromeet.hanriver.hanrivermeetup.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class LoginFragment extends Fragment{
//    LoginManager loginManager; // 로그인 상태인지 아닌지 확인 할 것 (버튼을 커스텀으로 생성할 시 필요)
    LoginButton fb_loginButton; // 커스텀 아닌 기본 제공되는 버튼.
    CallbackManager callbackManager; //요청 응답 받을 것

    private static String accessed_token; // 로그인 되어있을 경우 저장된 토큰

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);

        if(AccessToken.getCurrentAccessToken() != null) { //기존 로그인 되어있을 경우
            accessed_token = AccessToken.getCurrentAccessToken().getToken();
            Log.d("accessed_token@@: "," "+getAccessed_token());
        }

        //FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        fb_loginButton = view.findViewById(R.id.login_button);
        fb_loginButton.setFragment(this);
        fb_loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) { //로그인 버튼 눌러서 로그인 성공 시
                // App code
                Log.d("AccessToken@@ : "," "+loginResult.getAccessToken().getToken());
                Log.d("UserId@@ : ",""+loginResult.getAccessToken().getUserId());
                setAccessed_token(loginResult.getAccessToken().getToken());

                getFragmentManager().beginTransaction().
                        replace(R.id.login_activity_container, new CreateAccountFragment()).
                        addToBackStack("frags").commit();
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

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        LoginFragment.accessed_token = accessed_token;
    }
}
