package com.depromeet.hanriver.hanrivermeetup.fragment.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.depromeet.hanriver.hanrivermeetup.R;
import com.depromeet.hanriver.hanrivermeetup.fragment.meeting.MeetingCategoryFragment;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginFragment extends Fragment{

    LoginButton fb_loginButton;
    CallbackManager callbackManager;
    AccessTokenTracker accessTokenTracker;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//       callbackManager = CallbackManager.Factory.create();
        View view = inflater.inflate(R.layout.login_page, container, false);
        fb_loginButton = view.findViewById(R.id.login_button);
        fb_loginButton.setReadPermissions("email");
        // If using in a fragment
        fb_loginButton.setFragment(this);

        // Callback registration
        fb_loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code

                Log.d("AccessToken@@ : ",""+loginResult.getAccessToken());
                Log.d("UserId ",""+loginResult.getAccessToken().getUserId());
//                Log.d("getName()", String.valueOf(Profile.getCurrentProfile().getName())); // 이름
//                Log.d("getAccessToken()", String.valueOf(loginResult.getAccessToken()));
//                Log.d("getId()", String.valueOf(Profile.getCurrentProfile().getId()));

//                Log.d("getProfilePictureUri", String.valueOf(Profile.getCurrentProfile().getProfilePictureUri(200, 200)));
                getUserEmail(loginResult);

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
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
// App code
                Log.d("eNuri", "Current Token : " + currentAccessToken);
            }
        };
    }



        public void getUserEmail(LoginResult loginResult){
        GraphRequest request = GraphRequest.newMeRequest(
                loginResult.getAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        try {
                            String email = response.getJSONObject().getString("email");
                            Log.d("email", email);
//                            userEmailTv.setText(email);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "email");
        request.setParameters(parameters);
        request.executeAsync();
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
