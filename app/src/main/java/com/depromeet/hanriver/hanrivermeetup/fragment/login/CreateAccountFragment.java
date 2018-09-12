package com.depromeet.hanriver.hanrivermeetup.fragment.login;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.depromeet.hanriver.hanrivermeetup.R;
import com.depromeet.hanriver.hanrivermeetup.activity.main.MainActivity;
import com.depromeet.hanriver.hanrivermeetup.service.LoginService;
import com.facebook.login.Login;

public class CreateAccountFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_account, container, false);

        Button registerButton = view.findViewById(R.id.create_nickname_button);
        AppCompatEditText editText = view.findViewById(R.id.edit_nickname);;

        Log.d("###nickname",""+ LoginFragment.getUser_id());

        registerButton.setOnClickListener(v -> {
            String str = editText.getText().toString();
            LoginService.getInstance().registerUser(str)
                    .subscribe(res -> {
                        if(res) {
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }
                    });
        });

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}