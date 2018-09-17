package com.depromeet.hanriver.hanrivermeetup.fragment.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.depromeet.hanriver.hanrivermeetup.R;
import com.depromeet.hanriver.hanrivermeetup.activity.main.MainActivity;
import com.depromeet.hanriver.hanrivermeetup.common.PreferencesManager;
import com.depromeet.hanriver.hanrivermeetup.service.LoginService;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import javax.net.ssl.HttpsURLConnection;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class LoginFragment extends Fragment {
    LoginButton fb_loginButton;
    CallbackManager callbackManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);

        if (AccessToken.getCurrentAccessToken() != null) { //기존 로그인 되어있을 경우
            String accessed_token = AccessToken.getCurrentAccessToken().getToken();
            String user_id = AccessToken.getCurrentAccessToken().getUserId();
            login(user_id, accessed_token);
        }

        callbackManager = CallbackManager.Factory.create();
        fb_loginButton = view.findViewById(R.id.login_button);
        fb_loginButton.setFragment(this);
        fb_loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                String id = loginResult.getAccessToken().getUserId();
                String token = loginResult.getAccessToken().getToken();
                login(id, token);
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

    private void login(String id, String token) {
        PreferencesManager.setUserID(id);
        PreferencesManager.setFacebookToken(token);

        LoginService.getInstance().login(id, token)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(res -> {
                    if(res.code() == HttpsURLConnection.HTTP_OK){
                        PreferencesManager.setNickname(res.body().nickname);
                        intentMain();
                    }
                    else if(res.code() == HttpsURLConnection.HTTP_NOT_FOUND) {
                        replaceRegisterPage();
                    }
                    else {
                        Toast.makeText(getActivity(),
                                "서버에서 오류가 발생했습니다", Toast.LENGTH_SHORT).show();
                    }
                })
                .subscribe();
    }

    private void intentMain() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        this.getActivity().finish();
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

    private void replaceRegisterPage() {
        getFragmentManager().beginTransaction().
                replace(R.id.login_activity_container, new CreateAccountFragment()).
                commitAllowingStateLoss();
    }
}
